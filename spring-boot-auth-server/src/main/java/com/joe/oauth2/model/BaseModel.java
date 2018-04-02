/*******************************************************************************
 * Licensed to the OKChem
 *
 * http://www.joe.com
 *
 *******************************************************************************/
package com.joe.oauth2.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;


/**
 * base model ,which is general field for all entity
 * 
 * @author alan
 *
 */
@MappedSuperclass
public abstract class BaseModel implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3126910902448787329L;

	@NotNull
	private Long createdStamp;

	@NotNull
	private Long lastUpdatedStamp;

	@Column(length = 255)
	private String createdBy;

	@PrePersist
	protected void prePersist() {
		this.lastUpdatedStamp = new Date().getTime();
		if(this.createdStamp == null) {
			this.createdStamp = this.lastUpdatedStamp;
		}
	}

	@PreUpdate
	protected void onUpdate() {
		lastUpdatedStamp = new Date().getTime();
	}

	/**
	 * @return the createdStamp
	 */
	public Long getCreatedStamp() {
		return createdStamp;
	}

	/**
	 * @param createdStamp
	 *           the createdStamp to set
	 */
	public void setCreatedStamp(final Long createdStamp) {
		this.createdStamp = createdStamp;
	}

	/**
	 * @return the lastUpdatedStamp
	 */
	public Long getLastUpdatedStamp() {
		return lastUpdatedStamp;
	}

	/**
	 * @param lastUpdatedStamp
	 *           the lastUpdatedStamp to set
	 */
	public void setLastUpdatedStamp(final Long lastUpdatedStamp) {
		this.lastUpdatedStamp = lastUpdatedStamp;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}
