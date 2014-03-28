package cf.janga.hook.core;

/**
 * Super class of all plugin-related exceptions
 * 
 * @author Emerson Loureiro
 */
public final class PluginException extends Exception {

	private static final long serialVersionUID = -4675035962205807578L;

	/**
	 * Creates a new {@link PluginException}.
	 * 
	 * @param cause The cause of this exception.
	 * @param extension The extension associated with this exception.
	 */
	public PluginException(Exception cause) {
		super(cause);
	}

	public PluginException(String msg, Exception cause) {
		super(msg, cause);
	}

	public PluginException(String msg) {
		super(msg);
	}
}
