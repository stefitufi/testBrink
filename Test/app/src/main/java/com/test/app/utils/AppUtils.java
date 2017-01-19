package com.test.app.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;

/**
 * Clase utilidades
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public final class AppUtils {

    /** Private constructor to avoid instances **/
    private AppUtils() {}

    /**
     * Checks if the current device is a tablet or not
     *
     * @param context
     *         Application context
     *
     * @return True if the device is a tablet. False otherwise
     */
    public static boolean isTablet(Context context) {
        return (getScreenSize(context)) >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }

    /**
     * This method returns the device screen size, e.g., {@link Configuration#SCREENLAYOUT_SIZE_LARGE},
     * {@link Configuration#SCREENLAYOUT_SIZE_NORMAL} or {@link Configuration#SCREENLAYOUT_SIZE_SMALL}
     *
     * @param context
     *         Current context
     *
     * @return This method returns the device screen size, e.g., {@link Configuration#SCREENLAYOUT_SIZE_LARGE},
     * {@link Configuration#SCREENLAYOUT_SIZE_NORMAL} or {@link Configuration#SCREENLAYOUT_SIZE_SMALL}
     */
    public static int getScreenSize(Context context) {
        return context.getResources().getConfiguration().screenLayout &
                Configuration.SCREENLAYOUT_SIZE_MASK;
    }

    /**
     * This method checks if the current OS is grater or equals to Lollipop
     *
     * @return True if the current OS is grater or equal to Lollipop. False otherwise
     */
    public static boolean isGraterLollipop() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}
