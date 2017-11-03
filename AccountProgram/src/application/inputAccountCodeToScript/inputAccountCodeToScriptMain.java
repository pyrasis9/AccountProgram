package application.inputAccountCodeToScript;
	
import java.io.IOException;

import application.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class inputAccountCodeToScriptMain {
	

	//@Override
	public inputAccountCodeToScriptMain(Stage primaryStage, Parent root, Scene scene) {
		
		try {
			root = FXMLLoader.load(getClass().getResource("/application/inputAccountCodeToScript/inputAccountCodeToScript.fxml"));
			//primaryStage.initStyle(StageStyle.UNDECORATED);
			scene = new Scene(root,1024,768);
			scene.setRoot(root);
			primaryStage.setScene(scene);
			
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();		
		}

	}
	/*
	public static void main(String[] args) {
		//launch(args);
	}
*/

	
}
