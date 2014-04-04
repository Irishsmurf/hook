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
package cf.janga.hook.test;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

public abstract class BaseUnitTest extends TestCase {

	@Override
	@Before
	public final void setUp() {
		setupImpl();
	}

	@Override
	@After
	public final void tearDown() {
		teardownImpl();
	}

	protected void setupImpl() {
	}

	protected void teardownImpl() {
	}

	protected final String getResourcePath(String name) {
		return this.getClass().getResource(name).getPath();
	}
}
