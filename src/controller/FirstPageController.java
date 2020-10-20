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
    private void sceneChangeToMain(ActionEvent event) throws IOException {


    	
        Stage first_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        first_stage.setScene(SceneLoader.scene2);
        first_stage.show();

    }
}
