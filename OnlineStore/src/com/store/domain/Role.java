package com.store.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Role implements Serializable {

	private String id;
	private String name;
	private String description;
	
	private List<Function> list = new ArrayList();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + ", description="
				+ description + "]";
	}
	
}
