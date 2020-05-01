package com.automatic.click.controllers;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;
import org.jnativehook.example.NativeHookDemo;

import com.automatic.click.service.GlobalKeyListener;
import com.automatic.click.service.TaskAutomateClick;
import com.automatic.click.service.TaskAutomateMove;
import com.automatic.click.util.AppConstants;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class App extends Application {

	@FXML
	private Button clickButton;
	@FXML
	private Button moveButton;
	@FXML
	private Label label;

	final static Logger logger = Logger.getLogger(App.class);

	private static Stage stage;

	public boolean flag = true;

	ScheduledExecutorService t = Executors.newScheduledThreadPool(1);

	TaskAutomateClick aTask = new TaskAutomateClick();

	TaskAutomateMove mTask = new TaskAutomateMove();

	@Override
	public void start(Stage primaryStage) throws Exception {
		stage = primaryStage;
		FXMLLoader loader = new FXMLLoader(getClass().getResource(AppConstants.PATH_FXHTML));
		logger.info(AppConstants.READING + getClass().getResource(AppConstants.PATH_FXHTML));
		Parent root = (Parent) loader.load();
		Scene scene = new Scene(root, 400, 200);
		primaryStage.setTitle(AppConstants.AUTOMATE_CLICK);
		primaryStage.setScene(scene);
		primaryStage.show();
		logger.info(AppConstants.AUTOMATE_CLICK + AppConstants.READ + AppConstants.SUCESSFULLY);
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				logger.info(AppConstants.AUTOMATE_CLICK + AppConstants.CLOSED);
			}
		});
	}

	public static void main(String[] args) {
		try {
			logger.info(AppConstants.LOADING + AppConstants.NATIVE_HOOK);
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.exit(1);
			logger.error(AppConstants.ERROR + ": " + e);
		}
		GlobalScreen.addNativeKeyListener(new GlobalKeyListener());
		launch(args);
	}

	@FXML
	protected void startClick(ActionEvent event) {
		label.setText(AppConstants.RUNNING);
		logger.info(AppConstants.RUNNING);
		t.scheduleAtFixedRate(aTask, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	protected void startMove(ActionEvent event) {
		label.setText(AppConstants.RUNNING);
		logger.info(AppConstants.RUNNING);
		t.scheduleAtFixedRate(mTask, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	private void keyPressed(KeyEvent keyEvent) throws IOException {
		if (keyEvent.getCode() == KeyCode.P) {
			logger.info(AppConstants.P_KEY + AppConstants.PRESSED);
			stop();
		}
	}

	public void stop() {
		logger.info(AppConstants.STOP);
		t.shutdownNow();
		try {
			if (label != null) {
				Parent root = FXMLLoader.load(getClass().getResource(AppConstants.PATH_FXHTML));
				logger.info(AppConstants.LOADING + AppConstants.PAGE);
				new Scene(root, 400, 200);
				t = Executors.newScheduledThreadPool(1);
				label.setText(AppConstants.STOP);
				flag = false;
			} else {
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						stage.requestFocus();
						if (flag) {
							stop();
							flag = false;
						}
					}
				});
			}
		} catch (IOException e) {
			logger.error(AppConstants.PAGE_NOT_FOUND + e);
		}
	}

}
