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

import java.util.ArrayList;
import java.util.List;

import cf.janga.hook.core.Extension;
import cf.janga.hook.core.Plugin;

public class TestPlugin implements Plugin<TestCoreAPI> {

	private final List<Extension<TestCoreAPI>> extensions;

	public TestPlugin() {
		this.extensions = new ArrayList<Extension<TestCoreAPI>>();
	}

	public TestPlugin(List<Extension<TestCoreAPI>> extensions) {
		this.extensions = extensions;
	}

	@Override
	public List<Extension<TestCoreAPI>> getExtensions() {
		return extensions;
	}

	@Override
	public String getName() {
		return "Test plugin";
	}

	@Override
	public String getDescription() {
		return "A plugin for tests";
	}

	@Override
	public String getVersion() {
		return "1.0";
	}
}
