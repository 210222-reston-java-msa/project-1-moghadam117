package com.revature.models;

public class ResolveTemplate {
	private int reimbId;
	private int resolverId;
	private int statusId;

	public ResolveTemplate() {

	}

	public ResolveTemplate(int reimbId, int resolverId, int statusId) {
		super();
		this.reimbId = reimbId;
		this.resolverId = resolverId;
		this.statusId = statusId;
	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	@Override
	public String toString() {
		return "ResolveTemplate [reimbId=" + reimbId + ", resolverId=" + resolverId + ", statusId=" + statusId + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimbId;
		result = prime * result + resolverId;
		result = prime * result + statusId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResolveTemplate other = (ResolveTemplate) obj;
		if (reimbId != other.reimbId)
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (statusId != other.statusId)
			return false;
		return true;
	}
	
}
