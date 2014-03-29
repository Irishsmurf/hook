package cf.janga.hook.core;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultPluginRegistry implements PluginRegistry {

	private final Map<String, PluginRegistration> registrationTable;

	public DefaultPluginRegistry() {
		this.registrationTable = new LinkedHashMap<String, PluginRegistration>();
	}

	@Override
	public void register(PluginRegistration registration) {
		if (!this.registrationTable.containsKey(registration.getPluginClass())) {
			this.registrationTable.put(registration.getPluginClass(),
					registration);
		}
	}

	@Override
	public Collection<PluginRegistration> getRegistrations() {
		return this.registrationTable.values();
	}
}
