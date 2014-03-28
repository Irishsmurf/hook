package cf.janga.hook.core;

/**
 * Class of exceptions related to plugin platform.
 * 
 * @author Emerson Loureiro
 * 
 */
@SuppressWarnings("serial")
public class PlatformException extends Exception {

	/**
	 * Creates a new {@code PlatformException} having the provided throwable as
	 * the cause.
	 * 
	 * @param cause The root cause of this exception.
	 */
	public PlatformException(final Throwable cause) {
		super(cause.getMessage(), cause);
	}
}
