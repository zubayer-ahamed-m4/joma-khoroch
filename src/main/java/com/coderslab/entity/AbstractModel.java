package com.coderslab.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.format.annotation.DateTimeFormat;

import com.coderslab.model.enums.RecordStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author Zubayer Ahamed
 *
 */
@Data
@MappedSuperclass
public class AbstractModel {

	@CreationTimestamp
	@Column(name = "creationDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date creationDate;

	@CreationTimestamp
	@Column(name = "createTime")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "hh:mm:ss")
	private Date creationTime;

	@UpdateTimestamp
	@Column(name = "updateDate")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updateDate = new Date();

	@UpdateTimestamp
	@Column(name = "updateTime")
	@Temporal(TemporalType.TIME)
	@DateTimeFormat(pattern = "hh:mm:ss")
	private Date updateTime = new Date();

	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private RecordStatus status = RecordStatus.L;

	@Version
	@Column(name = "versionNo")
	private Long versionNo;

	@Transient
	@JsonIgnore
	public boolean isLive() {
		return status == null || status == RecordStatus.L;
	}

	@Transient
	@JsonIgnore
	public boolean isArchive() {
		return status != null && status == RecordStatus.D;
	}
}
