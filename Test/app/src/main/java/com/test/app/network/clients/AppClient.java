package com.test.app.network.clients;

import android.util.Log;

import com.google.inject.Singleton;
import com.test.app.model.rest.FeedResponse;
import com.test.app.utils.RestUtils;

/**
 * Clase que solicita operaciones básicas con el servidor
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
@Singleton
public class AppClient implements IAppClient {

    /** Tag Logs **/
    private static final String TAG_LOG = AppClient.class.getName();

    /**
     * This method requests to the server the updated feed
     *
     * @return Updated feed. If an error occurred, then null is returned
     */
    @Override
    public FeedResponse requestFeed() {
        FeedResponse response = null;

        try {
            response = RestUtils
                    .get("https://itunes.apple.com/us/rss/toppaidapplications/limit=20/json",
                            FeedResponse.class);
        } catch (Exception e) {
            Log.e(TAG_LOG, "Ha ocurrido un error haciendo la solicitud", e);
        }

        return response;
    }

}
