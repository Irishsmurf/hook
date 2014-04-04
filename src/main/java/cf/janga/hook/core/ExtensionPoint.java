package cf.janga.hook.core;

/**
 * Extension points are points in an application which can be extended with
 * extensions provided by plugins.
 * 
 * @author Emerson Loureiro
 * 
 */
public interface ExtensionPoint<T extends CoreAPI> {

	/**
	 * Checks whether this extension point can handle the provided extension or
	 * not.
	 * 
	 * @param extension
	 *            The extension to be checked.
	 * @return True if this extension point can handle the extension and false
	 *         otherwise.
	 */
	boolean canHandle(Extension<T> extension);

	/**
	 * Asks this extension point to accept this extension, and take care of
	 * hooking it to the right spot in the hosting application.
	 * 
	 * @param extension
	 *            The extension to be accepted by this extension point.
	 * @throws PluginException
	 *             If there's any error when handling the extension.
	 */
	void accept(Extension<T> extension) throws PluginException;
}
