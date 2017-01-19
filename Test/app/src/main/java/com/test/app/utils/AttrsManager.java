package com.test.app.utils;

import com.test.app.model.Attribute;

import java.util.Date;

/**
 * Esta clase permite leer los atributos de una manera fácil
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public final class AttrsManager {

    /** Private constructor to avoid instances **/
    private AttrsManager() {}

    /**
     * This method returns the label field of {@link Attribute}
     *
     * @return The label. Returns null if it does not exist
     */
    public static String getLabel(Attribute attribute) {
        return validateNotNull(attribute) ? attribute.getLabel() : null;
    }

    /**
     * This method returns the String related to the given attribute
     *
     * @param attribute
     *         The parent of the attribute
     * @param attr
     *         The parameter to be extracted
     *
     * @return The requested attribute. Null if there is no the requested attribute
     */
    public static String getString(Attribute attribute, AppAttribute attr) {
        return validateNotNull(attribute) && validateNotNull(attribute.getAttrs()) ?
                attribute.getAttrs().get(attr.getAttribute()) : null;
    }

    /**
     * This method gets the int value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, 0 is returned
     */
    public static int getInt(Attribute attribute, AppAttribute attr) {
        return getInt(attribute, attr, 0);
    }

    /**
     * This method gets the int value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static int getInt(Attribute attribute, AppAttribute attr, int defaultValue) {
        String value = getString(attribute, attr);
        return validateNotNull(value) ? Integer.parseInt(value) : defaultValue;
    }

    /**
     * This method gets the float value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, 0 is returned
     */
    public static float getFloat(Attribute attribute, AppAttribute attr) {
        return getFloat(attribute, attr, 0.0F);
    }

    /**
     * This method gets the float value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static float getFloat(Attribute attribute, AppAttribute attr, float defaultValue) {
        String value = getString(attribute, attr);
        return validateNotNull(value) ? Float.parseFloat(value) : defaultValue;
    }

    /**
     * This method gets the double value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, 0.0 is returned
     */
    public static double getDouble(Attribute attribute, AppAttribute attr) {
        return getDouble(attribute, attr, 0.0D);
    }

    /**
     * This method gets the double value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static double getDouble(Attribute attribute, AppAttribute attr, double defaultValue) {
        String value = getString(attribute, attr);
        return validateNotNull(value) ? Double.parseDouble(value) : defaultValue;
    }

    /**
     * This method gets the {@link Date} value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     *
     * @return The attribute value. If the attribute does not exist, null is returned
     */
    public static Date getDate(Attribute attribute, AppAttribute attr, String dateFormat) {
        return getDate(attribute, attr, dateFormat, null);
    }

    /**
     * This method gets the {@link Date} value of a given attribute
     *
     * @param attribute
     *         Parent of the requested attribute
     * @param attr
     *         Attribute that is being requested
     * @param defaultValue
     *         The default value if the attribute does not exist
     *
     * @return The attribute value or the given default value if the attribute does not exist
     */
    public static Date getDate(Attribute attribute, AppAttribute attr, String dateFormat,
                               Date defaultValue) {
        String value = getString(attribute, attr);
        return validateNotNull(value) ? DateUtils.parseDate(value, dateFormat) : defaultValue;
    }

    /**
     * This method checks if is the given object is not null
     *
     * @param obj
     *         Object to be validated
     *
     * @return True if it is valid. Otherwise returns false
     */
    private static boolean validateNotNull(Object obj) {
        return obj != null;
    }

}
