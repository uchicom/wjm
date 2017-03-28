// (c) 2017 uchicom
package com.uchicom.wjm;

import java.awt.BorderLayout;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.HyperlinkEvent.EventType;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uchicom.wjm.action.SearchAction;
import com.uchicom.wjm.entity.Item;
import com.uchicom.wjm.entity.Search;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class WJMFrame extends JFrame {

	private JTextField searchTextField = new JTextField();

	private Action searchAction = new SearchAction(this);

	private JEditorPane editorPane = new JEditorPane();

	/**
	 * 検索すると検索結果が表示される。
	 */
	public WJMFrame() {
		super("Regression");
		initComponents();
	}

	private void initComponents() {
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		JPanel northPanel = new JPanel();
		northPanel.setLayout(new BorderLayout());
		northPanel.add(searchTextField, BorderLayout.CENTER);
		northPanel.add(new JButton(searchAction), BorderLayout.EAST);
		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(northPanel, BorderLayout.NORTH);
		getContentPane().add(new JScrollPane(editorPane), BorderLayout.CENTER);

		editorPane.setContentType("text/html");
		editorPane.setEditable(false);
		editorPane.addHyperlinkListener((event)-> {
			if (event.getEventType() == EventType.ACTIVATED) {
				try {
					searchTextField.setText(event.getURL().toString());
					editorPane.setPage(event.getURL());
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(this, e.getMessage());
				}
			}
		});
//		HTMLDocument document = new HTMLDocument(){
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
	public void search() {
		String searchText = searchTextField.getText();
		if (searchText.startsWith("http://") || searchText.startsWith("https://")) {
			try {
				URL url = new URL(searchText);
				editorPane.setPage(url);// httpsに変化していることは検知できなさそう。WebKitのエンジンを使用する必要がありそう。

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
					"https://www.googleapis.com/customsearch/v1?cx=015439328453381694250:0lstixiiymo&key=AIzaSyAS7ksAWl1kla3bP1Mz1TGHjll7YjyRSHg&q=");
			strBuff.append(searchText);
			searchText = strBuff.toString();
			try {
				long start = System.currentTimeMillis();
				URL url = new URL(searchText);
				URLConnection con = url.openConnection();
				con.setRequestProperty("Accept-Encoding", "gzip,deflate,sdch");
				con.setRequestProperty("Accept-Charset", "UTF-8,*;q=0.5");
				con.setRequestProperty("Accept-Language", "ja,en-US;q=0.8,en;q=0.6");
				con.setRequestProperty("User-Agent", "Mozilla/5.0");
				System.out.println(con.getURL());

				InputStream is = con.getInputStream();
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				byte[] bytes = new byte[1024 * 4 * 10];
				int length = 0;
				while ((length = is.read(bytes)) > 0) {
					baos.write(bytes, 0, length);
				}
				String string = new String(baos.toByteArray());
				System.out.println(string);
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
			} catch (IOException e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this, e.getMessage());
			}
		}
	}

	public void viewSource() {

	}
}
