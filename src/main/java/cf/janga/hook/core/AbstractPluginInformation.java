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
 * An abstract implementation of {@link PluginInformation}, for reuse purposes.
 * 
 * @author Emerson Loureiro
 * 
 */
public abstract class AbstractPluginInformation implements PluginInformation {

	private final String name;
	private final String description;
	private final String version;

	public AbstractPluginInformation(String name, String description, String version) {
		this.name = name;
		this.description = description;
		this.version = version;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getDescription() {
		return this.description;
	}

	@Override
	public String getVersion() {
		return this.version;
	}
}
