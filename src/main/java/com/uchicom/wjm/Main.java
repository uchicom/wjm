// (c) 2017 uchicom
package com.uchicom.wjm;

import javax.swing.SwingUtilities;

/**
 *
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(()-> {
			WJMFrame frame = new WJMFrame();
			frame.setVisible(true);
		});
	}

}
