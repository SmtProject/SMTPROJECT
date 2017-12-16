package smt.model.tools;

import java.util.Date;

public class Followed {
	protected Integer id;
	protected Integer createdBy;
	protected Date createdDate;
	protected Integer updatedBy;
	protected Date updateDate;
	
	public void setFollowedAttribute(Integer userId,Date nowDate) {
		if(createdBy==null && createdDate==nowDate) {
			this.createdBy=userId;
			this.createdDate=nowDate;
		}else {
			this.updatedBy=userId;
			this.updateDate=nowDate;
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public Integer getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}

	public Integer getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	

}
