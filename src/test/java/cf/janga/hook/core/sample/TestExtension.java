package cf.janga.hook.core.sample;

import cf.janga.hook.core.Extension;
import cf.janga.hook.core.PluginException;

public class TestExtension implements Extension<TestCoreAPI> {

	public int initCalled = 0;

	@Override
	public String getName() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public void init(TestCoreAPI coreAPI) throws PluginException {
		this.initCalled++;
	}
}
