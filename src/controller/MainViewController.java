package controller;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.employeeModel;
import view.SceneLoader;

public class MainViewController implements Initializable {

	@FXML
	private Button insertB;
	@FXML
	private Button deleteB;
	@FXML
	private Button updateB;
	@FXML
	private Button returnB;
	@FXML
	private Button showB;
	@FXML
	public TextField idTf;
	@FXML
	public TextField firstNameTf;
	@FXML
	public TextField surnameTf;
	@FXML
	public TextField ssNumberTf;
	@FXML
	public TextField salaryTf;
	@FXML
	public TextField genderTf;
	@FXML
	private ObservableList<employeeModel> employeeLists = FXCollections.observableArrayList();
	@FXML
	private TableView <employeeModel> tableFormView;
	@FXML
	private TableColumn<employeeModel, Integer> idColumn;
	@FXML
	private TableColumn <employeeModel,String> firstNameColumn;
	@FXML
	private TableColumn<employeeModel,String> surnameColumn;
	@FXML
	private TableColumn<employeeModel,Integer> ssNumberColumn;
	@FXML
	private TableColumn<employeeModel,Integer> salaryColumn;
	@FXML
	private TableColumn<employeeModel,String> genderColumn;
	
	private final String tableName = "testemployeeinfos";
	
	
	//Handler to execute the insertion method below
	@FXML
	private void handleButtonA(ActionEvent event) throws SQLException {
		System.out.println("Processing Event Handler!");
		if(event.getSource() == insertB) {
			insertRecB();
			System.out.println("Executing insertion Action Event, record inserted!");
		}else if (event.getSource() == updateB){
			updateRecB();
			System.out.println("Executing update Action Event, record updated!");
		}else if (event.getSource() == deleteB) {
			deletionRecB();
			System.out.println("Executing deletion Action Event, record deleted!");
		}
	}

	
	
	public void initialize() throws SQLException {
//		createTable();
		showEmployees();
	}

	
	public Connection getConnection() throws SQLException {
		
     	Connection conn;

     	
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/testemployeedb?serverTimezone=UTC","root","admin");
					
			return conn;
		}catch (Exception ex) {
			System.out.println("Error: " + ex.getMessage());
			return null;
		}
		// TODO: handle exception
	}

	public ObservableList<employeeModel> getEmployeeList() throws SQLException {
		ObservableList<employeeModel> employeeList =FXCollections.observableArrayList();
		Connection conn = getConnection();
		String query = "SELECT * FROM "+this.tableName;
		Statement stmt;
		ResultSet rs;

		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			employeeModel employees;
			while(rs.next()) {
				employees = new employeeModel(rs.getInt("id"), rs.getString("firstName"), rs.getString("surname"), rs.getInt("ssNumber"), rs.getInt("salary"), rs.getString("gender"));
				employeeList.add(employees);
			}
		} catch (Exception ex) {
			// TODO: handle exception
			ex.printStackTrace();
		}
		return employeeList;
	}
	
	public void showEmployees() throws SQLException {
		ObservableList<employeeModel> list = getEmployeeList();
		
		
		idColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, Integer>("id"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, String>("firstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, String>("surname"));
		ssNumberColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, Integer>("ssNumber"));
		salaryColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, Integer>("salary"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, String>("gender"));
		
		tableFormView.setItems(list);
	}


	//insertion method function to execute 
	//@FXML
	private void insertRecB() throws SQLException {

		String query = "INSERT INTO " +this.tableName +" VALUES (" + idTf.getText() + ",'" + firstNameTf.getText()
		+ "','" + surnameTf.getText() + "'," + salaryTf.getText() + "," + ssNumberTf.getText() + ",'" + genderTf.getText() + "')";
		executeQuery(query);
		showEmployees();
	}
	
	
	//@FXML
	private void updateRecB() throws SQLException {
		
		try {
			
		String query = "UPDATE testemployeeinfos SET FIRSTNAME = '" + firstNameTf.getText()
		+ "', SURNAME = '" + surnameTf.getText() + "', SALARY = " + salaryTf.getText() + ", SSNUMBER = " + ssNumberTf.getText() + ", GENDER = '" + genderTf.getText() + "' WHERE ID = " + idTf.getText() +"";
	    executeQuery(query);
	    showEmployees();
		} catch (Exception e) {
			System.out.println("Can't Click here, please enter a value!");
			// TODO: handle exception
		}
	}
	
	//@FXML
	private void deletionRecB() throws SQLException {

		String query = "DELETE FROM testemployeeinfos WHERE ID =" + idTf.getText() + "";
		executeQuery(query);
		showEmployees();
	}
	
	@FXML
	private void handleMouseE(MouseEvent event) {
	 employeeModel epMouseE = tableFormView.getSelectionModel().getSelectedItem();
//	 System.out.println("id " + epMouseE.getId());
//	 System.out.println("firstName " +epMouseE.getFirstName());
	 idTf.setText(""+ epMouseE.getId());
	 firstNameTf.setText("" +epMouseE.getFirstName());
	 surnameTf.setText(""+epMouseE.getSurname());
	 ssNumberTf.setText(""+epMouseE.getSsNumber());
	 salaryTf.setText(""+epMouseE.getSalary());
	 genderTf.setText(""+epMouseE.getGender());
	 
	}
	
	/**
	 * Shared query command which is called upon execution and does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * 
	 * @throws SQLException If something goes wrong
	 */
	//Query updater function called upon execution of the insertion method to establish a connection method to localhost server and execute query
	public void executeQuery(String query) throws SQLException {
		Connection conn = getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(query); 
		} catch (Exception ex) // This will throw a SQLException if it fails
		{
			ex.printStackTrace();
			// TODO: handle exception
		}
	}
	
	
	@FXML
	private void returnSceneChange(ActionEvent event) throws IOException {

		Stage book_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		book_stage.setScene(SceneLoader.scene1); 
		book_stage.show();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub

	}	
}
