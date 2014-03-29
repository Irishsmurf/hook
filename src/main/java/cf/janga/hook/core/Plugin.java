package cf.janga.hook.core;

import java.util.List;

/**
 * A piece of software that can contribute with extensions to a host
 * application.
 * 
 * @author Emerson Loureiro
 */
public interface Plugin<T extends CoreAPI> extends PluginInformation {

	/**
	 * Returns all the extensions that this plugin provides.
	 * 
	 * @return a {@link List}.
	 */
	List<Extension<T>> getExtensions();
}
