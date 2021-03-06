// (c) 2017 uchicom
package com.uchicom.wjm;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URLStreamHandler;
import java.nio.file.Files;
import java.util.List;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent.EventType;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uchicom.ui.FileOpener;
import com.uchicom.ui.ResumeFrame;
import com.uchicom.ui.util.DialogUtil;
import com.uchicom.wjm.action.SearchAction;
import com.uchicom.wjm.entity.Item;
import com.uchicom.wjm.entity.Search;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class WJMFrame extends ResumeFrame implements FileOpener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JTextField searchTextField = new JTextField();

	private Action searchAction = new SearchAction(this);

	private JEditorPane editorPane = new JEditorPane();

	/**
	 * 検索すると検索結果が表示される。
	 */
	public WJMFrame() {
		super(Constants.CONF_FILE, Constants.PROP_KEY_WJM);
		initComponents();
	}
	public WJMFrame(URL url) {
		this();
		searchTextField.setText(url.toString());
		search(url);
	}

	private void initComponents() {

		setTitle("Regression");
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(searchTextField, BorderLayout.CENTER);
		northPanel.add(new JButton(searchAction), BorderLayout.EAST);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(editorPane), BorderLayout.CENTER);
		FileOpener.installDragAndDrop(editorPane, this);

		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener((event)-> {
			if (event.getEventType() == EventType.ACTIVATED) {
				try {
					searchTextField.setText(event.getURL().toString());
					editorPane.setPage(event.getURL());
				} catch (Exception e) {
					e.printStackTrace();
					DialogUtil.showMessageDialog(this, e.getMessage());
				}
			}
		});
//			@Override
//			public HTMLEditorKit.ParserCallback getReader(int pos) {
//				HTMLReader callback = new HTMLReader(pos) {
//					@Override
//					public void flush() throws BadLocationException {
//						System.out.println("flush");
//						System.out.println(editorPane.getText());
//						WJMFrame.this.flush();
//						super.flush();
//					}
//				};
//				return callback;
//			}
//		};
//		editorPane.setDocument(document);
		pack();
	}

//	public void flush() {
//		System.out.println(((HTMLDocument)editorPane.getDocument()).getBase());
//	}
	public void search(URL url) {
		System.out.println(editorPane.getText());
		if (url != null) {
			try {
//				URLConnection con = url.openConnection();
//				con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
//				con.setRequestProperty("Accept-Charset", "UTF-8,*;q=0.5");
//				con.setRequestProperty("Accept-Language", "ja,en-US;q=0.8,en;q=0.6");
//				con.setRequestProperty("User-Agent", "Mozilla/5.0");
//				System.out.println(con.getURL());

//				InputStream is = con.getInputStream();
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				byte[] bytes = new byte[1024 * 4 * 10];
//				int length = 0;
//				while ((length = is.read(bytes)) > 0) {
//					baos.write(bytes, 0, length);
//				}
//				String string = new String(baos.toByteArray());
//				System.out.println(string);


				editorPane.setPage(url);// httpsに変化していることは検知できなさそう。WebKitのエンジンを使用する必要がありそう。

				//				is.close();
			} catch (MalformedURLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
			return;
		}
		String searchText = searchTextField.getText();
		if (searchText.startsWith("http://") || searchText.startsWith("https://")) {
			try {

				url = new URL(searchText);
				url = new URL(url.getProtocol(), url.getHost(), url.getPort(), url.getFile(), new URLStreamHandler() {

					@Override
					protected int getDefaultPort() {
						System.out.println("dp:" + super.getDefaultPort());
						return 80;
					}
					@Override
					protected URLConnection openConnection(URL paramURL) throws IOException {
						System.out.println("openCon:" + paramURL);
						URLConnection con = new URL(paramURL.toString()).openConnection();
						if (con instanceof HttpURLConnection) {
							HttpURLConnection hconn = (HttpURLConnection) con;
							hconn.setInstanceFollowRedirects(false);
							int response = hconn.getResponseCode();
							boolean redirect = (response >= 300 && response <= 399);

							/*
							 * In the case of a redirect, we want to actually change the URL
							 * that was input to the new, redirected URL
							 */
							if (redirect) {
								String loc = con.getHeaderField("Location");
								if (loc.startsWith("http", 0)) {
									paramURL = new URL(loc);
								} else {
									paramURL = new URL(paramURL, loc);
								}
								con = new URL(paramURL.toString()).openConnection();
								searchTextField.setText(paramURL.toString());
							}
						}
//						con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
						con.setRequestProperty("Accept-Charset", "UTF-8,*;q=0.5");
						con.setRequestProperty("Accept-Language", "ja,en-US;q=0.8,en;q=0.6");
						con.setRequestProperty("User-Agent", "WJM/1.0.1");
						//JEditorPaneはfinalじゃないので、拡張すればいろいろできそう。

						System.out.println("opendCon:" + con.getURL());
						return con;
					}

				});
//				URLConnection con = url.openConnection();
//				con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
//				con.setRequestProperty("Accept-Charset", "UTF-8,*;q=0.5");
//				con.setRequestProperty("Accept-Language", "ja,en-US;q=0.8,en;q=0.6");
//				con.setRequestProperty("User-Agent", "Mozilla/5.0");
//				System.out.println(con.getURL());

//				InputStream is = con.getInputStream();
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				byte[] bytes = new byte[1024 * 4 * 10];
//				int length = 0;
//				while ((length = is.read(bytes)) > 0) {
//					baos.write(bytes, 0, length);
//				}
//				String string = new String(baos.toByteArray());
//
//				System.out.println(string);

				editorPane.setPage(url);// httpsに変化していることは検知できなさそう。WebKitのエンジンを使用する必要がありそう。
//				is.close();
			} catch (MalformedURLException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
		} else {
			StringBuffer strBuff = new StringBuffer();
			strBuff.append(
					"https://www.googleapis.com/customsearch/v1?cx=")
			.append(config.getProperty("cx"))
			.append("&key=")
			.append(config.getProperty("key"))
			.append("&q=");
			try {
				strBuff.append(URLEncoder.encode(searchText.replaceAll(" ", "+"), "utf-8"));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			searchText = strBuff.toString();
			try {
				long start = System.currentTimeMillis();
				url = new URL(searchText);
				URLConnection con = url.openConnection();
//				con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
				con.setRequestProperty("Accept-Charset", "UTF-8,*;q=0.5");
				con.setRequestProperty("Accept-Language", "ja,en-US;q=0.8,en;q=0.6");
				con.setRequestProperty("User-Agent", "WJM/1.0");
				System.out.println(con.getURL());

				InputStream is = con.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] bytes = new byte[1024 * 4 * 10];
				int length = 0;
				while ((length = is.read(bytes)) > 0) {
					baos.write(bytes, 0, length);
				}
				String string = new String(baos.toByteArray(), "utf-8");
//				System.out.println(string);
				System.out.println(baos.size());
				System.out.println("load:" + (System.currentTimeMillis() - start));
				start = System.currentTimeMillis();
				ObjectMapper mapper = new ObjectMapper();
				mapper.configure(Feature.AUTO_CLOSE_SOURCE, true);
				Search search = mapper.readValue(string, Search.class);

				System.out.println("obj:" + (System.currentTimeMillis() - start));
				start = System.currentTimeMillis();
				StringBuffer a = new StringBuffer();
				a.append("<html><body><h1>");
				a.append(searchTextField.getText());
				a.append("</h1><ul>");
				for (Item item : search.getItems()) {
					a.append("<li><a href='");
					a.append(item.getLink());
					a.append("'>");
					a.append(item.getTitle());
					a.append("</a>");
				}

				a.append("</ul></body></html>");

				editorPane.setText(a.toString());// .replace("src=['\"]http",
													// "src='http://localhost:8080/api/gk?url=http"));

				System.out.println("html:" + (System.currentTimeMillis() - start));
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
				DialogUtil.showMessageDialog(this, e.getMessage());
			}
		}
	}

	public void viewSource() {

	}
	/* (非 Javadoc)
	 * @see com.uchicom.ui.FileOpener#open(java.io.File)
	 */
	@Override
	public void open(File file) throws IOException {
		if (file.getName().endsWith(".url")) {
			byte[] bytes = Files.readAllBytes(file.toPath());
			if (new String(bytes, 0, 24).equals("[InternetShortcut]\r\nURL=") &&
					new String(bytes, bytes.length - 2, 2).equals("\r\n")) {
				String url = new String(bytes, 24, bytes.length - 26);
				searchTextField.setText(url);
				search(null);
			}
		}

	}
	/* (非 Javadoc)
	 * @see com.uchicom.ui.FileOpener#open(java.util.List)
	 */
	@Override
	public void open(List<File> fileList) {
		setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
		if (fileList.size() > 0) {
			try {
				open(fileList.get(0));
			} catch (IOException e) {
				DialogUtil.showMessageDialog(this, e.getMessage());
				e.printStackTrace();
			}
		}
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
	}

}
