package view;



/*
  * Author: Robert Solomon
 */
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class SceneLoader extends Application {
	
	//Variable definition for Scene 1 and 2
    public static Scene scene1, scene2;
    
    //Method to start and launch Scene
	@Override
	public void start(Stage primaryStage) throws Exception {

		
		/*
		 * Try/Catch exception handler to load scene and/or print error if scenes crashes
		 */
	 try {
		 
         loadAllScenes();
         
         primaryStage.setScene(scene1);
         
         //setting window title
         primaryStage.setTitle("Java-JDBC-Assignment-1");
         primaryStage.show();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
	
	
	/*
	 * Method call launched at main (by public void start) to load all scenes in the backend during Runtime
	 */
 public void loadAllScenes() {
     Parent root;
     
     /*
      * try/catch to load up all scenes or print error if something goes wrong.
      */
     try {
    	 
    	 //Setting and loading up all scenes
         root = FXMLLoader.load(getClass().getResource("FirstPage.fxml"));
        
         scene1 = new Scene(root);
         scene1.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
         root = FXMLLoader.load(getClass().getResource("MainView.fxml"));
         scene2 = new Scene(root);
         
     } catch (IOException e) {
         // TODO Auto-generated catch block
         e.printStackTrace();
     }
     
     

 }	
	
	public static void main(String[] args) {
		launch(args);
				
}

}
