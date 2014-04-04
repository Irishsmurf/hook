package cf.janga.hook.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import cf.janga.hook.core.sample.TestCoreAPI;
import cf.janga.hook.core.sample.TestExtension;
import cf.janga.hook.core.sample.TestExtensionPoint;
import cf.janga.hook.core.sample.TestHostApplication;
import cf.janga.hook.core.sample.TestPlugin;
import cf.janga.hook.core.sample.TestPluginFile;
import cf.janga.hook.test.BaseUnitTest;

@SuppressWarnings("unchecked")
public class SimplePluginPlatformTest extends BaseUnitTest {

	private SimplePluginPlatform pluginPlatform;

	@Override
	protected void setupImpl() {
		this.pluginPlatform = new SimplePluginPlatform();
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
		this.pluginPlatform = new SimplePluginPlatform() {

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
		PluginRegistry pluginRegistry = this.pluginPlatform.getPluginRegistry();
		Collection<PluginRegistration> registrations = pluginRegistry.getRegistrations();
		assertEquals(1, registrations.size());

		// Assert the plugin/application/extension point
		// API has been called
		assertEquals(numberOfExtensions, extensionPoint.acceptCalled);
		for (int i = 0; i < numberOfExtensions; i++) {
			TestExtension extension = (TestExtension) extensions.get(i);
			assertEquals(1, extension.initCalled);
		}
	}

	public void testLoadPluginsWhenAnExceptionIsThrownForOnePlugin() throws Exception {
		// Setting up a mocks
		// The plugin with its extensions...
		List<Extension<TestCoreAPI>> extensions = mockExtensions(1);
		final Plugin<TestCoreAPI> plugin = new TestPlugin(extensions);

		// Plugin file, containing only the plugin above...
		final List<PluginFile<TestCoreAPI>> pluginFiles = new ArrayList<PluginFile<TestCoreAPI>>();
		PluginFile<TestCoreAPI> pluginFile = new TestPluginFile(plugin);
		pluginFiles.add(pluginFile);

		final String message = "Sample error message";
		this.pluginPlatform = new SimplePluginPlatform() {

			@Override
			<T extends CoreAPI> Plugin<T> loadPluginIntoClasspath(PluginFile<T> pluginFile) throws PluginException {
				throw new PluginException(message);
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
		assertFalse(this.pluginPlatform.loadPlugins("", application));
		PluginRegistry pluginRegistry = this.pluginPlatform.getPluginRegistry();
		Collection<PluginRegistration> registrations = pluginRegistry.getRegistrations();
		assertEquals(1, registrations.size());
		assertEquals(message, registrations.iterator().next().getLoadingError().get());
	}

	public void testLoadPluginFilesForDirectoryPath() throws Exception {
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

	public void testLoadPluginFilesForJarFilePath() throws Exception {
		String pluginFolderPath = getResourcePath("pluginFile_1.jar");
		List<PluginFile<CoreAPI>> pluginFiles = this.pluginPlatform.loadPluginFiles(pluginFolderPath);
		assertEquals(1, pluginFiles.size());
		PluginFile<CoreAPI> pluginFile = pluginFiles.get(0);
		assertNotNull(pluginFile.getFilePath());
		assertNotNull(pluginFile.getPluginClass());
	}

	public void testLoadPluginFilesForNonJarFilePath() throws Exception {
		String pluginFolderPath = getResourcePath("pluginFile_1.zip");
		List<PluginFile<CoreAPI>> pluginFiles = this.pluginPlatform.loadPluginFiles(pluginFolderPath);
		assertEquals(0, pluginFiles.size());
	}

	private List<Extension<TestCoreAPI>> mockExtensions(int numberOfExtensions) {
		List<Extension<TestCoreAPI>> extensions = new ArrayList<Extension<TestCoreAPI>>(numberOfExtensions);
		for (int i = 0; i < numberOfExtensions; i++) {
			extensions.add(new TestExtension());
		}
		return extensions;
	}
}
