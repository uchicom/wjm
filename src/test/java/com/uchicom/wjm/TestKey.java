// (c) 2017 uchicom
package com.uchicom.wjm;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.uchicom.ui.util.ResourceUtil;
import com.uchicom.wjm.entity.Search;
import com.uchicom.wjm.util.CustomSearch;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class TestKey {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Properties config = ResourceUtil.createProperties(new File("conf/wjm.properties"), "utf-8");
		CustomSearch customSearch = new CustomSearch(config.getProperty("cx"));
		String[] searchValues = new String[]{
				"シェアオフィス　ウチコム",
				"藤沢市　ウチコム",
				"藤沢駅　ウチコム",
				"ソフトウェア　ウチコム",
				"Android　ウチコム",
				"レンタルオフィス　ウチコム",
				"会議室　ウチコム",
				"藤沢駅　企業",
				"藤沢市　企業",
				"藤沢駅　ソフトウェア　ウチコム",
				"湘南　ソフトウェア　ウチコム",
				"江ノ島　ソフトウェア　ウチコム",
				"wjm　ウチコム",
				"syo　ウチコム",
				"oss　ウチコム",
				"jsong　ウチコム",
				"jl　ウチコム",
				"jio　ウチコム",
				"SSL　ウチコム",
				"tm　ウチコム",
				"rssr　ウチコム",
				"csve　ウチコム",
				"pdfv　ウチコム",
				"源泉徴収票　出力　ウチコム",
				"給与管理システム　ウチコム",
				"運送業システム　ウチコム",
				"Android端末開発　ウチコム",
				"神奈川県　シェアオフィス　ウチコム",
				"藤沢市　シェアオフィス　ウチコム",
				"藤沢市　レンタルオフィス　ウチコム",
				"藤沢市　会議室　ウチコム",
				"ウチコム"};
		for (int i = 0; i < 32; i++) {
			try {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
				Search search = customSearch.execute(config.getProperty("key." + i), searchValues[i]);
				if (search != null) {
	//				System.out.println(search.getUrl());
//					System.out.println(search.getItems().length);
				}

			} catch (UnsupportedEncodingException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		}
	}

}
