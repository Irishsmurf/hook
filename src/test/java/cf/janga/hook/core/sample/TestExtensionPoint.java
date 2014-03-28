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
