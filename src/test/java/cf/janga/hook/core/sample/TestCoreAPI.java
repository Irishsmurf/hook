package cf.janga.hook.core.sample;

import cf.janga.hook.core.CoreAPI;

public class TestCoreAPI implements CoreAPI {

	@Override
	public String getVersion() {
		return "1.0";
	}
}
