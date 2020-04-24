package com.automatic.click;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

public class App extends Application{

	
	String teste = "Teste";
	
	@FXML
	private Button clickButton;
	@FXML
	private Button moveButton;
	@FXML
	private Label label;
	
	ScheduledExecutorService t = Executors.newScheduledThreadPool(1);
	TaskAutomateClick aTask = new TaskAutomateClick();
	TaskAutomateMove mTask = new TaskAutomateMove();
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("File.fxml"));
		Parent root = (Parent)loader.load();
		Scene scene = new Scene(root, 400, 200);
		primaryStage.setTitle("Automate Click");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	protected void startClick(ActionEvent event) {
		label.setText("Running..."); 
		t.scheduleAtFixedRate(aTask, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	protected void startMove(ActionEvent event) {
		label.setText("Running...");
		t.scheduleAtFixedRate(mTask, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	private void keyPressed(KeyEvent keyEvent) throws IOException {
	    if (keyEvent.getCode() == KeyCode.P) {
	    	System.out.println("Stop!");
			t.shutdown();
			Parent root = FXMLLoader.load(getClass().getResource("File.fxml"));
			new Scene(root, 400, 200);
			t=Executors.newScheduledThreadPool(1);
			label.setText("Stop");
	    }
	}
	
	
}

