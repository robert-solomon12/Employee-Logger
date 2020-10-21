package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import view.SceneLoader;

import java.io.IOException;

public class FirstPageController {
    
	@FXML
    private Button nextButton;
	
	@FXML
	private Button exitButton;
	
	
	/*
	 * Method call to move to the next Scene which is already loaded.
	 */
    @FXML
    private void sceneChangeToMain(ActionEvent event) throws IOException {   	
        Stage first_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        first_stage.setScene(SceneLoader.scene2);
        first_stage.show();
    }
    
    /*
     * Method call to close Stage (Program)
     */
    public void sceneCloseProgram(ActionEvent event) {
        Stage firstStage = (Stage) exitButton.getScene().getWindow();
        firstStage.close();
        System.out.println("Shutting down Program, Goodbye!!");
        
    }
    
    
}
