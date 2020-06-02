package function_employees_app;

import java.math.BigDecimal;

public class Employees {
	private int id;
	private String lastName;
	private String firstName;
	private String email;
	private String departament;
	private BigDecimal salary;

	public Employees (int id,String lastName,String firstName,String email,String departament,BigDecimal salary) {
		this.id=id;
		this.lastName=lastName;
		this.firstName=firstName;
		this.email=email;
		this.departament=departament;
		this.salary=salary;
	}
	public Employees (String lastName,String firstName,String email,String departament,BigDecimal salary) {
		
		this.lastName=lastName;
		this.firstName=firstName;
		this.email=email;
		this.departament=departament;
		this.salary=salary;
	}
	public String toString() {
		return (id+" "+lastName+" "+firstName+" "+email+" "+departament+" "+salary);
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartament() {
		return departament;
	}
	public void setDepartament(String departament) {
		this.departament = departament;
	}
	public BigDecimal getSalary() {
		return salary;
	}
	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}
	
	
}
