package cf.janga.hook.core;

import java.util.List;

/**
 * A host application is an application that defines extension points and
 * requires a plugin loader to load and initialize plugins and extensions at
 * runtime.
 * 
 * @author Emerson Loureiro
 */
public interface HostApplication<T extends CoreAPI> {

	/**
	 * Returns all extension points provided by this application.
	 * 
	 * @return A List of {@link ExtensionPoint}
	 */
	List<ExtensionPoint<T>> getExtensionPoints();

	/**
	 * Returns the core API the this host application provides to it's
	 * extensions.
	 * 
	 * @return CoreAPI.
	 */
	T getCoreAPI();
}