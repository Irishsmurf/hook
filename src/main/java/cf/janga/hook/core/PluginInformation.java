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
 * Holds general information about a plugin. Mostly for reuse purpose across
 * different types that hold the same information.
 * 
 * @author Emerson Loureiro
 * 
 */
public interface PluginInformation {

	/**
	 * Returns the name of this plugin.
	 * 
	 * @return String.
	 */
	String getName();

	/**
	 * Returns the description of this plugin.
	 * 
	 * @return String.
	 */
	String getDescription();

	/**
	 * Returns the version of the plugin.
	 * 
	 * @return String
	 */
	String getVersion();
}
