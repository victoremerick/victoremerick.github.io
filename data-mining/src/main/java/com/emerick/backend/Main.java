package com.emerick.backend;

import static spark.Spark.get;
import static spark.Spark.post;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;

import com.emerick.backend.model.ErrorMessage;
import com.emerick.backend.model.Search;
import com.emerick.backend.model.ThreadId;
import com.emerick.backend.model.ThreadStatus;
import com.emerick.backend.thread.SearchThread;
import com.google.gson.Gson;

public class Main {
	
	public static final String BASE_URL = System.getenv().get("BASE_URL");
	public static final Integer MAX_RESULTS = tryParseInteger(System.getenv().get("MAX_RESULTS"));
	
	private static Map<String, SearchThread> threads = new HashMap<>();
		
	public static void main(String[] args) {
		if("".equals(BASE_URL) || BASE_URL == null) {
			return ;
		}
		
        get("/crawl/:id", (req, res) -> {
    		res.header("Content-type", "application/json");
        	
        	Gson gson = new Gson();
        	
        	String id = req.params("id");

        	SearchThread thread = threads.get(id); 
        	if(thread == null) {
        		res.status(404);
        		return gson.toJson(new ErrorMessage("crawl not found: "+id, 404));
        	}

        	ThreadStatus status = thread.getStatus();
        	
        	return gson.toJson(status);
        });
        
        post("/crawl", (req, res) -> {
    		res.header("Content-type", "application/json");
        	
        	Gson gson = new Gson();
        	
        	String reqJSON = req.body();
        	Search search = gson.fromJson(reqJSON, Search.class);
        	
        	int lengthKeyword = search.getKeyword().length();
        	if(lengthKeyword >= 4 && lengthKeyword <= 32) {
	        	
	        	String id = RandomStringUtils.random(8, 0, 0, true, true, null, new SecureRandom());
	        	
	        	SearchThread instance = SearchThread.createInstance(
	    			search.getKeyword(),
	    			id
	        	);
	        	threads.put(id, instance);
	        	instance.start();
	        	
        		return gson.toJson(new ThreadId(id));
        	}else {
        		res.status(400);
        		return gson.toJson(new ErrorMessage("field 'keyword' is required (from 4 up to 32 chars)", 400));
        	}
        });
    }

	public static Integer tryParseInteger(String text) {
		try {
			Integer i = Integer.parseInt(text);
			if (i < 0) return -1;
			return i;
		} catch (NumberFormatException e) {
			return -1;
		}
	}
}
