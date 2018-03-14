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
			// ����
			mainui.adb_Shell_Util
					.execCmd("adb shell /system/bin/screencap -p /sdcard/tiao.png");
			mainui.adb_Shell_Util
					.execCmd("adb pull /sdcard/tiao.png G:/MyWorkSpace/��һ������/pic/tiao.png");

			// ��ʾͼƬ
			ImageIcon i1 = new ImageIcon(
					"G:\\MyWorkSpace\\��һ������\\pic\\tiao.png");
			mainui.icon.setIcon(i1);

			count++;
			int distance = new Image_Util().getRgb(
					"G:\\MyWorkSpace\\��һ������\\pic\\tiao.png",
					"G:\\MyWorkSpace\\��һ������\\pic\\tiao2.png");

			// jump
			int press_time = (int) (distance * 1.25);
			if (press_time < 300) {
				press_time = 300;
			}
			// else if (press_time > 400 && press_time < 510) {
			// press_time = 510;
			// }
			System.out.println(count + "��");
			System.out.println("Ŀ����룺" + distance);
			System.out.println("��ѹʱ�䣺" + press_time);
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
