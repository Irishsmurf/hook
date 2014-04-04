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
 * The Core API defines which parts of an application are made available to
 * plugins and their extensions. More precisely, this is meant to be provided to
 * an {@link Extension} upon its instantiation, allowing it to access the API
 * that the application is providing.
 * 
 * @author Emerson Loureiro
 */
public interface CoreAPI {

	/**
	 * Returns the version of this core API, for checking compatibility among
	 * extensions.
	 * 
	 * @return String.
	 */
	String getVersion();
}
