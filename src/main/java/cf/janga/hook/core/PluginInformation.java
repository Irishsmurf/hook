package cf.janga.hook.core;

/**
 * Holds general information about a plugin. Mostly for reuse purpose across
 * different types that hold the same information.
 * 
 * @author Emerson Loureiro
 * 
 */
public interface PluginInformation {

	/**
	 * Returns the name of this plugin.
	 * 
	 * @return String.
	 */
	String getName();

	/**
	 * Returns the description of this plugin.
	 * 
	 * @return String.
	 */
	String getDescription();

	/**
	 * Returns the version of the plugin.
	 * 
	 * @return String
	 */
	String getVersion();
}
