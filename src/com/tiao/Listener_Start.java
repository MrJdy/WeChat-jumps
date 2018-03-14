package com.tiao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Listener_Start implements ActionListener {

	MainUI mainui;
	int count = 0;

	@Override
	public void actionPerformed(ActionEvent e) {
		while (true) {
			// ½ØÆÁ
			mainui.adb_Shell_Util
					.execCmd("adb shell /system/bin/screencap -p /sdcard/tiao.png");
			mainui.adb_Shell_Util
					.execCmd("adb pull /sdcard/tiao.png G:/MyWorkSpace/ÌøÒ»Ìø¸¨Öú/pic/tiao.png");

			// ÏÔÊ¾Í¼Æ¬
			ImageIcon i1 = new ImageIcon(
					"G:\\MyWorkSpace\\ÌøÒ»Ìø¸¨Öú\\pic\\tiao.png");
			mainui.icon.setIcon(i1);

			count++;
			int distance = new Image_Util().getRgb(
					"G:\\MyWorkSpace\\ÌøÒ»Ìø¸¨Öú\\pic\\tiao.png",
					"G:\\MyWorkSpace\\ÌøÒ»Ìø¸¨Öú\\pic\\tiao2.png");

			// jump
			int press_time = (int) (distance * 1.25);
			if (press_time < 300) {
				press_time = 300;
			}
			// else if (press_time > 400 && press_time < 510) {
			// press_time = 510;
			// }
			System.out.println(count + "£º");
			System.out.println("Ä¿±ê¾àÀë£º" + distance);
			System.out.println("°´Ñ¹Ê±¼ä£º" + press_time);
			System.out.println();
			String cmd = "adb shell input swipe 170 187 170 187 " + press_time;
			// System.out.println(cmd);
			mainui.adb_Shell_Util.execCmd(cmd);
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	public MainUI getMainui() {
		return mainui;
	}

	public void setMainui(MainUI mainui) {
		this.mainui = mainui;
	}

}
