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
 * A piece of software that can contribute with extensions to a host
 * application.
 * 
 * @author Emerson Loureiro
 */
public interface Plugin<T extends CoreAPI> extends PluginInformation {

	/**
	 * Returns all the extensions that this plugin provides.
	 * 
	 * @return a {@link List}.
	 */
	List<Extension<T>> getExtensions();
}
