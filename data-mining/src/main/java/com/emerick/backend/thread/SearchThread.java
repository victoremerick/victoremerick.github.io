package com.emerick.backend.thread;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.emerick.backend.Main;
import com.emerick.backend.model.ThreadStatus;

public class SearchThread extends Thread {

	ThreadStatus status;
	String keyword;

	public SearchThread(String searchTerm, String id) {
		super();
		keyword = searchTerm;
		status = new ThreadStatus(id);
	}

	public static SearchThread createInstance(String term, String id) {
		return new SearchThread(term, id);
	}

	public ThreadStatus getStatus() {
		return status;
	}

	public boolean checkResult(int results) {
		return (results < Main.MAX_RESULTS || Main.MAX_RESULTS <= 0);
	}

	public boolean checkIfEqualURLBASE(String url) {
		return !url.equalsIgnoreCase(Main.BASE_URL) && !url.equalsIgnoreCase(Main.BASE_URL + "/")
				&& !url.equalsIgnoreCase(Main.BASE_URL + "\\") && url.toUpperCase().contains("HTTP");
	}

	public boolean checkIfURLRelative(String url) {
		return !url.startsWith("http") && !url.startsWith("/") && !url.startsWith("\\") && !url.startsWith("#")
				&& !url.startsWith("javascript");
	}

	public String checkIfStartsWithURLBASE(String url, List<String> listaURLs) {
		if (url.startsWith(Main.BASE_URL) && !listaURLs.contains(url)) {// se url absoluta com o mesmo path
			return (url);
		}else {
			return null;
		}
	}
	
	public String checkIfIsURLRelative(String url, List<String> listaURLs) {
		String barra = "";

		if (!Main.BASE_URL.endsWith("/") && !Main.BASE_URL.endsWith("\\"))
			barra = "/";
		
		if (!listaURLs.contains(Main.BASE_URL + barra + url)) {
			listaURLs.add(Main.BASE_URL + barra + url);
			return (Main.BASE_URL + barra + url);
		}else {
			return null;
		}
	}

	public List<String> analisarURLS(List<String> listaURLs, Elements anchors) {
		List<String> urls = new ArrayList<>();
		for (Element anchor : anchors) {
			String url = anchor.attr("href");
			if(!url.equals("")) {
				if (checkIfEqualURLBASE(url)) {
					String urltmp = checkIfStartsWithURLBASE(url, listaURLs);
					if(urltmp!= null) {
						urls.add(urltmp);
					}
				} else if (checkIfURLRelative(url)) { // se não inicia com essas coisas então é relativa
					String urltmp = checkIfIsURLRelative(url, listaURLs);
					if(urltmp!= null) {
						urls.add(urltmp);
					}
				}
			}
		}
		return urls;
	}

	@Override
	public synchronized void run() {
		status.setStatus("active");

		int results = 0;
		String urlAtual = "";

		List<String> urlsAcessar = new ArrayList<>();
		urlsAcessar.add(Main.BASE_URL);
		int countURL = 0;
		while (checkResult(results) && countURL < urlsAcessar.size()) {

			try {
				urlAtual = urlsAcessar.get(countURL);
				Document doc = Jsoup.connect(urlAtual).get();
				Elements anchors = doc.body().getElementsByTag("a");
				urlsAcessar.addAll(analisarURLS(urlsAcessar, anchors));

				if (doc.html().contains(keyword)) {
					status.getUrls().add(urlAtual);
					results++;
				}
				countURL++;
			} catch (IOException e1) {
				countURL++;
			}

		}
		status.setStatus("done");
	}
}
