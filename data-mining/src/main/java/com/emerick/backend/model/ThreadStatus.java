package com.emerick.backend.model;

import java.util.ArrayList;
import java.util.List;

public class ThreadStatus {
	private String id;
	private String status = "inactive";
	private List<String> urls = new ArrayList<>();
	
	public ThreadStatus(String id) {
		super();
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public List<String> getUrls() {
		return urls;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}

}
