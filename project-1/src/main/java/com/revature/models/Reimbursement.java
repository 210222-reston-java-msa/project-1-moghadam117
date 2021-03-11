package com.revature.models;

public class Reimbursement {

	private int reimbId;
	private int amount;
	private String description;
	private String submitDate;
	private String resolveDate;
	private int authorId;
	private int resolverId;
	private Status status;
	private Type type;

	public Reimbursement() {

	}

	public int getReimbId() {
		return reimbId;
	}

	public void setReimbId(int reimbId) {
		this.reimbId = reimbId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getSubmitDate() {
		return submitDate;
	}

	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}

	public String getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(String resolveDate) {
		this.resolveDate = resolveDate;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public int getResolverId() {
		return resolverId;
	}

	public void setResolverId(int resolverId) {
		this.resolverId = resolverId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + reimbId;
		result = prime * result + ((resolveDate == null) ? 0 : resolveDate.hashCode());
		result = prime * result + resolverId;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((submitDate == null) ? 0 : submitDate.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Reimbursement other = (Reimbursement) obj;
		if (amount != other.amount)
			return false;
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (reimbId != other.reimbId)
			return false;
		if (resolveDate == null) {
			if (other.resolveDate != null)
				return false;
		} else if (!resolveDate.equals(other.resolveDate))
			return false;
		if (resolverId != other.resolverId)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (submitDate == null) {
			if (other.submitDate != null)
				return false;
		} else if (!submitDate.equals(other.submitDate))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Reimbursement [reimbId=" + reimbId + ", amount=" + amount + ", description=" + description
				+ ", submitDate=" + submitDate + ", resolveDate=" + resolveDate + ", authorId=" + authorId
				+ ", resolverId=" + resolverId + ", status=" + status + ", type=" + type + "]";
	}

	public Reimbursement(int reimbId, int amount, String description, String submitDate, String resolveDate,
			int authorId, int resolverId, Status status, Type type) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.description = description;
		this.submitDate = submitDate;
		this.resolveDate = resolveDate;
		this.authorId = authorId;
		this.resolverId = resolverId;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int reimbId, int amount, String description, String submitDate, int authorId, Status status,
			Type type) {
		super();
		this.reimbId = reimbId;
		this.amount = amount;
		this.description = description;
		this.submitDate = submitDate;
		this.authorId = authorId;
		this.status = status;
		this.type = type;
	}

	public Reimbursement(int reimbId, int resolverId, Status status) {
		super();
		this.reimbId = reimbId;
		this.resolverId = resolverId;
		this.status = status;
	}


	

}
