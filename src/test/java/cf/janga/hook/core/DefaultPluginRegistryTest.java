package cf.janga.hook.core;

import java.util.Collection;

import cf.janga.hook.test.BaseUnitTest;

public class DefaultPluginRegistryTest extends BaseUnitTest {
	public void testRegisterWhenMultiplePluginsWhenSameClassAreRegistered() {
		DefaultPluginRegistry registry = new DefaultPluginRegistry();
		PluginRegistration registration = new DefaultPluginRegistration("name1", "description", "1", "org.whatever.Plugin1", "someFile.jar");
		registry.register(registration);
		registry.register(new DefaultPluginRegistration("name2", "description", "1", "org.whatever.Plugin1", "someFile2.jar"));
		Collection<PluginRegistration> registrations = registry.getRegistrations();
		PluginRegistration registeredRegistration = registrations.iterator().next();
		assertSame(registration, registeredRegistration);
	}

	public void testRegistration() {
		DefaultPluginRegistry registry = new DefaultPluginRegistry();
		registry.register(new DefaultPluginRegistration("name", "description", "1", "org.whatever.Plugin1", "someFile1.jar"));
		registry.register(new DefaultPluginRegistration("name", "description", "1", "org.whatever.Plugin1", "someFile1.jar"));
		registry.register(new DefaultPluginRegistration("name2", "description", "2", "org.whatever.Plugin2", "someFile2.jar"));
		Collection<PluginRegistration> registrations = registry.getRegistrations();
		assertEquals(2, registrations.size());
	}
}
