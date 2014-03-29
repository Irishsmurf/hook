package cf.janga.hook.core;

/**
 * A plugin platform is responsible for loading plugins and their extensions, as
 * well as providing them to be handled by their respective extension points.
 * The platform also keeps tracks of all loaded plugins and their states,
 * initializing and stopping them during the life cycle of an application.
 * 
 * @author Emerson Loureiro
 * 
 */
public interface PluginPlatform {

	/**
	 * Initializes this plugin platform.
	 * 
	 * @throws PlatformException
	 *             If there's any error while initializing this platform.
	 */
	void init() throws PlatformException;

	/**
	 * Loads plugins found in the path provided. If the path is a folder, all
	 * proper plugin files will be read and the plugins loaded. If the path
	 * refers to a single plugin file, then only its plugin will be loaded.
	 * 
	 * @param path
	 *            The path from where the plugins should be loaded.
	 * @param application
	 *            The application where the plugins to be loaded will be hosted.
	 * @throws PlatformException
	 *             If there's any error while loading the plugin.
	 */
	<T extends CoreAPI> boolean loadPlugins(String path,
			HostApplication<T> application) throws PlatformException;

	/**
	 * Stop this plugin platform.
	 * 
	 * @throws PlatformException
	 *             If there's any error while stopping this platform.
	 */
	void stop() throws PlatformException;

	/**
	 * Returns the plugin registry of this platform. The plugin registry should
	 * always be available, even if no plugins are loaded.
	 * 
	 * @return {@link PluginRegistry}
	 */
	PluginRegistry getPluginRegistry();
}
