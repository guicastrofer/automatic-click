package com.automatic.click.service;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Random;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.automatic.click.util.AppConstants;

public class TaskAutomateMove extends TimerTask{

	final static Logger logger = Logger.getLogger(TaskAutomateMove.class);
	
	@Override
	public void run() {
		Robot hal;
		logger.info(AppConstants.RUNNING);
		
		try {
			Random random = new Random();
			hal = new Robot();
			hal.mouseMove(500+random.nextInt(), 800+random.nextInt());
			logger.info(AppConstants.MOUSE_MOVING);
		} catch (AWTException e) {
			logger.error(AppConstants.ERROR, e);
		}
		
	}

}
