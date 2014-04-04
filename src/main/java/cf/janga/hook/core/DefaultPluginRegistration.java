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

class DefaultPluginRegistration extends AbstractPluginInformation implements PluginRegistration {

	private final Optional<String> loadingError;
	private final String pluginClass;
	private final String pluginFileName;

	public DefaultPluginRegistration(String name, String description, String version, String pluginClass, String pluginFileName) {
		super(name, description, version);
		this.loadingError = Optional.absent();
		this.pluginClass = pluginClass;
		this.pluginFileName = pluginFileName;
	}

	public DefaultPluginRegistration(String pluginFileName, String pluginClass, String loadingError) {
		super("", "", "");
		this.loadingError = Optional.of(loadingError);
		this.pluginClass = pluginClass;
		this.pluginFileName = pluginFileName;
	}

	@Override
	public Optional<String> getLoadingError() {
		return this.loadingError;
	}

	@Override
	public String getPluginClass() {
		return this.pluginClass;
	}

	@Override
	public String getPluginFilePath() {
		return this.pluginFileName;
	}
}
