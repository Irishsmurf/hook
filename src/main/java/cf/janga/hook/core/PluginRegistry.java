package cf.janga.hook.core;

import java.util.Collection;

/**
 * The plugin registry keeps runtime information about each plugin loaded by its
 * respective {@link PluginPlatform}.
 * 
 * @author Emerson Loureiro
 * 
 */
public interface PluginRegistry {

	/**
	 * Adds a new plugin registration to the registry.
	 * 
	 * @param registration
	 *            Registration containing information about a given plugin
	 */
	void register(PluginRegistration registration);

	/**
	 * Returns all registrations against this registry.
	 * 
	 * @return A List of {@link PluginRegistration}
	 */
	Collection<PluginRegistration> getRegistrations();
}
