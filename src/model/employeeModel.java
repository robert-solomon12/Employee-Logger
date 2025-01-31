package model;


/*
 * EmployeeModel Object with 6 attributes, this is the blueprint for creating multiple variants of the Employee Object.
 */
public class employeeModel {

		private Integer id;
	 	private String firstName;
	    private String surname;
	    private Integer ssNumber;
	    private Integer salary;
	    private String gender;
	    
	    
	  /*
	   * EmployeeModel Contructor initializing multiple objects of this type, this object is called when the EmployeeModel object is called.
	   */
		public employeeModel(Integer id, String firstName, String surname, Integer ssNumber, Integer salary ,String gender) {
			this.id = id;
			this.firstName = firstName;
			this.surname = surname;
			this.ssNumber = ssNumber;
			this.salary = salary;
			this.gender = gender;
		}

		/*
		 * Getters/Setters for each attributes of the object.
		 */
		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		
		public String getFirstName() {
			return firstName;
		}

		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}
		
		
		public Integer getSsNumber() {
			return ssNumber;
		}

		public void setSsNumber(Integer ssNumber) {
			this.ssNumber = ssNumber;
		}


		public Integer getSalary() {
			return salary;
		}

		public void setSalary(Integer salary) {
			this.salary = salary;
		}


		public String getGender() {
			return gender;
		}

		public void setGender(String gender) {
			this.gender = gender;
		}


		


		
		
		
	    
	    
	    
	    
    
}
