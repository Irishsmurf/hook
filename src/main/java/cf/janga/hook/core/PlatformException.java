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
 * Class of exceptions related to plugin platform.
 * 
 * @author Emerson Loureiro
 * 
 */
@SuppressWarnings("serial")
public class PlatformException extends Exception {

	/**
	 * Creates a new {@code PlatformException} having the provided throwable as
	 * the cause.
	 * 
	 * @param cause
	 *            The root cause of this exception.
	 */
	public PlatformException(final Throwable cause) {
		super(cause.getMessage(), cause);
	}
}
