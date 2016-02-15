package com.kronos.jersey.hello;

public class Aloha {
	private String target = "";
	public Aloha() {
	}
	public Aloha(String target) {
		this.target = target;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
}
