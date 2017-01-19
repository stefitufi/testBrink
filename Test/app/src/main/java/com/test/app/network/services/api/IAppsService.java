package com.test.app.network.services.api;

import com.test.app.model.App;
import com.test.app.model.Category;
import com.test.app.model.Feed;

import java.util.List;

/**
 * Interfaz del servicio App
 *
 * @author <a href="mailto:stefiluna@gmail.com">Stephania Moreno</a>
 */
public interface IAppsService {

    /**
     * This method requests feed. The resulting feed is stored in the DB and then is returned while
     * the online feed is not modified
     *
     * @return Resulting feed. If an error occurs, then null is returned
     */
    Feed getFeed();

    /**
     * This method returns all loaded categories from the Feed. If a feed has not been loaded,
     * therefore it is requested from the server. Internet connection would be needed.
     *
     * @return Returns a List of Categories. If an error occurs, then null is returned
     */
    List<Category> getCategories();

    /**
     * This method returns all loaded categories from the Feed. It is possible to force offline
     * loaded, therefore internet connection will not be required, but at least a feed must to be
     * stored in the DB. This parameter would be useful when categories are needed in Main Thread
     *
     * @return Returns a List of Categories. If an error occurs, then null is returned
     */
    List<Category> getCategories(boolean forceOffline);

    /**
     * This method requests a List of apps of a given category
     *
     * @param categoryId
     *         Requested Category
     *
     * @return List of apps from the requested category. If an error occurs, then null will be
     * returned
     */
    List<App> getAppsByCategory(int categoryId);

    /**
     * This method requests a List of apps of a given category and a limit of it.
     *
     * @param categoryId
     *         Requested Category
     * @param limit
     *         Limit of apps. -1 will return all apps from the category
     *
     * @return List of apps from the requested category. The amount of returned apps are given from
     * the limit, if the limit is grater than the total amount of apps, then all the apps are
     * returned. If an error occurs, then null will be returned
     */
    List<App> getAppsByCategory(int categoryId, int limit);

}
