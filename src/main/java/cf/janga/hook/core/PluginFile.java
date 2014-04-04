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

/**
 * Represents the package file of a plugin.
 * 
 * @author Emerson Loureiro
 */
public interface PluginFile<T extends CoreAPI> {

	/**
	 * Returns the path for this plugin file.
	 * 
	 * @return String
	 * @throws PluginException
	 *             If there's any I/O error.
	 */
	String getFilePath() throws PluginException;

	/**
	 * Returns the size, in bytes, of this plugin file.
	 * 
	 * @return int
	 * @throws PluginException
	 *             If there's any I/O error.
	 */
	long getSize() throws PluginException;

	/**
	 * Returns the name of the file.
	 * 
	 * @return String
	 * @throws PluginException
	 *             If there's any I/O error.
	 */
	String getFileName() throws PluginException;

	/**
	 * Returns the class of the plugin wrapped by this file.
	 * 
	 * @return String
	 * @throws PluginException
	 *             If there's any I/O error.
	 */
	String getPluginClass() throws PluginException;
}
