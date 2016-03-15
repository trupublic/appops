package pyramid.infra.appserver.provision;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.Inject;

public class ProvisionSettingsServiceImpl implements ProvisionSettingsService{

	private ProvisionSettingsStore settingsStore;
	Logger logger = Logger.getLogger(this.getClass().getSimpleName());
	@Inject
	public ProvisionSettingsServiceImpl(ProvisionSettingsStore store) {
		settingsStore = store ;
		
	}
	
	@Override
	public void setGlobalProvisionMode(ProvisionMode mode , String dummy)  {
		settingsStore.setCurrentGlobal(mode);
		logger.log(Level.INFO, "provision mode switched to - " + mode.toString());
	}

	@Override
	public ProvisionMode getGlobalProvisionMode(String dummy) {
		return settingsStore.getCurrentGlobal();
	}

	@Override
	public void setPathProvision(String path, ProvisionMode pro) {
		settingsStore.setPathProvisionMode(path , pro) ;
	}

	@Override
	public ProvisionMode getPathProvision(String path) {
		return settingsStore.getPathProvisionMode(path) ;

	}

}
