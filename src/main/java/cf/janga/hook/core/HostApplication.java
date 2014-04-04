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

import java.util.List;

/**
 * A host application is an application that defines extension points and
 * requires a plugin loader to load and initialize plugins and extensions at
 * runtime.
 * 
 * @author Emerson Loureiro
 */
public interface HostApplication<T extends CoreAPI> {

	/**
	 * Returns all extension points provided by this application.
	 * 
	 * @return A List of {@link ExtensionPoint}
	 */
	List<ExtensionPoint<T>> getExtensionPoints();

	/**
	 * Returns the core API the this host application provides to it's
	 * extensions.
	 * 
	 * @return CoreAPI.
	 */
	T getCoreAPI();
}