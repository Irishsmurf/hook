package cf.janga.hook.core.sample;

import cf.janga.hook.core.Plugin;
import cf.janga.hook.core.PluginFile;

@SuppressWarnings("unused")
public class TestPluginFile implements PluginFile<TestCoreAPI> {

	private final Plugin<TestCoreAPI> plugin;

	public TestPluginFile(Plugin<TestCoreAPI> plugin) {
		this.plugin = plugin;
	}

	@Override
	public String getFilePath() {
		return "";
	}

	@Override
	public long getSize() {
		return 0;
	}

	@Override
	public String getFileName() {
		return "FileName";
	}

	@Override
	public String getPluginClass() {
		// TODO Auto-generated method stub
		return null;
	}
}
