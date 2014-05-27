/*******************************************************************************
 * Copyright (c) 2014 Emerson Loureiro.
 * 
 * All rights reserved. This program and the accompanying materials are made
 * available under the terms of the Eclipse Public License v1.0 which
 * accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html.
 * 
 * Contributors:
 *    Emerson Loureiro - initial API, implementation, and documentation
 *******************************************************************************/
package cf.janga.hook.core;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import cf.janga.hook.utils.FileConstants;
import cf.janga.hook.utils.IOUtils;

/**
 * A simple implementation of the {@link PluginLoader} interface. It manually
 * loads all jars and puts all classes in the classpath for the application.
 * 
 * @author Emerson Loureiro
 * 
 */
@SuppressWarnings("unchecked")
public class SimplePluginLoader implements PluginLoader {

	private final PluginRegistry pluginRegistry;

	public SimplePluginLoader() {
		this.pluginRegistry = new DefaultPluginRegistry();
	}

	@Override
	public void init() throws PlatformException {
	}

	@Override
	public <T extends CoreAPI> boolean loadPlugins(String path, HostApplication<T> application) throws PlatformException {
		List<PluginFile<T>> pluginFiles = loadPluginFiles(path);

		if (pluginFiles.isEmpty()) {
			return true;
		}
		boolean result = true;
		for (PluginFile<T> pluginFile : pluginFiles) {
			try {
				Plugin<T> plugin = loadPlugin(application, pluginFile);
				this.pluginRegistry.register(new DefaultPluginRegistration(plugin.getName(), plugin.getDescription(), plugin.getVersion(), plugin
						.getClass().getName(), pluginFile.getFileName()));
			} catch (Exception e) {
				result = false;
				this.pluginRegistry.register(new DefaultPluginRegistration(getPluginFileNameForError(pluginFile), getPluginClassForError(pluginFile),
						e.getMessage()));
			}
		}
		return result;
	}

	@Override
	public void stop() throws PlatformException {
	}

	@Override
	public PluginRegistry getPluginRegistry() {
		return this.pluginRegistry;
	}

	// ----------------------
	// Utiliy Methods
	// ----------------------

	private <T extends CoreAPI> String getPluginClassForError(PluginFile<T> file) {
		try {
			return file.getPluginClass();
		} catch (PluginException e) {
			return "The plugin class was not available. File: " + getPluginFileNameForError(file);
		}
	}

	private <T extends CoreAPI> String getPluginFileNameForError(PluginFile<T> file) {
		try {
			return file.getFileName();
		} catch (PluginException e) {
			return "The plugin file name was not available";
		}
	}

	private <T extends CoreAPI> Plugin<T> loadPlugin(HostApplication<T> application, PluginFile<T> pluginFile) throws PluginException {
		Plugin<T> plugin = loadPluginIntoClasspath(pluginFile);
		for (Extension<T> extension : plugin.getExtensions()) {
			ExtensionPoint<T> extensionPoint = findExtensionPoint(application.getExtensionPoints(), extension);
			if (extensionPoint != null) {
				extensionPoint.accept(extension);
				extension.init(application.getCoreAPI());
			}
		}
		return plugin;
	}

	private <T extends CoreAPI> ExtensionPoint<T> findExtensionPoint(List<ExtensionPoint<T>> extensionPoints, Extension<T> extension) {
		ExtensionPoint<T> foundExtensionPoint = null;
		for (ExtensionPoint<T> extensionPoint : extensionPoints) {
			if (extensionPoint.canHandle(extension)) {
				foundExtensionPoint = extensionPoint;
				break;
			}
		}
		return foundExtensionPoint;
	}

	<T extends CoreAPI> Plugin<T> loadPluginIntoClasspath(PluginFile<T> pluginFile) throws PluginException {
		pluginFile.getFilePath();
		String pluginClassName = pluginFile.getPluginClass();
		if (StringUtils.isBlank(pluginClassName)) {
			throw new PluginException("The plugin class has not been provided on the manifest of the plugin file.");
		}
		try {
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] { new File(pluginFile.getFilePath()).toURI().toURL() });
			Class<Plugin<T>> pluginClass = (Class<Plugin<T>>) classLoader.loadClass(pluginClassName);
			return pluginClass.newInstance();
		} catch (MalformedURLException e) {
			throw new PluginException("Error loading plugin file. The plugin folder may be missing or malformed.", e);
		} catch (ClassNotFoundException e) {
			throw new PluginException("Error loading plugin file. The plugin class cannot be found on the file provided.", e);
		} catch (InstantiationException e) {
			throw new PluginException("Error instantiating the plugin class.", e);
		} catch (IllegalAccessException e) {
			throw new PluginException("Error instantiating the plugin. Illegal access to plugin class.", e);
		}
	}

	/** Package-private so that it can easily be overridden for testing. */
	<T extends CoreAPI> List<PluginFile<T>> loadPluginFiles(String path) throws PlatformException {
		File filePath = new File(path);
		List<PluginFile<T>> pluginFiles = new ArrayList<PluginFile<T>>();

		if (filePath.isDirectory()) {
			File[] jarFiles = filePath.listFiles(new JarFileFilter());
			for (File jarFile : jarFiles) {
				pluginFiles.add((PluginFile<T>) toPluginFile(jarFile));
			}
		} else {
			if (IOUtils.hasExtension(filePath, FileConstants.JAR_EXTENSION)) {
				pluginFiles.add((PluginFile<T>) toPluginFile(filePath));
			}
		}

		return pluginFiles;
	}

	private <T extends CoreAPI> PluginFile<T> toPluginFile(File jarFile) throws PlatformException {
		try {
			return (PluginFile<T>) new DefaultPluginFile(jarFile);
		} catch (PluginException e) {
			throw new PlatformException(e);
		}
	}

	private class JarFileFilter implements FileFilter {

		@Override
		public boolean accept(File pathname) {
			return pathname.getName() != null && pathname.getName().endsWith(FileConstants.JAR_EXTENSION);
		}
	}
}