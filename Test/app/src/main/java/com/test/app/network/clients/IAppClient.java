package com.test.app.network.clients;

import com.test.app.model.rest.FeedResponse;

/**
 * Interfaz cliente
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public interface IAppClient {

    /**
     * This method requests to the server the updated feed
     *
     * @return Updated feed. If an error occurred, then null is returned
     */
    FeedResponse requestFeed();

}
