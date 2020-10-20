package view;
	
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;



public class SceneLoader extends Application {
	
	
    public static Scene scene1, scene2;
    
	@Override
	public void start(Stage primaryStage) throws Exception {

	 try {
		 
         loadAllScenes();
         
         
         
         primaryStage.setScene(scene1);
         primaryStage.show();
     } catch (Exception e) {
         e.printStackTrace();
     }
 }
	
	
	
 public void loadAllScenes() {
     Parent root;
     try {
    	 
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
//		/** The name of the table we are testing with */
//		String tableName = "EMPLOYEES";
			
	
}

}
//try {
//Parent root = FXMLLoader.load(getClass().getResource("Main.fxml"));
//Scene scene = new Scene(root, 400, 400);
//primaryStage.setTitle("WIT Database");
//primaryStage.setScene(scene);
//primaryStage.show();
//} catch(Exception e) {
//e.printStackTrace();
//}
//}
//