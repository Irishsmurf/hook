package cf.janga.hook.core.sample;

import java.util.List;

import cf.janga.hook.core.ExtensionPoint;
import cf.janga.hook.core.HostApplication;

public class TestHostApplication implements HostApplication<TestCoreAPI> {

	private final List<ExtensionPoint<TestCoreAPI>> extensions;
	private final TestCoreAPI api;

	public TestHostApplication(List<ExtensionPoint<TestCoreAPI>> extensions, TestCoreAPI coreAPI) {
		this.extensions = extensions;
		this.api = coreAPI;
	}

	@Override
	public List<ExtensionPoint<TestCoreAPI>> getExtensionPoints() {
		return this.extensions;
	}

	@Override
	public TestCoreAPI getCoreAPI() {
		return this.api;
	}
}
