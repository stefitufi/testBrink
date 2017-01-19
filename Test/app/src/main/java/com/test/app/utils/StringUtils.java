package com.test.app.utils;

/**
 * String Utils class
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public final class StringUtils {

    private static final String STRING_NULL = "null";

    /** Private constructor to avoid instanes **/
    private StringUtils() {}

    /**
     * Este método ejecuta el String.format normal pero reemplaza los valores nulos por "null" This
     * method performs String.format method but every null value will be replaced to "null"
     *
     * @param string
     *         Target String
     * @param params
     *         String parametters
     *
     * @return Formatted String
     */
    public static String format(String string, Object... params) {
        for (int i = 0; i < params.length; i++) {
            if (params[i] == null) {
                params[i] = STRING_NULL;
            }
        }
        return String.format(string, params);
    }
}
