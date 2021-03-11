package com.revature.models;

public class IdTemplate {
	private int id;
	
	public IdTemplate() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		IdTemplate other = (IdTemplate) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "IdTemplate [id=" + id + "]";
	}

	public IdTemplate(int id) {
		super();
		this.id = id;
	}
	
	
}
