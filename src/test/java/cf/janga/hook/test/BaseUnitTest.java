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
