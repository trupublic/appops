package org.appops.webserver;

import org.apache.shiro.config.Ini;
import org.apache.shiro.guice.ShiroModule;
import org.apache.shiro.realm.text.IniRealm;

import com.google.inject.Provides;

public class AppOpsShiroWebModule extends ShiroModule {

	@Override
	protected void configureShiro() {

		try {
			bindRealm().toConstructor(IniRealm.class.getConstructor(Ini.class));
		} catch (NoSuchMethodException e) {
			addError(e);
		}
	}

	@Provides
	Ini loadShiroIni() {
		return Ini.fromResourcePath("classpath:shiro.ini");
	}

}
