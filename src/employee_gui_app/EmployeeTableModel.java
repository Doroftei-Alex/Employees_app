package employee_gui_app;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import function_employees_app.Employees;

public class EmployeeTableModel extends AbstractTableModel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int LS_NAME=0;
	private static final int FS_NAME=1;
	private static final int EMAIL=2;
	private static final int DEPARTAMENT=3;
	private static final int SALARY=4;
	public static final int OBJECT_COL = -1 ;
	private List<Employees> employees;
	String []columnNames= {"Last Name","First Name","Email","Departament","Salary"};
	public EmployeeTableModel(List<Employees> theEmplpyees) {
		employees=theEmplpyees;
	}
	 
			    public int getColumnCount() {
			        return columnNames.length;
			    }

			    public int getRowCount() {
			        return employees.size();
			    }

			    public String getColumnName(int col) {
			        return columnNames[col];
			    }

			    public Object getValueAt(int row, int col) {
			        Employees tempEmployees=employees.get(row);
			        
			        switch(col) {
			        case LS_NAME: 
			        	return tempEmployees.getLastName();
			        case FS_NAME:
			        	return tempEmployees.getFirstName();
			        case EMAIL:
			        	return tempEmployees.getEmail();
			        case SALARY:
			        	return tempEmployees.getSalary();
			        case DEPARTAMENT:
			        	return tempEmployees.getDepartament();
			        default:
			        	return tempEmployees;
			        }
			    }

			    public Class getColumnClass(int c) {
			        return getValueAt(0, c).getClass();
			    }

}
