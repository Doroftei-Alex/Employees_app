package employee_gui_app;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;


import function_employees_app.EmployeesDAO;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import java.awt.event.ActionEvent;
import function_employees_app.*;

public class DialogAdd extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textLastName;
	private JTextField textFirstName;
	private JTextField textEmail;
	private JTextField textDepartament;
	private JTextField textSalary;
    private EmployeesDAO employeeD;
    private employees_search_app employeeApp;
    private Employees employeesInTxt;
    private boolean update;
    private JButton saveButton;
	/**
	 * Launch the application.
	 */
	public DialogAdd(employees_search_app e,EmployeesDAO em,Employees emp,boolean up) {
		this();
		employeeApp=e;
		employeeD=em;
		employeesInTxt=emp;
		update=up;
		if(update==true) {
		       
		
			saveButton.setText("Update");
			textLastName.setText(employeesInTxt.getLastName());
			textFirstName.setText(employeesInTxt.getFirstName());
			textEmail.setText(employeesInTxt.getEmail());
			textDepartament.setText(employeesInTxt.getDepartament());
			textSalary.setText(employeesInTxt.getSalary().toString());	
			
		}
	}

	/**
	 * Create the dialog.
	 */
	public DialogAdd() {
		setForeground(Color.WHITE);
		setTitle("Add Employee");
		setBackground(Color.WHITE);
		setResizable(false);
		setBounds(100, 100, 490, 350);
		getContentPane().setLayout(new BorderLayout());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.DARK_GRAY);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				saveButton = new JButton("Save");
				saveButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						saveButton(update);
						
					}
				});
				saveButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				saveButton.setActionCommand("OK");
				buttonPane.add(saveButton);
				getRootPane().setDefaultButton(saveButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 13));
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		{
			JPanel panel = new JPanel();
			panel.setForeground(Color.BLACK);
			panel.setBackground(Color.GRAY);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setVgap(20);
			flowLayout.setHgap(10);
			getContentPane().add(panel, BorderLayout.CENTER);
			{
				JLabel lblNewLabel = new JLabel("Last Name");
				lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel.setForeground(Color.WHITE);
				lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel.setPreferredSize(new Dimension(81, 14));
				panel.add(lblNewLabel);
			}
			{
				textLastName = new JTextField();
				panel.add(textLastName);
				textLastName.setColumns(27);
				textLastName.setPreferredSize(new Dimension(0, 25));
			}
			{
				JLabel lblNewLabel_1 = new JLabel("First Name");
				lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
				lblNewLabel_1.setForeground(Color.WHITE);
				lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_1.setPreferredSize(new Dimension(81, 14));
				panel.add(lblNewLabel_1);
			}
			{
				textFirstName = new JTextField();
				textFirstName.setPreferredSize(new Dimension(0, 25));
				panel.add(textFirstName);
				textFirstName.setColumns(27);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("Email");
				lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_2.setForeground(Color.WHITE);
				lblNewLabel_2.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_2.setHorizontalTextPosition(SwingConstants.LEADING);
				lblNewLabel_2.setPreferredSize(new Dimension(81, 14));
				panel.add(lblNewLabel_2);
			}
			{
				textEmail = new JTextField();
				textEmail.setPreferredSize(new Dimension(0, 25));
				panel.add(textEmail);
				textEmail.setColumns(27);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("Departament");
				lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_3.setForeground(Color.WHITE);
				lblNewLabel_3.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_3.setPreferredSize(new Dimension(81, 14));
				panel.add(lblNewLabel_3);
			}
			{
				textDepartament = new JTextField();
				
				
				textDepartament.setPreferredSize(new Dimension(0, 25));
				panel.add(textDepartament);
				textDepartament.setColumns(27);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("Salary");
				lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 12));
				lblNewLabel_4.setForeground(Color.WHITE);
				lblNewLabel_4.setHorizontalAlignment(SwingConstants.RIGHT);
				lblNewLabel_4.setPreferredSize(new Dimension(81, 14));
				panel.add(lblNewLabel_4);
			}
			{
				textSalary = new JTextField();
				textSalary.setPreferredSize(new Dimension(0, 25));
				panel.add(textSalary);
				textSalary.setColumns(27);
			}
		}
	}
	

	protected void saveButton(boolean b)  {
		
		
		String lName=textLastName.getText();
		String fName=textFirstName.getText();
		String email=textEmail.getText();
		String dep=textDepartament.getText();
		BigDecimal salary=new BigDecimal(textSalary.getText());
		Employees tEmploye=new Employees(lName, fName, email, dep, salary);
		tEmploye.setId(employeesInTxt.getId());
		String nameF=null;
		try {
			String s=null;
			if(b) {
				
				employeeD.updateEmployee(tEmploye);
				 s="Employee updated succesfull";
				nameF="Employee updated";
			}else {
			  employeeD.addEmployee(tEmploye);
			   s="Employee added succesfull";
			   nameF="Employee added";
			   
			}
			setVisible(false);
			employeeApp.refreshEmployeeView();
			JOptionPane.showMessageDialog(employeeApp,s ,nameF,JOptionPane.INFORMATION_MESSAGE);
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(employeeApp, "Error saving employee "+e,"Error",JOptionPane.INFORMATION_MESSAGE);
		}
			
		
		
	}

}
