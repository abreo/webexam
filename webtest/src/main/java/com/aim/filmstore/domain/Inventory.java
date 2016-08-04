package com.aim.filmstore.domain;

import java.util.Date;

public class Inventory {
	private Integer inventory_id;
	private Integer film_idsmall;
	private Integer store_id;
	private Date last_update;
	public Integer getInventory_id() {
		return inventory_id;
	}
	public void setInventory_id(Integer inventory_id) {
		this.inventory_id = inventory_id;
	}
	public Integer getFilm_idsmall() {
		return film_idsmall;
	}
	public void setFilm_idsmall(Integer film_idsmall) {
		this.film_idsmall = film_idsmall;
	}
	public Integer getStore_id() {
		return store_id;
	}
	public void setStore_id(Integer store_id) {
		this.store_id = store_id;
	}
	public Date getLast_update() {
		return last_update;
	}
	public void setLast_update(Date last_update) {
		this.last_update = last_update;
	}
	
	
}
