package com.uchicom.wjm;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.uchicom.wjm.entity.Bookmark;

public class BookmarkManager {

	private List<Bookmark> urlList = new ArrayList<>();
	//private Properties config;
	public BookmarkManager(Properties config) {
		//this.config = config;
	}
	
	public void add(Bookmark bookmark) {
		System.out.println("url:" + bookmark.url);
		urlList.add(bookmark);
	}
	public void remove(String url) {
		
	}
}
