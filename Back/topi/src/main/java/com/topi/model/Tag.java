package com.topi.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Class created to be a Model for Tags.
 *
 * @since 2021-03-14
 */

@Entity(name = "tags")
public class Tag extends BasicEntity {

    /**
     * Name of the tag.
     */
    @Column(nullable = false, length = 50)
    private String name;

    /**
     * Description of the tag.
     */
    @Column(length = 150)
    private String description;

    /* Constructors */

    /**
     * Constructor with all params.
     *
     * @param id          ID of the entity.
     * @param regDate     Date that the entity was registered.
     * @param updateDate  Date that the entity was last updated.
     * @param enabled     If the register is deleted or not.
     * @param name        Name of the tag.
     * @param description Description of the tag.
     */
    public Tag(Long id, Date regDate, Date updateDate, boolean enabled, String name, String description) {
        super(id, regDate, updateDate, enabled);
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor with this class' only params
     *
     * @param name        Name of the tag.
     * @param description Description of the tag.
     */
    public Tag(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Empty constructor.
     */
    public Tag() {
        super.setEnabled(true);
    }

    /* Getters and Setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /* Hash Code and Equals methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Tag tag = (Tag) o;
        return name.equals(tag.name) && Objects.equals(description, tag.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }

    /* To String */

    /**
     * To String method.
     *
     * @return {@link String}. String containing the actual object values.
     */
    @Override
    public String toString() {
        return "Tag{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
