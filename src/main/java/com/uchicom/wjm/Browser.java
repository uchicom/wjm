package com.uchicom.wjm;

import java.util.Properties;

import com.uchicom.util.ResourceUtil;
import com.uchicom.wjm.entity.Bookmark;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class Browser extends BorderPane {

	private final WebEngine engine;

	private final TextField urlText = new TextField();

	private final Properties config = ResourceUtil.createProperties(Constants.CONF_FILE, "utf-8");

	private final BookmarkManager bookmarkManager = new BookmarkManager(config);

	public static Scene createScene() {
		Scene scene = new Scene(new Browser());
		return scene;
	}

	/**
	 * ブラウザに必要な機能はブックマークと設定 ブックマークは、追加、移動 タブブラウザ
	 */
	public Browser() {
		// ブラウザ
		WebView view = new WebView();
		engine = view.getEngine(); // 2.WebViewからWebEngineを取得

		// メニューには領域が必要
		VBox vbox = new VBox();
		Menu menu1 = new Menu("ブックマーク");
		MenuItem add = new MenuItem("現在のページを追加");
		add.setOnAction((ActionEvent t) -> {
			Bookmark bookmark = new Bookmark();
			bookmark.title = engine.getTitle();
			bookmark.url = engine.getLocation();
			bookmarkManager.add(bookmark);
			menu1.getItems().add(new MenuItem(engine.getTitle()));
		});
		menu1.getItems().add(add);
		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().add(menu1);
		vbox.getChildren().add(menuBar);
		setTop(vbox);
		engine.getLoadWorker().stateProperty()
				.addListener((ObservableValue<? extends State> ov, State oldState, State newState) -> {

					if (newState == State.SUCCEEDED) {
						System.out.println("body:" + engine.getDocument().getElementsByTagName("body").item(0).getTextContent());
//						System.out.println("title:" + engine.getTitle());
						System.out.println("url:" + engine.getLocation());
						urlText.setText(engine.getLocation());
						urlText.setEditable(true);
						getScene().setCursor(Cursor.DEFAULT);
					} else if (newState == State.CANCELLED) {
						urlText.setEditable(true);
						getScene().setCursor(Cursor.DEFAULT);
					} else {
						System.out.println("その他：" + newState);
					}
				});
		view.setMinSize(600, 500);
		setCenter(view);

		// 水平ボックス
		HBox hbox = new HBox(10);
		hbox.setPrefHeight(40);
		hbox.setAlignment(Pos.BASELINE_CENTER);
		vbox.getChildren().add(hbox);

		// テキスト入力
		urlText.setPromptText("http://labs.uchicom.com/");
		// urlBox.setFont(new Font("sanserif", 16));
		hbox.getChildren().add(urlText);

		// ボタン
		Button button = new Button("Go");
		button.setFont(new Font("sanserif", 16));
		hbox.getChildren().add(button);

		button.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				loadUrl();
			}
		});
		// ボタン
		Button cancel = new Button("Cancel");
		cancel.setFont(new Font("sanserif", 16));
		hbox.getChildren().add(cancel);

		cancel.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				engine.getLoadWorker().cancel();
			}
		});
	}

	public void loadUrl() {
		urlText.setEditable(false);
		engine.load(urlText.getText());
		getScene().setCursor(Cursor.WAIT);
	}
}
