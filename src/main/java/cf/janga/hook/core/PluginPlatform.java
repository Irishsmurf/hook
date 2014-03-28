package cf.janga.hook.core;

import java.io.File;
import java.io.FileFilter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * A plugin platform is responsible for loading plugins and their extensions, as
 * well as providing them to be handled by their respective extension points.
 * The platform also keeps tracks of all loaded plugins and their states,
 * initializing and stopping them during the life cycle of an application.
 * 
 * @author Emerson Loureiro
 * 
 */
@SuppressWarnings("unchecked")
public class PluginPlatform {

	/**
	 * Initializes this plugin platform.
	 * 
	 * @throws PlatformException
	 *             If there's any error while initializing this platform.
	 */
	public void init() throws PlatformException {
	}

	/**
	 * Loads plugins found in the path provided. If the path is a folder, all
	 * proper plugin files will be read and the plugins loaded. If the path
	 * refers to a single plugin file, then only its plugin will be loaded.
	 * 
	 * @param path
	 *            The path from where the plugins should be loaded.
	 * @param application
	 *            The application where the plugins to be loaded will be hosted.
	 * @throws PlatformException
	 *             If there's any error while loading the plugin.
	 */
	public <T extends CoreAPI> boolean loadPlugins(String path,
			HostApplication<T> application) throws PlatformException {
		List<PluginFile<T>> pluginFiles = loadPluginFiles(path);

		if (pluginFiles.isEmpty()) {
			return true;
		}
		boolean result = true;
		for (PluginFile<T> pluginFile : pluginFiles) {
			try {
				Plugin<T> plugin = loadPluginIntoClasspath(pluginFile);
				for (Extension<T> extension : plugin.getExtensions()) {
					ExtensionPoint<T> extensionPoint = findExtensionPoint(
							application.getExtensionPoints(), extension);
					if (extensionPoint != null) {
						extensionPoint.accept(extension);
						extension.init(application.getCoreAPI());
					} else {
						// TODO Handle error
					}
				}
			} catch (PluginException e) {
				// TODO Handle error
				result = false;
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Handle error
				result = false;
				e.printStackTrace();
			}
		}
		return result;
	}

	/**
	 * Stop this plugin platform.
	 * 
	 * @throws PlatformException
	 *             If there's any error while stopping this platform.
	 */
	public void stop() throws PlatformException {
	}

	// ----------------------
	// Utiliy Methods
	// ----------------------

	private <T extends CoreAPI> ExtensionPoint<T> findExtensionPoint(
			List<ExtensionPoint<T>> extensionPoints, Extension<T> extension) {
		ExtensionPoint<T> foundExtensionPoint = null;
		for (ExtensionPoint<T> extensionPoint : extensionPoints) {
			if (extensionPoint.canHandle(extension)) {
				foundExtensionPoint = extensionPoint;
				break;
			}
		}
		return foundExtensionPoint;
	}

	<T extends CoreAPI> Plugin<T> loadPluginIntoClasspath(
			PluginFile<T> pluginFile) throws PluginException {
		pluginFile.getFilePath();
		String pluginClassName = pluginFile.getPluginClass();
		if (StringUtils.isBlank(pluginClassName)) {
			throw new PluginException(
					"The plugin class has not been provided on the manifest of the plugin file.");
		}
		try {
			URLClassLoader classLoader = URLClassLoader
					.newInstance(new URL[] { new File(pluginFile.getFilePath())
							.toURI().toURL() });
			Class<Plugin<T>> pluginClass = (Class<Plugin<T>>) classLoader
					.loadClass(pluginClassName);
			return pluginClass.newInstance();
		} catch (MalformedURLException e) {
			throw new PluginException("Error loading plugin file.", e);
		} catch (ClassNotFoundException e) {
			throw new PluginException(
					"Error loading plugin file. The plugin class cannot be found on the file provided.",
					e);
		} catch (InstantiationException e) {
			throw new PluginException("Error instantiating the plugin.", e);
		} catch (IllegalAccessException e) {
			throw new PluginException(
					"Error instantiating the plugin. Illegal access to plugin class.",
					e);
		}
	}

	/** Package-private so that it can easily be overridden for testing. */
	<T extends CoreAPI> List<PluginFile<T>> loadPluginFiles(String path)
			throws PlatformException {
		File filePath = new File(path);
		List<PluginFile<T>> pluginFiles = new ArrayList<PluginFile<T>>();

		if (filePath.isDirectory()) {
			File[] jarFiles = filePath.listFiles(new JarFileFilter());
			for (File jarFile : jarFiles) {
				PluginFile<T> pluginFile;
				try {
					pluginFile = (PluginFile<T>) new DefaultPluginFile(jarFile);
				} catch (PluginException e) {
					throw new PlatformException(e);
				}
				pluginFiles.add(pluginFile);
			}
		} else {
		}

		return pluginFiles;
	}

	private class JarFileFilter implements FileFilter {

		@Override
		public boolean accept(File pathname) {
			return pathname.getName() != null
					&& pathname.getName().endsWith(".jar");
		}
	}
}