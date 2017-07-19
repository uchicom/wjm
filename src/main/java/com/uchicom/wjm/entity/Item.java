// (c) 2017 uchicom
package com.uchicom.wjm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

	private int seq;
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
	/**
	 * seqを取得します.
	 *
	 * @return seq
	 */
	public int getSeq() {
		return seq;
	}
	/**
	 * seqを設定します.
	 *
	 * @param seq seq
	 */
	public void setSeq(int seq) {
		this.seq = seq;
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + seq;
		return result;
	}
	/* (非 Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (seq != other.seq)
			return false;
		return true;
	}
}
