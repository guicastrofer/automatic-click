package com.automatic.click;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class TaskAutomateMove extends TimerTask{

	final static Logger logger = Logger.getLogger(TaskAutomateMove.class);
	
	@Override
	public void run() {
		Robot hal;
		System.out.println("Running");
		logger.info("Running");
		try {
			Random random = new Random();
			hal = new Robot();
			hal.mouseMove(500+random.nextInt(), 800+random.nextInt());
			logger.info("Mouse moving");
		} catch (AWTException e) {
			logger.error("ERROR: Description:", e);
		}
	}

}
