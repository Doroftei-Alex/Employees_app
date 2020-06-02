package employee_gui_app;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import function_employees_app.Employees;
import function_employees_app.EmployeesDAO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import function_employees_app.*;

public class employees_search_app extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField jtextNume;
	private JTable table;
    private EmployeesDAO employeesDAO;
   
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					employees_search_app frame = new employees_search_app();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public employees_search_app() {
	
			try {
				employeesDAO=new EmployeesDAO();
			} catch (Exception e) {
				JOptionPane.showConfirmDialog(this,"Error "+e,"Error",JOptionPane.ERROR_MESSAGE);
			}
		
		setTitle("Employee search app");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 377);
		contentPane = new JPanel();
		contentPane.setToolTipText("");
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPane.add(panel, BorderLayout.NORTH);
		
		JLabel label = new JLabel("Enter last name");
		panel.add(label);
		
		jtextNume = new JTextField();
		jtextNume.setFont(new Font("Tahoma", Font.ITALIC, 13));
		jtextNume.setSize(20,50 );
		panel.add(jtextNume);
		jtextNume.setColumns(10);
		
		JButton buttonSearch = new JButton("Search");
		buttonSearch.setForeground(Color.BLACK);
		buttonSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text_lastName=jtextNume.getText();
				List<Employees> lista=null;
				
				if((text_lastName!=null) && (text_lastName.trim().length()>0)) {
					lista=employeesDAO.searchEmployee(text_lastName);
				}else {
					lista=employeesDAO.getAllEmployees();
				}
				EmployeeTableModel model=new EmployeeTableModel(lista);
				table.setModel(model);
			}
		});
		panel.add(buttonSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JPanel panel_1 = new JPanel();
		contentPane.add(panel_1, BorderLayout.SOUTH);
		
		JButton buttonAddEmployee = new JButton("Add Employee");
		buttonAddEmployee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Employees theemp=new Employees(0, "lastName", "firstName", "email", "departament", new BigDecimal(500));
				DialogAdd dialog=new DialogAdd(employees_search_app.this,employeesDAO,theemp,false);
				dialog.setVisible(true);
			}
		});
		panel_1.add(buttonAddEmployee);
		
		JButton updButton = new JButton("Update Employee");
		updButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				int r=table.getSelectedRow();
				
				if(r<0) {
					JOptionPane.showConfirmDialog(employees_search_app.this, "You must select employee","info",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				 Employees employeeGetRow=(Employees) table.getValueAt(r, EmployeeTableModel.OBJECT_COL);
						
				 
			    DialogAdd updateDialog=new DialogAdd(employees_search_app.this,employeesDAO,employeeGetRow,true);
				updateDialog.setVisible(true);
			}
		});
		panel_1.add(updButton);
		
		JButton deleteButton = new JButton("Delete Employee");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                   int r=table.getSelectedRow();
				
				if(r<0) {
					JOptionPane.showConfirmDialog(employees_search_app.this, "You must select employee","info",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
			   Employees employeeGetRow=(Employees) table.getValueAt(r, EmployeeTableModel.OBJECT_COL);
				
				int rez = JOptionPane.showConfirmDialog(employees_search_app.this,"Delete this employee?");
				if(rez==JOptionPane.YES_OPTION) {
					try {
						employeesDAO.deleteEmployee(employeeGetRow);
						refreshEmployeeView();
					} catch (Exception e) {
						JOptionPane.showMessageDialog(employees_search_app.this, "Error"+e,"Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		panel_1.add(deleteButton);
		refreshEmployeeView();
	}

	public  void refreshEmployeeView() {
		String txt_lastName=jtextNume.getText();
		List<Employees> lista=null;
		if((txt_lastName!=null) && (txt_lastName.trim().length()>0)) {
			lista=employeesDAO.searchEmployee(txt_lastName);
		}else {
			lista=employeesDAO.getAllEmployees();
		}
		EmployeeTableModel model=new EmployeeTableModel(lista);
		table.setModel(model);
	}
	
		
		
	
	
}
