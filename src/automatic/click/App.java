package automatic.click;

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
import javafx.stage.Stage;

public class App extends Application {

	@FXML
	private Button clickButton;
	@FXML
	private Button cancelButton;
	public int flag;

	ScheduledExecutorService t = Executors.newScheduledThreadPool(1);
	TaskAutomateClick mTask = new TaskAutomateClick();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("File.fxml"));
		primaryStage.setTitle("Automate Click");
		primaryStage.setScene(new Scene(root, 350, 100));
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}

	@FXML
	protected void startCommand(ActionEvent event) {
		t.scheduleAtFixedRate(mTask, 0, 1, TimeUnit.SECONDS);
	}

	@FXML
	protected void stopCommand(ActionEvent event) {
		System.out.println("Stop");
		t.shutdown();
		t.shutdownNow();
	}

}
