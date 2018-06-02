// (c) 2017 uchicom
package com.uchicom.wjm;

import javax.swing.SwingUtilities;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * JavaFX
 * 
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class Main extends Application {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length > 0 && "-fx".equals(args[0])) {
			launch(args);
		} else {
			SwingUtilities.invokeLater(() -> {
				WJMFrame frame = new WJMFrame();
				frame.setVisible(true);
			});
		}
	}

	@Override
	public void start(Stage stage) throws Exception {
		stage.setTitle("Wjm");
		stage.setScene(Browser.createScene());
		stage.show();
	}

}
