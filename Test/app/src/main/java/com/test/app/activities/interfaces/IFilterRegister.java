package com.test.app.activities.interfaces;

/**
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public interface IFilterRegister {

    /**
     * This method registers a "subscriber" from filter actions
     *
     * @param subscriber
     *         The subscriber
     */
    void register(IFilterSubscriber subscriber);

    /**
     * This method unregister a subscriber from filter actions
     *
     * @param subscriber
     *         The subscriber to be unregistered
     */
    void unregister(IFilterSubscriber subscriber);

}
