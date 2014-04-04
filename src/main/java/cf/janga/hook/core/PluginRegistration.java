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

import com.google.common.base.Optional;

/**
 * Holds information of a plugin's registration against a plugin platform's
 * {@link PluginRegistry}, including loading errors.
 * 
 * @author Emerson Loureiro (emerson.loureiro@gmail.com)
 * 
 */
public interface PluginRegistration extends PluginInformation {

	/**
	 * Returns the plugin class of the plugin under this registration.
	 * 
	 * @return String
	 */
	String getPluginClass();

	/**
	 * Returns an {@link Optional} for the error loading the plugin, if any.
	 * 
	 * @return {@link Optional}
	 */
	Optional<String> getLoadingError();

	/**
	 * Returns the full path on the disk of the plugin under this registration.
	 * 
	 * @return String
	 */
	String getPluginFilePath();
}
