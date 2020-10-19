package application;

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
	//	/** The name of the table we are testing with */
	//	String tableName = "EMPLOYEES";

//	/** The name of the MySQL account to use (or empty for anonymous) */
//	private final String userName = "root";
//	/** The password for the MySQL account (or empty for anonymous) */
//	private final String password = "admin";
//	/** The name of the computer running MySQL */
//	private final String serverName = "localhost";
//	/** The port of the MySQL server (default is 3306) */
//	private final int portNumber = 3306;
//	/** The name of the database we are testing with (this default is installed with MySQL) */
//	private final String dbName = "testemployeedb";
	


	public void initialize() throws SQLException {
//		createTable();
		showEmployees();
	}
		//		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("FirstName"));
		//		surnameColumn.setCellValueFactory(new PropertyValueFactory<>("Surname"));
		//		ssNumberColumn.setCellValueFactory(new PropertyValueFactory<>("SsNumber"));
		//		salaryColumn.setCellValueFactory(new PropertyValueFactory<>("Salary"));
		//		genderColumn.setCellValueFactory(new PropertyValueFactory<>("Gender"));
		//		tableFormView.setItems(getEmployeeList());
	
	
	//			new employeeModel("Robert", "Solomon", 50000, "Male", 234384),
	//			new employeeModel("Choice", "Solomon", 45000, "Female", 235434),
	//			new employeeModel(firstNameTf,surnameTf, ssNumberTf,salaryTf,genderTf));


	

	public Connection getConnection() throws SQLException {
		
		
     	Connection conn;
//		Properties connectionProps = new Properties();
//		connectionProps.put("user", this.userName);
//		connectionProps.put("password", this.password);
//     	"?serverTimezone=UTC"
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


	//Initialize createTable method in the initializion function above ???
	
//	 private void createTable() {
//		 
//		 Connection conn = null;
//		 Statement stmt = null;
//			try {
//				conn = this.getConnection();
//				System.out.println("Connected to database");
//			} catch (SQLException e) {
//				System.out.println("ERROR: Could not connect to the database");
//				e.printStackTrace();
//				return;
//			}
////	// Create a table
//				try {
//	
//				    String createString =
//					        "CREATE TABLE " + this.tableName + " ( " +
//					        "ID INTEGER NOT NULL, " +
//					        "FIRST_NAME varchar(10) NOT NULL, " +
//					        "SURNAME varchar(10) NOT NULL, " +
//					        "SALARY INTEGER(6) NOT NULL, " +
//					        "S.S.NUMBER INTEGER(6) NOT NULL, " +
//					        "GENDER char(5), " +
//					        "PRIMARY KEY (ID))";
//					stmt.executeUpdate(createString);
//					
//					System.out.println("Created a table");
//			    } catch (SQLException e) {
//					System.out.println("ERROR: Could not create the table");
//					e.printStackTrace();
//					return;
//				}
//}
				
	
				// Drop the table
//				try {
//				    String dropString = "DROP TABLE " + this.tableName;
//					this.executeUpdate(conn, dropString);
//					System.out.println("Dropped the table");
//			    } catch (SQLException e) {
//					System.out.println("ERROR: Could not drop the table");
//					e.printStackTrace();
//					return;
//				}
				
	
	//insertion method function to execute 
	//@FXML
	private void insertRecB() throws SQLException {
//		employeeModel employM = new employeeModel(firstNameTf.getText(), surnameTf.getText(), Integer.parseInt(salaryTf.getText()), genderTf.getText(), Integer.parseInt(ssNumberTf.getText()));
//		tableFormView.getItems().add(employM);
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
	
	
	
	
	
//	 private void createTable() {
	// Create a table
//				try {
//				    String createString =
//					        "CREATE TABLE " + this.tableName + " ( " +
//					        "ID INTEGER NOT NULL, " +
//					        "FIRST_NAME varchar(10) NOT NULL, " +
//					        "SURNAME varchar(10) NOT NULL, " +
//					        "SALARY INTEGER(6) NOT NULL, " +
//					        "S.S.NUMBER INTEGER(6) NOT NULL, " +
//					        "GENDER char(5), " +
//					        "PRIMARY KEY (ID))";
//					this.executeUpdate(conn, createString);
//					System.out.println("Created a table");
//			    } catch (SQLException e) {
//					System.out.println("ERROR: Could not create the table");
//					e.printStackTrace();
//					return;
//				}
//}
	
	

	/**
	 * Connect to MySQL and do some stuff.
	 */




//	@FXML
//	private void removeEmployee(ActionEvent event) {
//
//		ObservableList<employeeModel> em = tableFormView.getSelectionModel().getSelectedItems();
//		tableFormView.getItems().removeAll(em);
//		employeeLists.remove(em);
//	}



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
