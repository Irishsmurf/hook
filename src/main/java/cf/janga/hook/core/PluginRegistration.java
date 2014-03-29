package cf.janga.hook.core;

import com.google.common.base.Optional;

public interface PluginRegistration extends PluginInformation {

	String getPluginClass();

	Optional<String> getLoadingError();

	String getPluginFilePath();
}
