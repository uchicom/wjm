// (c) 2017 uchicom
package com.uchicom.wjm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

	private String kind;
	private String title;
	private String link;
	private String snippet;
	public String getKind() {
		return kind;
	}
	public void setKind(String kind) {
		this.kind = kind;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	/**
	 * snippetを取得します.
	 *
	 * @return snippet
	 */
	public String getSnippet() {
		return snippet;
	}
	/**
	 * snippetを設定します.
	 *
	 * @param snippet snippet
	 */
	public void setSnippet(String snippet) {
		this.snippet = snippet;
	}
}
