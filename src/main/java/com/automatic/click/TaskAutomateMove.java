package com.automatic.click;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;
import java.util.TimerTask;

public class TaskAutomateMove extends TimerTask{

	@Override
	public void run() {
		Robot hal;
		System.out.println("Running");
		try {
			Random random = new Random();
			hal = new Robot();
			hal.mouseMove(500+random.nextInt(), 800+random.nextInt());
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

}
