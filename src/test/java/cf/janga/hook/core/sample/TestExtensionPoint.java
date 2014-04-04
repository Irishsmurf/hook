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
package cf.janga.hook.core.sample;

import cf.janga.hook.core.Extension;
import cf.janga.hook.core.ExtensionPoint;
import cf.janga.hook.core.PluginException;

public class TestExtensionPoint implements ExtensionPoint<TestCoreAPI> {

	public int acceptCalled = 0;

	@Override
	public boolean canHandle(Extension<TestCoreAPI> extension) {
		return extension instanceof TestExtension;
	}

	@Override
	public void accept(Extension<TestCoreAPI> extension) throws PluginException {
		this.acceptCalled++;
	}
}
