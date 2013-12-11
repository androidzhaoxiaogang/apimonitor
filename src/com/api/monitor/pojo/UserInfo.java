package com.api.monitor.pojo;

public class UserInfo {

	private boolean result;
	private String name;
	private String message;
	public boolean getResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "UserInfo [result=" + result + ", name=" + name + ", message="
				+ message + "]";
	}
	
	
}
