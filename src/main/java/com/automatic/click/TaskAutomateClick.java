package com.automatic.click;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.TimerTask;

import org.apache.log4j.Logger;

public class TaskAutomateClick extends TimerTask {
	
	final static Logger logger = Logger.getLogger(TaskAutomateClick.class);
	
	
	@Override
	public void run() {
		Robot hal;
		System.out.println("Running");
		logger.info("Running");
		
		try {
			hal = new Robot();
			hal.mousePress(0);
			logger.info("Mouse pressed");
		} catch (AWTException e) {
			logger.error("ERROR: Description:", e);
		}

	}

}
