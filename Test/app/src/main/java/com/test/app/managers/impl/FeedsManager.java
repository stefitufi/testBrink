package com.test.app.managers.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.test.app.managers.api.IFeedsManager;
import com.test.app.model.Feed;
import com.test.app.persistence.DatabaseHelper;

import java.sql.SQLException;
/**
 * Clase implemenqcion Feedd manager
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
@Singleton
public class FeedsManager extends CrudManager<Feed, Integer> implements IFeedsManager {

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
    public FeedsManager(DatabaseHelper helper) throws SQLException {
        super(helper, Feed.class);
    }
}
