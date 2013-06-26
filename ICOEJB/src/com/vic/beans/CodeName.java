package com.vic.beans;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="CODENAME")
public class CodeName {
	private String code;
	private String name;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
