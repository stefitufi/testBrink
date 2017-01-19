package com.test.app.model;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

/**
 * Clase que representa App
 *
 * @author <a href="stefiluna@gmail.com">Stephania Moreno V.</a>
 */
public class App implements Serializable {

    /** Local ID fot DB **/
    @DatabaseField(id = true)
    private Integer localId;

    /** App Id **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute id;

    /** App name **/
    @SerializedName("im:name")
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute name;

    /** App images **/
    @SerializedName("im:image")
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute[] images;

    /** App summary **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute summary;

    /** App price **/
    @SerializedName("im:price")
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute price;

    /** App Content Type **/
    @SerializedName("im:contentType")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute contentType;

    /** App Rights **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute rights;

    /** App Title **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute title;

    /** App Link **/
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute link;

    /** App Artist **/
    @SerializedName("im:artist")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute artist;

    /** App Category **/
    @DatabaseField(canBeNull = false, dataType = DataType.SERIALIZABLE)
    private Attribute category;

    /** App Release Date **/
    @SerializedName("im:releaseDate")
    @DatabaseField(dataType = DataType.SERIALIZABLE)
    private Attribute releaseDate;

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
     * @return the images
     */
    public Attribute[] getImages() {
        return images;
    }

    /**
     * @return images the images to set
     */
    public void setImages(Attribute[] images) {
        this.images = images;
    }

    /**
     * @return the summary
     */
    public Attribute getSummary() {
        return summary;
    }

    /**
     * @return summary the summary to set
     */
    public void setSummary(Attribute summary) {
        this.summary = summary;
    }

    /**
     * @return the price
     */
    public Attribute getPrice() {
        return price;
    }

    /**
     * @return price the price to set
     */
    public void setPrice(Attribute price) {
        this.price = price;
    }

    /**
     * @return the contentType
     */
    public Attribute getContentType() {
        return contentType;
    }

    /**
     * @return contentType the contentType to set
     */
    public void setContentType(Attribute contentType) {
        this.contentType = contentType;
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
     * @return the link
     */
    public Attribute getLink() {
        return link;
    }

    /**
     * @return link the link to set
     */
    public void setLink(Attribute link) {
        this.link = link;
    }

    /**
     * @return the artist
     */
    public Attribute getArtist() {
        return artist;
    }

    /**
     * @return artist the artist to set
     */
    public void setArtist(Attribute artist) {
        this.artist = artist;
    }

    /**
     * @return the category
     */
    public Attribute getCategory() {
        return category;
    }

    /**
     * @return category the category to set
     */
    public void setCategory(Attribute category) {
        this.category = category;
    }

    /**
     * @return the releaseDate
     */
    public Attribute getReleaseDate() {
        return releaseDate;
    }

    /**
     * @return releaseDate the releaseDate to set
     */
    public void setReleaseDate(Attribute releaseDate) {
        this.releaseDate = releaseDate;
    }
}
