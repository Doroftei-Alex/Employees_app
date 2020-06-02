package function_employees_app;

import java.io.FileInputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class EmployeesDAO {
	private Connection myConn=null;
	
	public EmployeesDAO() throws Exception{
		Properties prop=new Properties();
		
		prop.load(new FileInputStream("demo.properties"));
	
		String user=prop.getProperty("user");
		String password=prop.getProperty("password");
		String url=prop.getProperty("url");
		myConn=DriverManager.getConnection(url,user,password);
	  
}
	
	public void addEmployee(Employees empadd) throws Exception {
		PreparedStatement stmt=null;
		try {
		stmt=myConn.prepareStatement("insert into employees (last_name, first_name, email, department, salary) values (?, ?, ?, ?, ?)");
		stmt.setString(1, empadd.getLastName());
		stmt.setString(2, empadd.getFirstName());
		stmt.setString(3, empadd.getEmail());
		stmt.setString(4, empadd.getDepartament());
		stmt.setBigDecimal(5, empadd.getSalary());
		stmt.executeUpdate();
		}
		finally {
			stmt.close();
		}
	}
		public void updateEmployee(Employees empadd) throws Exception {
			System.out.println(empadd);
			PreparedStatement stmt=null;
			try {
				stmt=myConn.prepareStatement("update employees set last_name=?, first_name=?, email=?, department=?, salary=? where id=?");
				stmt.setString(1, empadd.getLastName());
				stmt.setString(2, empadd.getFirstName());
				stmt.setString(3, empadd.getEmail());
				stmt.setString(4, empadd.getDepartament());
				stmt.setBigDecimal(5, empadd.getSalary());
				stmt.setInt(6, empadd.getId());
				stmt.executeUpdate();
			}
			finally {
				stmt.close();
			}
	}
		
		public void deleteEmployee(Employees delEmp) throws Exception{
			PreparedStatement stmt= null;
			
			try {
				stmt=myConn.prepareStatement("delete from employees where id=?");
				stmt.setInt(1, delEmp.getId());
				stmt.executeUpdate();
			} finally {
				stmt.close();

			}
		}
		
    	public List<Employees> getAllEmployees(){
		List<Employees> list=new ArrayList<Employees>();
	    Statement myStmt=null;
		ResultSet myRs=null;
		
		try {
			myStmt=myConn.createStatement();
			myRs=myStmt.executeQuery("select * from employees");
			while(myRs.next()) {
				Employees thisEmployee= convertRowToEmployee(myRs);
				list.add(thisEmployee);
			}
			
		} catch(Exception e) {
			System.out.println("error getallemployees");
		}finally {
			try {
				myStmt.close();
				myRs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		
		return list;
	}
	public List<Employees> searchEmployee(String name){
		List<Employees> listS=new ArrayList<Employees>();
		PreparedStatement pStmt=null;
		ResultSet myRs=null;
		try {
			name=name+"%";
			pStmt=myConn.prepareStatement("select * from employees where last_name like ?");
		    pStmt.setString(1, name);
		    myRs=pStmt.executeQuery();
		    while(myRs.next()) {
		    	Employees emp=convertRowToEmployee(myRs);
		    	listS.add(emp);
		    }
		
		} catch (Exception e) {
			System.out.println("error searchEmployee");
		}
		finally {
			try {
				pStmt.close();
				myRs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			
		}
		
		return listS;
	}
	
	private Employees convertRowToEmployee(ResultSet myRs) throws Exception{
		int id= myRs.getInt(1);
		 String lastName=myRs.getString(2);
	     String firstName=myRs.getString(3);
		 String email=myRs.getString(4);
		 String departament=myRs.getString(5);
		 BigDecimal salary=myRs.getBigDecimal(6);
		 Employees emp=new Employees(id, lastName, firstName, email, departament, salary);
		 
		return emp;
	}
	
	
	
}
