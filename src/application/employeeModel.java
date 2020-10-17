package application;

public class employeeModel {

	 private String firstName;
	    private String surname;
	    private Integer salary;
	    private String gender;
	    private Integer ssNumber;
	    
	    
		public employeeModel(String firstName, String surname, Integer salary, String gender, Integer ssNumber) {
			this.firstName = firstName;
			this.surname = surname;
			this.salary = salary;
			this.gender = gender;
			this.ssNumber = ssNumber;
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


		public Integer getSsNumber() {
			return ssNumber;
		}


		public void setSsNumber(Integer ssNumber) {
			this.ssNumber = ssNumber;
		}
		
		
	    
	    
	    
	    
    
}
