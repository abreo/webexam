package com.aim.filmstore.domain;

import java.util.Date;

public class Category {

private Integer category_id;
private String name;
private Date last_update;
public Integer getCategory_id() {
	return category_id;
}
public void setCategory_id(Integer category_id) {
	this.category_id = category_id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public Date getLast_update() {
	return last_update;
}
public void setLast_update(Date last_update) {
	this.last_update = last_update;
}


}
