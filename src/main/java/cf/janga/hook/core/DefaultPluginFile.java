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
import java.io.IOException;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import cf.janga.hook.utils.IOUtils;

class DefaultPluginFile implements PluginFile<CoreAPI> {

	private static final String PLUGIN_CLASS_MANIFEST_ENTRY = "Plugin-class";
	private final File file;
	private final JarFile jarFile;

	public DefaultPluginFile(File file) throws PluginException {
		if (!IOUtils.hasExtension(file, PluginConstants.PLUGIN_FILE_EXTENSION)) {
			throw new PluginException("The file provided is not a valid plugin file. Only .jar files are supported.");
		}
		this.file = file;
		try {
			this.jarFile = new JarFile(this.file);
		} catch (IOException e) {
			throw new PluginException("Error loading plugin file.", e);
		}
	}

	@Override
	public String getFilePath() throws PluginException {
		try {
			return this.file.getCanonicalPath();
		} catch (IOException e) {
			throw new PluginException("Error retrieving the file path", e);
		}
	}

	@Override
	public long getSize() throws PluginException {
		return this.file.getTotalSpace();
	}

	@Override
	public String getFileName() throws PluginException {
		return this.file.getName();
	}

	@Override
	public String getPluginClass() throws PluginException {
		try {
			Manifest manifest = this.jarFile.getManifest();
			return manifest.getMainAttributes().getValue(PLUGIN_CLASS_MANIFEST_ENTRY);
		} catch (IOException e) {
			throw new PluginException("Error retrieving plugin class from plugin file.", e);
		}
	}
}
