package com.topi.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Class created to be the Model for Categories.
 *
 * @since 2021-03-14
 */

@Entity(name = "categories")
public class Category extends BasicEntity {

    /**
     * Name of the category.
     */
    @Column(length = 50, nullable = false)
    private String name;

    /**
     * Description of the category.
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
     * @param name        Name of the category.
     * @param description Description of the category.
     */
    public Category(Long id, Date regDate, Date updateDate, boolean enabled, String name, String description) {
        super(id, regDate, updateDate, enabled);
        this.name = name;
        this.description = description;
    }

    /**
     * Constructor with this class' only params
     *
     * @param name        Name of the category.
     * @param description Description of the category.
     */
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Empty constructor.
     */
    public Category() {
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
        Category category = (Category) o;
        return name.equals(category.name) && Objects.equals(description, category.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, description);
    }

    /* To String */

    /**
     * To String method.
     * @return {@link String}. String containing the actual object values.
     */
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
