package com.aim.filmstore.domain;

import java.util.List;

public class PageBean<T>{
	private int pc;//当前页码
	private Integer tr;//总记录数
	private Integer ps;//每页记录数
    private List<T> beanList;//当前页记录数
	private String url;
	public int getPc() {
		return pc;
	}
	public void setPc(Integer pc) {
		this.pc = pc;
	}
	public int getTr() {
		return tr;
	}
	public void setTr(Integer tr) {
		this.tr = tr;
	}
	public int getPs() {
		return ps;
	}
	public void setPs(int ps) {
		this.ps = ps;
	}
	public List<T> getBeanList() {
		return beanList;
	}
	public void setBeanList(List<T> beanList) {
		this.beanList = beanList;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 计算总页数
	 * @return
	 */
	public int getTp(){
		int tp=tr/ps;
		return tr%ps==0?tp:tp+1;
	}
	
}
