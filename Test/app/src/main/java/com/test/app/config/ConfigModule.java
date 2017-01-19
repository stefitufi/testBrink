package com.test.app.config;

import com.google.inject.AbstractModule;
import com.test.app.managers.api.IAppsManager;
import com.test.app.managers.api.IFeedsManager;
import com.test.app.managers.impl.AppsManager;
import com.test.app.managers.impl.FeedsManager;
import com.test.app.network.clients.AppClient;
import com.test.app.network.clients.IAppClient;
import com.test.app.network.services.api.IAppsService;
import com.test.app.network.services.impl.AppsService;

/**
 * Clase dependencia RoboGuice
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class ConfigModule extends AbstractModule {

    @Override
    protected void configure() {
        bindServices();
        bindManagers();
        bindOthers();
    }

    /**
     * This method binds all services
     */
    private void bindServices() {
        bind(IAppsService.class).to(AppsService.class);
    }

    /**
     * This method binds all managers
     */
    private void bindManagers() {
        bind(IFeedsManager.class).to(FeedsManager.class);
        bind(IAppsManager.class).to(AppsManager.class);
    }

    /**
     * This method binds other classes, such as clients and internal classes
     */
    private void bindOthers() {
        bind(IAppClient.class).to(AppClient.class);
    }
}
