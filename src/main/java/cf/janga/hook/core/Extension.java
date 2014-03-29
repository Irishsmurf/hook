package cf.janga.hook.core;

/**
 * Extensions are the contributions that a plugin brings to a hosting
 * application.
 * 
 * @author Emerson Loureiro
 */
public interface Extension<T extends CoreAPI> {

	/**
	 * Returns the name of this extension.
	 * 
	 * @return String.
	 */
	String getName();

	/**
	 * Returns the description of this extension.
	 * 
	 * @return String
	 */
	String getDescription();

	/**
	 * Initializes this extension. This method is automatically called by the
	 * {@link SimplePluginPlatform} whenever the plugin associated with the extension
	 * is loaded, and the extension initialized.
	 * 
	 * @param coreAPI The {@link CoreAPI} of the host application this extension
	 *            is associated to.
	 * @throws PluginException If there's any error while initializing this
	 *             extension point.
	 */
	void init(T coreAPI) throws PluginException;
}
