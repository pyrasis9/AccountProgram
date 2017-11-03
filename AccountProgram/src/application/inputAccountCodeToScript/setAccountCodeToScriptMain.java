package application.inputAccountCodeToScript;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;



public class setAccountCodeToScriptMain {
	

	//@Override
	public setAccountCodeToScriptMain(Stage primaryStage, Parent root, Scene scene) {
		
		try {
			root = FXMLLoader.load(getClass().getResource("/application/inputAccountCodeToScript/setAccountCodeToScript.fxml"));
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			scene = new Scene(root,1024,768);
			scene.setRoot(root);
			primaryStage.setScene(scene);			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//launch(args);
	}

	
}

/*
public class setAccountCodeToScriptMain extends Application {
	
	
	
	@Override
	public void start(Stage primaryStage) {
			
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/application/inputAccountCodeToScript/setAccountCodeToScript.fxml"));
			primaryStage.initStyle(StageStyle.UNDECORATED);
			Scene scene = new Scene(root,1024,768);
			//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
*/