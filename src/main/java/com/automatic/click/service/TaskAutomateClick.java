package com.automatic.click.service;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.TimerTask;

import org.apache.log4j.Logger;

import com.automatic.click.util.AppConstants;

public class TaskAutomateClick extends TimerTask {

	final static Logger logger = Logger.getLogger(TaskAutomateClick.class);

	@Override
	public void run() {
		Robot hal;
		System.out.println(AppConstants.RUNNING);
		logger.info(AppConstants.RUNNING);

		try {
			hal = new Robot();
			hal.mousePress(0);
			logger.info(AppConstants.MOUSE_MOVING);
		} catch (AWTException e) {
			logger.error(AppConstants.ERROR, e);
		}

	}

}
