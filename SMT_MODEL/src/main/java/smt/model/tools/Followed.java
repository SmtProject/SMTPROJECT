package smt.model.tools;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.smt.data.entity.SmtUser.SmtUserStatus;

@MappedSuperclass
public class Followed implements Serializable{
	
	public enum FollowedStatus{ACTIVE,INACTIVE}

	private static final long serialVersionUID = 3398492809036819501L;

	protected Integer id;
	protected String createdBy;
	protected Date createdDate;
	protected String updatedBy;
	protected Date updatedDate;
	protected FollowedStatus status;
	
	public Followed() {
		
	}
	
	@Column (name = "ID")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CREATEDBY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	@Column(name = "CREATEDATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	@Column(name = "UPDATEDBY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	@Column(name = "UPDATEDATE")
	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	
	@Column(name = "STATUS")
	@Enumerated(EnumType.STRING) 
	public FollowedStatus getStatus() {
		return status;
	}

	public void setStatus(FollowedStatus status) {
		this.status = status;
	}

	public void setFollowedAttribute(String userName,Date nowDate) {
		if(createdBy==null && createdDate==null) {
			this.createdBy=userName;
			this.createdDate=nowDate;
		}else {
			this.updatedBy=userName;
			this.updatedDate=nowDate;
		}
	}
}
