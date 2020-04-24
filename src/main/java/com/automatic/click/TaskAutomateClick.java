package com.automatic.click;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.TimerTask;

public class TaskAutomateClick extends TimerTask {
	@Override
	public void run() {
		Robot hal;
		System.out.println("Running");
		try {
			hal = new Robot();
			hal.mousePress(0);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

}
