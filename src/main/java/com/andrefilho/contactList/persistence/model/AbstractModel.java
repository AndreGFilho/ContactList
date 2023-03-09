package com.andrefilho.contactList.persistence.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import com.andrefilho.contactList.persistence.model.Model;

import java.util.Date;

/**
 * A generic model entity to be used as a base for concrete types of models
 */
@MappedSuperclass
public abstract class AbstractModel implements Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
/*    @SequenceGenerator(
            name = "contact_sequence",
            sequenceName = "contact_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "contact_sequence"
    )*/
    private Long id;

    @Version
    private Integer version;

    @CreationTimestamp
    private Date creationTime;

    @UpdateTimestamp
    private Date updateTime;

    /**
     * @see Model#getId()
     */
    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the model version
     *
     * @return the model version
     */
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the model version
     *
     * @param version the model version to set
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * Gets the model creation time
     *
     * @return the model creation time
     */
    public Date getCreationTime() {
        return creationTime;
    }

    /**
     * Sets the model creation time
     *
     * @param creationTime the model creation time to set
     */
    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    /**
     * Gets the model update time
     *
     * @return the model update time
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the model update time
     *
     * @param updateTime the model update time to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * @see Object#toString()
     */
    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", creationTime=" + creationTime +
                ", updateTime=" + updateTime +
                '}';
    }
}

