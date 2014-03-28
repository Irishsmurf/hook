package cf.janga.hook.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cf.janga.hook.core.CoreAPI;
import cf.janga.hook.core.DefaultPluginFile;
import cf.janga.hook.core.Extension;
import cf.janga.hook.core.ExtensionPoint;
import cf.janga.hook.core.Plugin;
import cf.janga.hook.core.PluginFile;
import cf.janga.hook.core.PluginPlatform;
import cf.janga.hook.core.sample.TestCoreAPI;
import cf.janga.hook.core.sample.TestExtension;
import cf.janga.hook.core.sample.TestExtensionPoint;
import cf.janga.hook.core.sample.TestHostApplication;
import cf.janga.hook.core.sample.TestPlugin;
import cf.janga.hook.core.sample.TestPluginFile;
import cf.janga.hook.test.BaseUnitTest;

@SuppressWarnings("unchecked")
public class PluginPlatformTest extends BaseUnitTest {

	private PluginPlatform pluginPlatform;

	@Override
	protected void setupImpl() {
		this.pluginPlatform = new PluginPlatform();
	}

	public void testPluginCycle() throws Exception {
		int numberOfExtensions = 2;
		// Setting up a mocks
		// The plugin with its extensions...
		List<Extension<TestCoreAPI>> extensions = mockExtensions(numberOfExtensions);
		final Plugin<TestCoreAPI> plugin = new TestPlugin(extensions);

		// Plugin file, containing only the plugin above...
		final List<PluginFile<TestCoreAPI>> pluginFiles = new ArrayList<PluginFile<TestCoreAPI>>();
		PluginFile<TestCoreAPI> pluginFile = new TestPluginFile(plugin);
		pluginFiles.add(pluginFile);
		this.pluginPlatform = new PluginPlatform() {

			@Override
			<T extends CoreAPI> Plugin<T> loadPluginIntoClasspath(PluginFile<T> pluginFile) {
				return (Plugin<T>) plugin;
			}

			@Override
			List<PluginFile<TestCoreAPI>> loadPluginFiles(String path) {
				return pluginFiles;
			}
		};

		// The host application with its core API
		// extension points...
		TestCoreAPI coreAPI = new TestCoreAPI();
		TestExtensionPoint extensionPoint = new TestExtensionPoint();
		List<ExtensionPoint<TestCoreAPI>> extensionPoints = new ArrayList<ExtensionPoint<TestCoreAPI>>();
		extensionPoints.add(extensionPoint);
		TestHostApplication application = new TestHostApplication(extensionPoints, coreAPI);

		// Load the plugins
		assertTrue(this.pluginPlatform.loadPlugins("", application));

		// Assert the plugin/application/extension point
		// API has been called
		assertEquals(numberOfExtensions, extensionPoint.acceptCalled);
		for (int i = 0; i < numberOfExtensions; i++) {
			TestExtension extension = (TestExtension) extensions.get(i);
			assertEquals(1, extension.initCalled);
		}
	}

	public void testLoadPluginFiles_DirectoryPath() throws Exception {
		String pluginFolderPath = getResourcePath("");
		List<PluginFile<CoreAPI>> pluginFiles = this.pluginPlatform.loadPluginFiles(pluginFolderPath);
		assertEquals(2, pluginFiles.size());
		for (int i = 0; i < pluginFiles.size(); i++) {
			PluginFile<CoreAPI> pluginFile = pluginFiles.get(i);
			String pluginFileName = "pluginFile_" + (i + 1) + ".jar";
			assertEquals(pluginFileName, pluginFile.getFileName());
			assertNotNull(pluginFile.getFilePath());
			assertNotNull(pluginFile.getPluginClass());
		}
	}

	public void testLoadPluginIntoClasspath() throws Exception {
		DefaultPluginFile pluginFile = new DefaultPluginFile(new File(getResourcePath("pluginFile_1.jar")));
		Plugin<?> plugin = this.pluginPlatform.loadPluginIntoClasspath(pluginFile);
		assertNotNull(plugin);
	}

	private List<Extension<TestCoreAPI>> mockExtensions(int numberOfExtensions) {
		List<Extension<TestCoreAPI>> extensions = new ArrayList<Extension<TestCoreAPI>>(numberOfExtensions);
		for (int i = 0; i < numberOfExtensions; i++) {
			extensions.add(new TestExtension());
		}
		return extensions;
	}
}
