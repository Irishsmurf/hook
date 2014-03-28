package cf.janga.hook.core;

import java.util.List;

/**
 * A piece of software that can contribute with extensions to a host
 * application.
 * 
 * @author Emerson Loureiro
 */
public interface Plugin<T extends CoreAPI> {

	/**
	 * Returns all the extensions that this plugin provides.
	 * 
	 * @return a {@link List}.
	 */
	List<Extension<T>> getExtensions();

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
