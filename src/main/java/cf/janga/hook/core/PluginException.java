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
 * Super class of all plugin-related exceptions
 * 
 * @author Emerson Loureiro
 */
public final class PluginException extends Exception {

	private static final long serialVersionUID = -4675035962205807578L;

	/**
	 * Creates a new {@link PluginException}.
	 * 
	 * @param cause
	 *            The cause of this exception.
	 */
	public PluginException(Exception cause) {
		super(cause);
	}

	/**
	 * Creates a new {@link PluginException}.
	 * 
	 * @param msg
	 *            The message for this exception
	 * @param cause
	 *            The cause of this exception.
	 */
	public PluginException(String msg, Exception cause) {
		super(msg, cause);
	}

	/**
	 * Creates a new {@link PluginException}.
	 * 
	 * @param msg
	 *            The message for this exception
	 */
	public PluginException(String msg) {
		super(msg);
	}
}
