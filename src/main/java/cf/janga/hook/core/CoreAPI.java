package cf.janga.hook.core;

/**
 * The Core API defines which parts of an application are made available to
 * plugins and their extensions. More precisely, this is meant to be provided to
 * an {@link Extension} upon its instantiation, allowing it to access the API
 * that the application is providing.
 * 
 * @author Emerson Loureiro
 */
public interface CoreAPI {

	/**
	 * Returns the version of this core API, for checking compatibility among
	 * extensions.
	 * 
	 * @return String.
	 */
	String getVersion();
}
