package com.automatic.click;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

import javafx.application.Application;
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

public class App extends Application{
	
	@FXML
	private Button clickButton;
	@FXML
	private Button moveButton;
	@FXML
	private Label label;
	
	final static Logger logger = Logger.getLogger(App.class);

	
	ScheduledExecutorService t = Executors.newScheduledThreadPool(1);
	TaskAutomateClick aTask = new TaskAutomateClick();
	TaskAutomateMove mTask = new TaskAutomateMove();
	

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/File.fxml"));
		logger.info("Reading: "+getClass().getResource("/File.fxml"));
		Parent root = (Parent)loader.load();
		Scene scene = new Scene(root, 400, 200);
		stage.setTitle("Automate Click");
		stage.setScene(scene);
		stage.show();
		logger.info("Fxml file read complete");
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
			
			@Override
			public void handle(WindowEvent event) {
				logger.info("The Application was closed");
			}
		});
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	protected void startClick(ActionEvent event) {
		label.setText("Running..."); 
		logger.info("Running");
		t.scheduleAtFixedRate(aTask, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	protected void startMove(ActionEvent event) {
		label.setText("Running...");
		logger.info("Running");
		t.scheduleAtFixedRate(mTask, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	private void keyPressed(KeyEvent keyEvent) throws IOException {
	    if (keyEvent.getCode() == KeyCode.P) {
	    	logger.info(keyEvent.getCode() +" pressed");
	    	System.out.println("Stop!");
	    	logger.info("Stopping");
			t.shutdown();
			Parent root = FXMLLoader.load(getClass().getResource("/File.fxml"));
			logger.info("Reloading page");
			new Scene(root, 400, 200);
			t=Executors.newScheduledThreadPool(1);
			label.setText("Stop");
	    }
	}
	
	
}

