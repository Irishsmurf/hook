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
 * Extensions are the contributions that a plugin brings to a hosting
 * application.
 * 
 * @author Emerson Loureiro
 */
public interface Extension<T extends CoreAPI> {

	/**
	 * Returns the name of this extension.
	 * 
	 * @return String.
	 */
	String getName();

	/**
	 * Returns the description of this extension.
	 * 
	 * @return String
	 */
	String getDescription();

	/**
	 * Initializes this extension. This method is automatically called by the
	 * {@link SimplePluginLoader} whenever the plugin associated with the
	 * extension is loaded, and the extension initialized.
	 * 
	 * @param coreAPI
	 *            The {@link CoreAPI} of the host application this extension is
	 *            associated to.
	 * @throws PluginException
	 *             If there's any error while initializing this extension point.
	 */
	void init(T coreAPI) throws PluginException;
}
