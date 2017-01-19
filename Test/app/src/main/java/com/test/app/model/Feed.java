package com.test.app.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.util.List;

/**
 * Representacion de la clase Feed
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class Feed {

    /** Local DB Id **/
    @DatabaseField(generatedId = true)
    private Integer localId;

    /** Feed Id **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute id;

    /** Feed author **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Author author;

    /** Feed apps. Note: Stored in other table **/
    @SerializedName("entry")
    private List<App> apps;

    /** Feed updated **/
    @DatabaseField(dataType = DataType.SERIALIZABLE, canBeNull = false)
    private Attribute updated;

    /** Feed rights **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute rights;

    /** Feed title **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute title;

    /** Feed Icon **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute icon;

    /** Feed links **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute[] link;

    /**
     * @return the localId
     */
    public Integer getLocalId() {
        return localId;
    }

    /**
     * @return localId the localId to set
     */
    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    /**
     * @return the id
     */
    public Attribute getId() {
        return id;
    }

    /**
     * @return id the id to set
     */
    public void setId(Attribute id) {
        this.id = id;
    }

    /**
     * @return the author
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * @return author the author to set
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * @return the apps
     */
    public List<App> getApps() {
        return apps;
    }

    /**
     * @return apps the apps to set
     */
    public void setApps(List<App> apps) {
        this.apps = apps;
    }

    /**
     * @return the updated
     */
    public Attribute getUpdated() {
        return updated;
    }

    /**
     * @return updated the updated to set
     */
    public void setUpdated(Attribute updated) {
        this.updated = updated;
    }

    /**
     * @return the rights
     */
    public Attribute getRights() {
        return rights;
    }

    /**
     * @return rights the rights to set
     */
    public void setRights(Attribute rights) {
        this.rights = rights;
    }

    /**
     * @return the title
     */
    public Attribute getTitle() {
        return title;
    }

    /**
     * @return title the title to set
     */
    public void setTitle(Attribute title) {
        this.title = title;
    }

    /**
     * @return the icon
     */
    public Attribute getIcon() {
        return icon;
    }

    /**
     * @return icon the icon to set
     */
    public void setIcon(Attribute icon) {
        this.icon = icon;
    }

    /**
     * @return the link
     */
    public Attribute[] getLink() {
        return link;
    }

    /**
     * @return link the link to set
     */
    public void setLink(Attribute[] link) {
        this.link = link;
    }
}
