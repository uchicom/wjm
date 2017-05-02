// (c) 2017 uchicom
package com.uchicom.wjm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Queries {

	private Query[] request;
	private Query[] nextPage;
	/**
	 * requestを取得します.
	 *
	 * @return request
	 */
	public Query[] getRequest() {
		return request;
	}
	/**
	 * requestを設定します.
	 *
	 * @param request request
	 */
	public void setRequest(Query[] request) {
		this.request = request;
	}
	/**
	 * nextPageを取得します.
	 *
	 * @return nextPage
	 */
	public Query[] getNextPage() {
		return nextPage;
	}
	/**
	 * nextPageを設定します.
	 *
	 * @param nextPage nextPage
	 */
	public void setNextPage(Query[] nextPage) {
		this.nextPage = nextPage;
	}
}
