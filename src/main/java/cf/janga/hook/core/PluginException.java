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
	 * @param cause
	 *            The cause of this exception.
	 */
	public PluginException(Exception cause) {
		super(cause);
	}

	/**
	 * Creates a new {@link PluginException}.
	 * 
	 * @param msg
	 *            The message for this exception
	 * @param cause
	 *            The cause of this exception.
	 */
	public PluginException(String msg, Exception cause) {
		super(msg, cause);
	}

	/**
	 * Creates a new {@link PluginException}.
	 * 
	 * @param msg
	 *            The message for this exception
	 */
	public PluginException(String msg) {
		super(msg);
	}
}
