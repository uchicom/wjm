// (c) 2017 uchicom
package com.uchicom.wjm.action;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import com.uchicom.wjm.WJMFrame;

/**
 * @author uchicom: Shigeki Uchiyama
 *
 */
public class ViewSourceAction extends AbstractAction {

	private WJMFrame frame;
	public ViewSourceAction(WJMFrame frame) {
		this.frame = frame;
		putValue(NAME, "ソース表示");
	}
	/* (非 Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		frame.viewSource();

	}

}
