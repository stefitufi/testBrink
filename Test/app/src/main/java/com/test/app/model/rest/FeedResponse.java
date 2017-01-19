package com.test.app.model.rest;


import com.test.app.model.Feed;

/**
 * Clase tipo Feed que contiene la informacion
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class FeedResponse {

    /** Expected feed response **/
    private Feed feed;

    /**
     * @return the feed
     */
    public Feed getFeed() {
        return feed;
    }

    /**
     * @return feed the feed to set
     */
    public void setFeed(Feed feed) {
        this.feed = feed;
    }
}
