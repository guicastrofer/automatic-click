package com.automatic.click.service;

import org.apache.log4j.Logger;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

import com.automatic.click.controllers.App;
import com.automatic.click.util.AppConstants;

import javafx.application.Application;

public class GlobalKeyListener implements NativeKeyListener {

	final static Logger logger = Logger.getLogger(GlobalKeyListener.class);
	
	public App app = new App();

	@Override
	public void nativeKeyPressed(NativeKeyEvent nativeKeyEvent) {
		if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_P) {
			logger.info(nativeKeyEvent.getKeyCode()+" "+AppConstants.PRESSED);
			app.stop();
		}
	}

	@Override
	public void nativeKeyReleased(NativeKeyEvent nativeKeyEvent) {
		if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_P) {
			logger.info(nativeKeyEvent.getKeyCode()+" "+AppConstants.PRESSED);
			app.stop();
		}
	}

	@Override
	public void nativeKeyTyped(NativeKeyEvent nativeKeyEvent) {
		if (nativeKeyEvent.getKeyCode() == NativeKeyEvent.VC_P) {
			logger.info(nativeKeyEvent.getKeyCode()+" "+AppConstants.PRESSED);
			app.stop();
		}
	}

}
