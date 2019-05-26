package com.imad.retailstore.entity;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Version;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

public class BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Column(name = "CREATED_BY")
	private long createdBy;
	@CreationTimestamp
	@Column(name = "CREATED_DATE")
	private transient LocalDate createdDate;
	@Column(name = "MODIFIED_BY")
	private long modifiedBy;
	@UpdateTimestamp
	@Column(name = "MODIFIED_DATE")
	private transient LocalDate modifiedDate;
	@Version
	@Column(name = "VERSION_NO")
	private long versionNo;

	public long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(long createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}

	public long getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public LocalDate getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDate modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public long getVersionNo() {
		return versionNo;
	}

	public void setVersionNo(long versionNo) {
		this.versionNo = versionNo;
	}

}
