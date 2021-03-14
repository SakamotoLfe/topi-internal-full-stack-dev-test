package com.topi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * Class created to save the media of Meals.
 *
 * @since 2021-01-05
 */

@Entity(name = "medias")
public class Media extends BasicEntity {

    /**
     * Media's type.
     */
    @Column(nullable = false, length = 10)
    private String type;

    /**
     * Media's path.
     */
    @Column(nullable = false, length = 1024)
    private String path;

    /**
     * Media's size.
     */
    @Column(nullable = false)
    private float sizeKb;

    /**
     * Media's name.
     */
    @Column(nullable = false)
    private String name;

    /**
     * Media's bytes.
     * Used only to return
     * the bytes of a media
     * with all its data.
     */
    @Transient
    private byte[] bytes;

    /* Constructors */

    /**
     * Constructor with all params.
     *
     * @param id         ID of the entity.
     * @param regDate    Date that the entity was registered.
     * @param updateDate Date that the entity was last updated.
     * @param enabled    If the register is deleted or not.
     * @param type       Media's type.
     * @param path       Media's path.
     * @param sizeKb     Media's size.
     * @param name       Media's name.
     * @param bytes      Media's bytes.
     */
    public Media(Long id, Date regDate, Date updateDate, boolean enabled, String type, String path, float sizeKb,
                 String name, byte[] bytes) {
        super(id, regDate, updateDate, enabled);
        this.type = type;
        this.path = path;
        this.sizeKb = sizeKb;
        this.name = name;
        this.bytes = bytes;
    }

    /**
     * Constructor with this class' only params
     *
     * @param type   Media's type.
     * @param path   Media's path.
     * @param sizeKb Media's size.
     * @param name   Media's name.
     * @param bytes  Media's bytes.
     */
    public Media(String type, String path, float sizeKb, String name, byte[] bytes) {
        this.type = type;
        this.path = path;
        this.sizeKb = sizeKb;
        this.name = name;
        this.bytes = bytes;
    }

    /**
     * Empty constructor
     */
    public Media() {
        super.setEnabled(true);
    }

    /* Getters and Setters */

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public float getSizeKb() {
        return sizeKb;
    }

    public void setSizeKb(float sizeKb) {
        this.sizeKb = sizeKb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getBytes() {
        return bytes;
    }

    public void setBytes(byte[] bytes) {
        this.bytes = bytes;
    }

    /* Hash Code and Equals methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Media media = (Media) o;
        return Float.compare(media.sizeKb, sizeKb) == 0 && type.equals(media.type) && path.equals(media.path)
                && name.equals(media.name) && Arrays.equals(bytes, media.bytes);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), type, path, sizeKb, name);
        result = 31 * result + Arrays.hashCode(bytes);
        return result;
    }

    /* To String */

    /**
     * To String method.
     *
     * @return {@link String}. String containing the actual object values.
     */
    @Override
    public String toString() {
        return "Media{" +
                "type='" + type + '\'' +
                ", path='" + path + '\'' +
                ", sizeKb=" + sizeKb +
                ", name='" + name + '\'' +
                ", bytes=" + Arrays.toString(bytes) +
                '}';
    }
}
