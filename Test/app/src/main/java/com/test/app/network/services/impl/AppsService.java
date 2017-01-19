package com.test.app.network.services.impl;

import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.test.app.managers.api.IAppsManager;
import com.test.app.managers.api.IFeedsManager;
import com.test.app.model.App;
import com.test.app.model.Category;
import com.test.app.model.Feed;
import com.test.app.model.rest.FeedResponse;
import com.test.app.network.clients.IAppClient;
import com.test.app.network.services.api.IAppsService;
import com.test.app.utils.AppAttribute;
import com.test.app.utils.AttrsManager;
import com.test.app.utils.DateUtils;

import org.apache.commons.lang3.Validate;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Clase que implementa la interfaz {@link IAppsService} del servicio App
 *
 * @author <a href="mailto:stefiluna@gmail.com">Stephania Moreno</a>
 */
@Singleton
public class AppsService implements IAppsService {

    /** Feed Manager **/
    @Inject
    private IFeedsManager feedManager;

    /** App Manager **/
    @Inject
    private IAppsManager appManager;

    /** LookApp server Client **/
    @Inject
    private IAppClient appClient;

    /** This is the current Feed **/
    private Feed currentFeed;

    /** Categories List **/
    private List<Category> categories;

    /**
     * This method requests feed. The resulting feed is stored in the DB and then is returned while
     * the online feed is not modified
     *
     * @return Resulting feed. If an error occurs, then null is returned
     */
    @Override
    public Feed getFeed() {

        if (currentFeed == null) {
            List<Feed> feeds = feedManager.all();
            if (!feeds.isEmpty()) {
                currentFeed = feeds.get(0);
                currentFeed.setApps(appManager.all());
            }
        }
        FeedResponse response = appClient.requestFeed();
        if (response == null && currentFeed == null) {
            return null;
        }

        if (response != null && (currentFeed == null ||
                currentFeed != null && compareUpdateFeed(response.getFeed(), currentFeed) > 0)) {
            currentFeed = response.getFeed();
            cleanFields();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    updateFeed(currentFeed);
                }
            }).start();
        }

        return currentFeed;
    }

    /**
     * This method cleans all related fields to the Current Feed
     */
    private void cleanFields() {
        categories = null;
    }

    /**
     * This method returns all loaded categories from the Feed
     *
     * @return Returns a List of Categories. If an error occurs, then null is returned
     */
    @Override
    public List<Category> getCategories() {
        return getCategories(false);
    }

    /**
     * This method returns all loaded categories from the Feed. It is possible to force offline
     * loaded, therefore internet connection will not be required, but at least a feed must to be
     * stored in the DB. This parameter would be useful when categories are needed in Main Thread
     *
     * @param forceOffline
     *         True to load stored categories in DB. Otherwise feed would be loaded using internet
     *         connection.
     *
     * @return Returns a List of Categories. If an error occurs, then null is returned
     */
    @Override
    public List<Category> getCategories(boolean forceOffline) {
        if (categories != null) {
            return categories;
        }
        Feed feed = forceOffline ? currentFeed : getFeed();
        if (feed == null) {
            return null;
        }
        categories = new ArrayList<Category>();
        Set<Integer> categoriesSet = new HashSet<Integer>();
        for (App app : feed.getApps()) {
            int categoryId = AttrsManager.getInt(app.getCategory(), AppAttribute.ID);
            if (!categoriesSet.contains(categoryId)) {
                categoriesSet.add(categoryId);

                String label = AttrsManager.getString(app.getCategory(), AppAttribute.LABEL);
                categories.add(new Category(categoryId, label));
            }
        }

        return categories;
    }

    /**
     * This method requests a List of apps of a given category
     *
     * @param categoryId
     *         Requested Category
     *
     * @return List of apps from the requested category. If an error occurs, then null will be
     * returned
     */
    @Override
    public List<App> getAppsByCategory(int categoryId) {
        return getAppsByCategory(categoryId, -1);
    }

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
    @Override
    public List<App> getAppsByCategory(int categoryId, int limit) {

        Validate.isTrue(limit > 0 || limit == -1, "The apps limit should by grater than 0");

        if (currentFeed == null) {
            return null;
        }

        List<App> filteredApps = new ArrayList<>();
        for (App app : currentFeed.getApps()) {
            if (filteredApps.size() == limit) {
                return filteredApps;
            }

            int appCatId = AttrsManager.getInt(app.getCategory(), AppAttribute.ID);
            if (categoryId == appCatId) {
                filteredApps.add(app);
            }
        }
        return filteredApps;
    }

    /**
     * This method updates the DB with new feed
     *
     * @param newFeed
     *         The feed to be stored
     */
    private void updateFeed(Feed newFeed) {
        feedManager.deleteAll();
        appManager.deleteAll();

        feedManager.createOrUpdate(newFeed);
        for (App app : newFeed.getApps()) {
            appManager.createOrUpdate(app);
        }
    }

    /**
     * This method compares two update dates of two given feeds
     *
     * @param f1
     *         First feed
     * @param f2
     *         Second feed
     *
     * @return < 0 if {@code f1} is lesser than {@code f1}. 0 if both are the same. > 0 if {@code
     * f1} is grater than {@code f1}
     */
    private int compareUpdateFeed(Feed f1, Feed f2) {
        Date dateF1 = DateUtils.parseDate(f1.getUpdated().getLabel(), DateUtils.DEFAULT_FORMAT);
        Date dateF2 = DateUtils.parseDate(f2.getUpdated().getLabel(), DateUtils.DEFAULT_FORMAT);

        return dateF1.compareTo(dateF2);
    }

}
