package com.emerick.backend.test;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import com.emerick.backend.model.Search;
import com.emerick.backend.model.ThreadId;
import com.emerick.backend.model.ThreadStatus;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

class TestMain{

	public static ThreadId compartilhado = null;
	 
	@Tag("DEV")
    @Test
    void test_1_crawl_post() 
    {		
		Gson gson = new Gson();
		
		Document doc;
		try {
			doc = Jsoup.connect("http://localhost:4567/crawl").requestBody(gson.toJson(new Search("desenvolvedor"))).post();
			TestMain.compartilhado = gson.fromJson(doc.text(), ThreadId.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Assertions.assertFalse(false);
		}catch (JsonSyntaxException e) {
			Assertions.fail();
		}
    }
	
	@Tag("DEV")
    @Test
    void test_2_crawl_get() 
    {
		
		if(TestMain.compartilhado == null) {
			Assertions.assertFalse(false);
			return;
		}
		
		ThreadStatus status = null;
		do {
			Gson gson = new Gson();
			Document doc;
			try {
				doc = Jsoup.connect("http://localhost:4567/crawl/"+TestMain.compartilhado.getId()).get();
				status = gson.fromJson(doc.text(), ThreadStatus.class);
			} catch (IOException e) {
				break;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}while(status.getUrls().size() < 10  || !status.getStatus().equals("done"));
		Assertions.assertEquals("done", status.getStatus());
		Assertions.assertEquals(10, status.getUrls().size());
    }
}
