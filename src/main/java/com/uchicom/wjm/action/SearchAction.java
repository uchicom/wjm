// (c) 2017 uchicom
package com.uchicom.wjm.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.uchicom.wjm.WJMFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class SearchAction extends AbstractAction {

	private WJMFrame frame;
	public SearchAction(WJMFrame frame) {
		this.frame = frame;
		putValue(NAME, "検索");
	}
	/* (非 Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.search();
	}

}
