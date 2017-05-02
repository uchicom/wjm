// (c) 2017 uchicom
package com.uchicom.wjm.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class Query {

	private String title;
	private String totalResults;
	private String searchTerms;
	private String count;
	private String startIndex;
	private String inputEncoding;
	private String outputEncoding;
	private String safe;
	private String cx;

	/**
	 * titleを取得します.
	 *
	 * @return title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * titleを設定します.
	 *
	 * @param title title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * totalResultsを取得します.
	 *
	 * @return totalResults
	 */
	@JsonProperty("totalResults")
	public String getTotalResults() {
		return totalResults;
	}
	/**
	 * totalResultsを設定します.
	 *
	 * @param totalResults totalResults
	 */
	public void setTotalResults(String totalResults) {
		this.totalResults = totalResults;
	}
	/**
	 * searchTermsを取得します.
	 *
	 * @return searchTerms
	 */
	@JsonProperty("searchTerms")
	public String getSearchTerms() {
		return searchTerms;
	}
	/**
	 * searchTermsを設定します.
	 *
	 * @param searchTerms searchTerms
	 */
	public void setSearchTerms(String searchTerms) {
		this.searchTerms = searchTerms;
	}
	/**
	 * countを取得します.
	 *
	 * @return count
	 */
	public String getCount() {
		return count;
	}
	/**
	 * countを設定します.
	 *
	 * @param count count
	 */
	public void setCount(String count) {
		this.count = count;
	}
	/**
	 * startIndexを取得します.
	 *
	 * @return startIndex
	 */
	@JsonProperty("startIndex")
	public String getStartIndex() {
		return startIndex;
	}
	/**
	 * startIndexを設定します.
	 *
	 * @param startIndex startIndex
	 */
	public void setStartIndex(String startIndex) {
		this.startIndex = startIndex;
	}
	/**
	 * inputEncodingを取得します.
	 *
	 * @return inputEncoding
	 */
	@JsonProperty("inputEncoding")
	public String getInputEncoding() {
		return inputEncoding;
	}
	/**
	 * inputEncodingを設定します.
	 *
	 * @param inputEncoding inputEncoding
	 */
	public void setInputEncoding(String inputEncoding) {
		this.inputEncoding = inputEncoding;
	}
	/**
	 * outputEncodingを取得します.
	 *
	 * @return outputEncoding
	 */
	@JsonProperty("outputEncoding")
	public String getOutputEncoding() {
		return outputEncoding;
	}
	/**
	 * outputEncodingを設定します.
	 *
	 * @param outputEncoding outputEncoding
	 */
	public void setOutputEncoding(String outputEncoding) {
		this.outputEncoding = outputEncoding;
	}
	/**
	 * safeを取得します.
	 *
	 * @return safe
	 */
	public String getSafe() {
		return safe;
	}
	/**
	 * safeを設定します.
	 *
	 * @param safe safe
	 */
	public void setSafe(String safe) {
		this.safe = safe;
	}
	/**
	 * cxを取得します.
	 *
	 * @return cx
	 */
	public String getCx() {
		return cx;
	}
	/**
	 * cxを設定します.
	 *
	 * @param cx cx
	 */
	public void setCx(String cx) {
		this.cx = cx;
	}
}
