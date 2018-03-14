package com.tiao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ADB_Shell_Util {
	
	public MainUI mainUI;
	
	
	
	
	public void  execCmd(String cmd) {
		
		try {
			Process p = Runtime.getRuntime().exec(cmd);
			BufferedReader br = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String s = br.readLine();
			mainUI.area.setText("");
			while(s != null) {
				mainUI.area.append(s+"\n");
				s = br.readLine();
			}
			br.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}


	public MainUI getMainUI() {
		return mainUI;
	}


	public void setMainUI(MainUI mainUI) {
		this.mainUI = mainUI;
	}
	
	
	

}
