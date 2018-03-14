package com.tiao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Listener_Conn implements ActionListener {

	MainUI mainui;

	@Override
	public void actionPerformed(ActionEvent e) {
		mainui.adb_Shell_Util.execCmd("adb devices");
	}

	public MainUI getMainui() {
		return mainui;
	}

	public void setMainui(MainUI mainui) {
		this.mainui = mainui;
	}

}
