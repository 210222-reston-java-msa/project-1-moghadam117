package com.revature.models;

public class SubmitTemplate {
	private int amount;
	private int authorId;
	private String description;
	private int typeId;
	
	public SubmitTemplate() {
		
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getAuthorId() {
		return authorId;
	}

	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTypeId() {
		return typeId;
	}

	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}

	public SubmitTemplate(int amount, int authorId, String description, int typeId) {
		super();
		this.amount = amount;
		this.authorId = authorId;
		this.description = description;
		this.typeId = typeId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + amount;
		result = prime * result + authorId;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + typeId;
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
		SubmitTemplate other = (SubmitTemplate) obj;
		if (amount != other.amount)
			return false;
		if (authorId != other.authorId)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (typeId != other.typeId)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SubmitTemplate [amount=" + amount + ", authorId=" + authorId + ", description=" + description
				+ ", typeId=" + typeId + "]";
	}
	
}
