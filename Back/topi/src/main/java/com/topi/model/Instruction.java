package com.topi.model;

import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;
import java.util.Objects;

/**
 * Class created to be the Model of Instructions.
 *
 * @since 2021-03-14
 */

@Entity(name = "instructions")
@Where(clause = "enabled = true")
public class Instruction extends BasicEntity {

    /**
     * String explaining the instruction.
     */
    @Column(nullable = false, length = 150)
    private String description;

    /**
     * Number that orders the steps in the meal.
     */
    @Column(nullable = false)
    private Integer step;

    /**
     * Time that this step will take/require.
     */
    @Column
    private Float stepTime;

    /* Constructors */

    /**
     * Constructor with all params.
     *
     * @param id          ID of the entity.
     * @param regDate     Date that the entity was registered.
     * @param updateDate  Date that the entity was last updated.
     * @param enabled     If the register is deleted or not.
     * @param description String explaining the instruction.
     * @param step        Number that orders the steps in the meal.
     * @param stepTime    Time that this step will take/require.
     */
    public Instruction(Long id, Date regDate, Date updateDate, boolean enabled, String description, Integer step,
                       Float stepTime) {
        super(id, regDate, updateDate, enabled);
        this.description = description;
        this.step = step;
        this.stepTime = stepTime;
    }

    /**
     * Constructor with this class' only params
     *
     * @param description String explaining the instruction.
     * @param step        Number that orders the steps in the meal.
     * @param stepTime    Time that this step will take/require.
     */
    public Instruction(String description, Integer step, Float stepTime) {
        this.description = description;
        this.step = step;
        this.stepTime = stepTime;
    }

    /**
     * Empty constructor.
     */
    public Instruction() {
        super.setEnabled(true);
    }

    /* Getters and Setters */

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStep() {
        return step;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Float getStepTime() {
        return stepTime;
    }

    public void setStepTime(Float stepTime) {
        this.stepTime = stepTime;
    }

    /* Hash Code and Equals methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Instruction that = (Instruction) o;
        return description.equals(that.description) && step.equals(that.step)
                && Objects.equals(stepTime, that.stepTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, step, stepTime);
    }

    /* To String */

    /**
     * To String method.
     *
     * @return {@link String}. String containing the actual object values.
     */
    @Override
    public String toString() {
        return "Instruction{" +
                "description='" + description + '\'' +
                ", step=" + step +
                ", stepTime=" + stepTime +
                '}';
    }
}
