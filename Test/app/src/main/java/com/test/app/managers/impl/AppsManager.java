package com.test.app.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.test.app.managers.api.IAppsManager;
import com.test.app.model.App;
import com.test.app.persistence.DatabaseHelper;

import java.sql.SQLException;

/**
 * Clase de la implementacion IAppsManager
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
@Singleton
public class AppsManager extends CrudManager<App, Integer> implements IAppsManager {

    /**
     * This is the main constructor of the CrudManager
     *
     * @param helper
     *         The DBHelper
     *
     * @throws SQLException
     *         If there's an error creating the Entity's DAO
     */
    @Inject
    public AppsManager(DatabaseHelper helper) throws SQLException {
        super(helper, App.class);
    }
}
