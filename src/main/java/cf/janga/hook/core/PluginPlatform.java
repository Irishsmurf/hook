package cf.janga.hook.core;

/**
 * A plugin platform is responsible for loading plugins and their extensions and
 * managing their life-cycle. It also ensures all extensions are handed to their
 * corresponding extension point to be handled. The platform also keeps tracks
 * of all loaded plugins and their states via a {@link PluginRegistry}.
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
	 * jar files will be read. The platform assumes, in this case, that all jar
	 * files correspond to a plugin, and will indeed treat them all as plugin
	 * jar files. If the jar file is intead an ordinary jar file, an error will
	 * be thrown and a record of that kept on this platforms
	 * {@link PluginRegistry}. If the path refers to a single jar file, on the
	 * other hand, then only that file will be loaded.
	 * 
	 * @param path
	 *            The path from where the plugins should be loaded from, or the
	 *            path to the jar file of a plugin.
	 * @param application
	 *            The application where the plugins to be loaded will be hosted.
	 * @param <T>
	 *            The type of the {@link CoreAPI} associated with the host
	 *            application.
	 * @return True if all plugins are loaded successfuly and false otherwise.
	 * @throws PlatformException
	 *             If there's any error while loading the plugins.
	 */
	<T extends CoreAPI> boolean loadPlugins(String path, HostApplication<T> application) throws PlatformException;

	/**
	 * Stops this plugin platform.
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
