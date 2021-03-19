package com.topi.model;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Class created to be the Model of Ingredients.
 *
 * @since 2021-03-14
 */

@Entity(name = "ingredients")
@Where(clause = "enabled = true")
public class Ingredient extends BasicEntity {

    /**
     * Name of the ingredient.
     */
    @Column(nullable = false, length = 150)
    private String name;

    /**
     * Measure that the ingredient needs to be add.
     */
    @Column(nullable = false, length = 100)
    private String measure;

    /* Constructors */

    /**
     * Constructor with all params.
     *
     * @param id         ID of the entity.
     * @param regDate    Date that the entity was registered.
     * @param updateDate Date that the entity was last updated.
     * @param enabled    If the register is deleted or not.
     * @param name       Name of the ingredient.
     * @param measure    Measure that the ingredient needs to be add.
     */
    public Ingredient(Long id, Date regDate, Date updateDate, boolean enabled, String name, String measure) {
        super(id, regDate, updateDate, enabled);
        this.name = name;
        this.measure = measure;
    }

    /**
     * Constructor with this class' only params
     *
     * @param name    Name of the ingredient.
     * @param measure Measure that the ingredient needs to be add.
     */
    public Ingredient(String name, String measure) {
        this.name = name;
        this.measure = measure;
    }

    /**
     * Empty constructor.
     */
    public Ingredient() {
        super.setEnabled(true);
    }

    /* Getters and Setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    /* Hash Code and Equals methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Ingredient that = (Ingredient) o;
        return name.equals(that.name) && measure.equals(that.measure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), name, measure);
    }

    /* To String */

    /**
     * To String method.
     *
     * @return {@link String}. String containing the actual object values.
     */
    @Override
    public String toString() {
        return "Ingredient{" +
                "name='" + name + '\'' +
                ", measure='" + measure + '\'' +
                '}';
    }
}
