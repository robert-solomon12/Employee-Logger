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
	public TextField consoleFeedback;
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
	
	private final String userName = "root";
	/** The password for the MySQL account (or empty for anonymous) */
	private final String password = "admin";
	/** The name of the computer running MySQL */
	private final String serverName = "localhost";
	/** The port of the MySQL server (default is 3306) */
	private final int portNumber = 3306;
	/** The name of the database we are testing with (this default is installed with MySQL) */
	private final String tableName = "testemployeeinfos";
	
	private final String dbName = "test";
	

	/*Initializer to always call the showEmployees method below when user enters the main view
	 *or even when the user closes the program with existing users in the database, users will persist and always be displayed unless they are deleted.
	 */
		@Override
		public void initialize(URL arg0, ResourceBundle arg1) {
			// TODO Auto-generated method stub
		try {
			showEmployees();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
	
	
	/*
	 * Connection method call to execute connection to my WAMP Server Database
	 */
	public Connection getConnection() throws SQLException {
		
     	Connection conn;

		try {
			
			/*
			 * Connection String containing 3 parameters to establish connectivity to my Database WAMP SERVER (URL, USERNAME, PASSWORD)
			 */
			conn = DriverManager.getConnection("jdbc:mysql://" + this.serverName + ":" + this.portNumber + "/" +this.dbName+"?serverTimezone=UTC",this.userName,this.password);
				
			//Printing a successful connection message if a successful connection has been established. 
			System.out.println("Connection to Database has been successfully established!");
			consoleFeedback.setText("Connection to Database has been successfully established!");
			return conn;
		}catch (Exception e) {
			System.out.println("Error: " + e.getMessage());
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
	
	
	/*
	 * Method call to list the existing employees in the Observable list by calling the getEmployeeList  
	 */
	public void showEmployees() throws SQLException {
		ObservableList<employeeModel> list = getEmployeeList();
		
		/*Observable list populated in table view by taking two arguments, the object model, employeeModel and the
		 * variable type of the each attribute of the object model
		 */
		idColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, Integer>("id"));
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, String>("firstName"));
		surnameColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, String>("surname"));
		ssNumberColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, Integer>("ssNumber"));
		salaryColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, Integer>("salary"));
		genderColumn.setCellValueFactory(new PropertyValueFactory<employeeModel, String>("gender"));
		
		//Setting the items in the list by taking the observablelist object type called 'list' to set the items and pass them to the respective columns.
		tableFormView.setItems(list);
	}

	/*
	 * Insertion method function to execute the 'INSERT INTO' SQL Command into the employeeinfos table,
	 * in this case to allow the user to insert input records with the click of a button calling this function. 
	 */
	@FXML
	private void insertRecB(ActionEvent event) throws SQLException {
		
		
//		try {	
		//Insertion query method 'INSERT INTO' table which is defined above as a variable called 'tablename' and concatinated below   
		String query = "INSERT INTO " +this.tableName +" VALUES (" + idTf.getText() + ",'" + firstNameTf.getText()
		+ "','" + surnameTf.getText() + "'," + salaryTf.getText() + "," + ssNumberTf.getText() + ",'" + genderTf.getText() + "')";
		//specifies and calls the query method
		executeQuery(query);
		showEmployees();
		consoleFeedback.setText("Record has been successfully inserted to our Database!");
//		} catch (SQLException e) {
			// TODO: handle exception
//			System.out.println("Invalid!, please enter a valid record!");
//			consoleFeedback.setText("Invalid record, could not store in to Database!");
		}
	
	
	/*
	 * Update method function to execute the 'UPDATE' and 'SET' SQL Command in the employeeinfos table where ID is selected,
	 * in this case to allow the user to update existing records based on ID Number and then with the click of a button calling this function.
	 */
	@FXML
	private void updateRecB(ActionEvent event) throws SQLException {
		
	//	try {
			
		String query = "UPDATE " + this.tableName +" SET FIRSTNAME = '" + firstNameTf.getText()
		+ "', SURNAME = '" + surnameTf.getText() + "', SALARY = " + salaryTf.getText() + ", SSNUMBER = " + ssNumberTf.getText() + ", GENDER = '" + genderTf.getText() + "' WHERE ID = " + idTf.getText() +"";
	    executeQuery(query);
	    showEmployees();
	    System.out.println("Record has been successfully updated in our Database!");
	    consoleFeedback.setText("Record has been successfully updated in our Database!");
//		} catch (SQLException e) {
//			System.out.println("Could not update record, please try again!");
//			consoleFeedback.setText("Could not update record, please try again!");
//			// TODO: handle exception
//		}
	}
	
	/*
	 * Deletion method call to execute the 'DELETE FROM' SQL Command from the employeeinfos table where the ID is selected, in this case
	 * to allow the user to delete the selected record existing
	 * in the tableView and remove it from the Database in WAMP Server once the server is refreshed.
	 */
	@FXML
	private void deleteRecB(ActionEvent event) throws SQLException {
		
//		try {
		
		String query = "DELETE FROM " +this.tableName +" WHERE ID =" + idTf.getText() + "";
		executeQuery(query);
		showEmployees();
		System.out.println("Record has been successfully deleted from our Database!");
		consoleFeedback.setText("Record has been successfully deleted from our Database!");
//	} catch (SQLException e) {
		// TODO: handle exception
//		System.out.println("Could not update record, please try again!");
	//	consoleFeedback.setText("Could not update record, please try again!");
	}
	
	/*
	 * MouseEvent method call to populate and display the selected record displayed in the Table in the Textfield 
	 */
	@FXML
	private void handleMouseE(MouseEvent event) throws Exception{
	 employeeModel epMouseE = tableFormView.getSelectionModel().getSelectedItem(); //employeeModel object to get selected item on the table list
	 try {
		
	 /*
	  * Item selected in the Tableview referenced back to the employee object created above 'epMouseE' sets the Text back
	  * to the Textfield to give the user feedback.
	  */
	 idTf.setText(""+ epMouseE.getId());
	 firstNameTf.setText("" +epMouseE.getFirstName());
	 surnameTf.setText(""+epMouseE.getSurname());
	 ssNumberTf.setText(""+epMouseE.getSsNumber());
	 salaryTf.setText(""+epMouseE.getSalary());
	 genderTf.setText(""+epMouseE.getGender());
	}
	 catch (Exception e) {
			// TODO: handle exception
		 System.out.println("Can't select row, field is empty!");
		 consoleFeedback.setText("Can't select row, field is empty!");
		}
	}
	
	/*
	 * Shared query command which is called upon execution and does not return a recordset:
	 * CREATE/INSERT/UPDATE/DELETE/DROP/etc.
	 * Query updater function called upon execution of the insertion method to establish a connection method to localhost server and execute query call (INSERT/UPDATE/DELETE)
	 */
	public void executeQuery(String query) throws SQLException {
		Connection conn = getConnection();
		Statement st;
		try {
			st = conn.createStatement();
			st.executeUpdate(query); 
		} catch (SQLException ex) // This will throw a SQLException if it fails
		{
			System.out.println("Error has occurred while query has been called"+ex); //handles sql exception error and prints a more valid error trace to developer 
//			ex.printStackTrace();
//			throw ex;
			// TODO: handle exception
		}
	}
	
	/*
	 * method call to return to previous stage (scene/window)
	 */
	@FXML
	private void returnSceneChange(ActionEvent event) throws IOException {

		Stage ep_stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		ep_stage.setScene(SceneLoader.scene1); 
		ep_stage.show();
	}
}
	
