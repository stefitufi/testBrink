package com.test.app.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Map;

/**
 * Clase auxiliar
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class Attribute implements Serializable {

    /** Attribute Label **/
    private String label;

    /** Attribute Attributes **/
    @SerializedName("attributes")
    private Map<String, String> attrs;

    /**
     * @return the label
     */
    public String getLabel() {
        return label;
    }

    /**
     * @return label the label to set
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return the attrs
     */
    public Map<String, String> getAttrs() {
        return attrs;
    }

    /**
     * @return attrs the attrs to set
     */
    public void setAttrs(Map<String, String> attrs) {
        this.attrs = attrs;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Attribute{");
        sb.append("label='").append(label).append('\'');
        sb.append(", attrs=").append(attrs);
        sb.append('}');
        return sb.toString();
    }
}
