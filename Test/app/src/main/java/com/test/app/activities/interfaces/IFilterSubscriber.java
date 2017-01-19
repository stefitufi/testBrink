package com.test.app.activities.interfaces;

/**
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public interface IFilterSubscriber {

    /**
     * This method will be called when the filter is changed
     *
     * @param s
     *         The new filter request
     */
    void filter(CharSequence s);

}
