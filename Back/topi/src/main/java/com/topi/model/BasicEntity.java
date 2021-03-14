package com.topi.model;


import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.Objects;

/**
 * Class created to be the basic entity
 * between all the entities of the system.
 *
 * @since 2021-03-14
 */

@MappedSuperclass
public class BasicEntity {

    /**
     * ID of the entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Date that the entity was registered.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    /**
     * Date that the entity was last updated.
     */
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    /**
     * If the register is deleted or not.
     */
    private boolean enabled;

    /* Constructors */

    /**
     * Constructor with all params.
     *
     * @param id         ID of the entity.
     * @param regDate    Date that the entity was registered.
     * @param updateDate Date that the entity was last updated.
     * @param enabled    If the register is deleted or not.
     */
    public BasicEntity(Long id, Date regDate, Date updateDate, boolean enabled) {
        this.id = id;
        this.regDate = regDate;
        this.updateDate = updateDate;
        this.enabled = enabled;
    }

    /**
     * Empty constructor.
     */
    public BasicEntity() {
        this.setEnabled(true);
    }

    /* Getters and Setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getRegDate() {
        return regDate;
    }

    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    /* Hash Code and Equals methods */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicEntity that = (BasicEntity) o;
        return enabled == that.enabled && id.equals(that.id) && regDate.equals(that.regDate) && updateDate.equals(that.updateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, regDate, updateDate, enabled);
    }

    /* Pre Persist and Pre Update methods */

    /**
     * Method that auto sets the date of register of the entity.
     */
    @PrePersist
    private void autoRegDate() {
        this.setRegDate(new Date());
    }

    /**
     * Method that auto sets the date of last updated of the entity.
     */
    @PreUpdate
    private void autoUpdateDate(){
        this.setUpdateDate(new Date());
    }

    /* To String */

    /**
     * To String method.
     *
     * @return {@link String}. String
     * containing all the data of the entity.
     */
    @Override
    public String toString() {
        return "BasicEntity{" +
                "id=" + id +
                ", regDate=" + regDate +
                ", updateDate=" + updateDate +
                ", enabled=" + enabled +
                '}';
    }
}
