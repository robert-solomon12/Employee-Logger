package application;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainViewController {
	
	@FXML
	private Button insertB;
	@FXML
	private Button deleteB;
	@FXML
	private Button updateB;
	@FXML
	private Button returnB;
	@FXML
	private TextField firstNameTf;
	@FXML
	private TextField surnameTf;
	@FXML
	private TextField ssNumberTf;
	@FXML
	private TextField salaryTf;
	@FXML
	private TextField genderTf;
	@FXML
    private ObservableList<employeeModel> employeeLists = FXCollections.observableArrayList();
    @FXML
	private TableView <employeeModel> tableFormView;
	@FXML 
	private TableColumn <employeeModel,String> firstNameColumn;
	@FXML
	private TableColumn<employeeModel,String> surnameColumn;
	@FXML
	private TableColumn<employeeModel,Integer> ssNumberColumn;
	@FXML
	private TableColumn<employeeModel,String> salaryColumn;
	@FXML
	private TableColumn<employeeModel,String> genderColumn;
	
	
	
	
	@FXML
	public void initialize() {
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
		ssNumberColumn.setCellValueFactory(new PropertyValueFactory<>("SsNumber"));
		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		tableFormView.setItems(observableList);
	}
	
	private ObservableList<employeeModel> observableList=FXCollections.observableArrayList(
			new employeeModel("Robert", "Solomon", 50000, "Male", 234384),
			new employeeModel("Choice", "Solomon", 45000, "Female", 235434),
			new employeeModel("Sarah", "McCartty", 40000, "Female", 245384)
			
			);
//        return observableList;
    
	@FXML
    private void addEmployee(ActionEvent event) {
        employeeModel employM = new employeeModel(firstNameTf.getText(), surnameTf.getText(), Integer.parseInt(salaryTf.getText()), genderTf.getText(), Integer.parseInt(ssNumberTf.getText()));
        tableFormView.getItems().add(employM);
    }

	
	@FXML
    private void removeEmployee(ActionEvent event) {
      
        ObservableList<employeeModel> em = tableFormView.getSelectionModel().getSelectedItems();
        tableFormView.getItems().removeAll(em);
        employeeLists.remove(em);
    }
	
	
	
    @FXML
    private void returnSceneChange(ActionEvent event) throws IOException {
    	
        Stage book_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        book_stage.setScene(SceneLoader.scene1); 
        book_stage.show();
    }	
}
