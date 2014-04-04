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

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

class DefaultPluginRegistry implements PluginRegistry {

	private final Map<String, PluginRegistration> registrationTable;

	public DefaultPluginRegistry() {
		this.registrationTable = new LinkedHashMap<String, PluginRegistration>();
	}

	@Override
	public void register(PluginRegistration registration) {
		if (!this.registrationTable.containsKey(registration.getPluginClass())) {
			this.registrationTable.put(registration.getPluginClass(), registration);
		}
	}

	@Override
	public Collection<PluginRegistration> getRegistrations() {
		return this.registrationTable.values();
	}
}
