package com.test.app.model;

import java.io.Serializable;

/**
 * Clase que representa Author
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class Author implements Serializable {

    /** Author Name **/
    private Attribute name;

    /** Author Uri **/
    private Attribute uri;

    /**
     * @return the name
     */
    public Attribute getName() {
        return name;
    }

    /**
     * @return name the name to set
     */
    public void setName(Attribute name) {
        this.name = name;
    }

    /**
     * @return the uri
     */
    public Attribute getUri() {
        return uri;
    }

    /**
     * @return uri the uri to set
     */
    public void setUri(Attribute uri) {
        this.uri = uri;
    }
}
