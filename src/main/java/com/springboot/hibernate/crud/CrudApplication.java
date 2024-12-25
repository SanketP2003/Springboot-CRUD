package com.springboot.hibernate.crud;

import com.springboot.hibernate.crud.dao.EmployeeDAO;
import com.springboot.hibernate.crud.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}

	private int empid;
	private String firstname;
	private String lastname;
	private int salary;
	private String emailid;

	Scanner sc = new Scanner(System.in);

	@Bean
	public CommandLineRunner commandLineRunner(EmployeeDAO employeeDAO) {
		return args -> {

			while (true) {
				System.out.println("1.Add Employee");
				System.out.println("2.Add Multiple Employee");
				System.out.println("3.Search Employee");
				System.out.println("4.See all Employee records");
				System.out.println("5.Update Employee");
				System.out.println("6.Delete Employee");
				System.out.println("7.Delete all records");
				System.out.println("8.Exit"+"\n");
				System.out.println("Enter your choice");
				int ch = sc.nextInt();
				switch (ch) {
					case 1:
						createEmployee(employeeDAO);
						break;
					case 2:
						createMultipleEmployee(employeeDAO);
						break;
					case 3:
						readEmployee(employeeDAO);
						break;
					case 4:
						seeAllRecords(employeeDAO);
						break;
					case 5:
						updateTheEmployee(employeeDAO);
						break;
					case 6:
						deleteTheEmployee(employeeDAO);
						break;
					case 7:
						deletingAll(employeeDAO);
						break;
				}
				if (ch == 8) {
					System.out.println("Exiting the loop.");
					break; // Exit the loop
				}
			}

			sc.close();
			System.out.println("Program has ended.");
		};
	}

	private void deletingAll(EmployeeDAO employeeDAO) {

		try{
			System.out.println("Deleting all employees");
			int numRowsDeleted = employeeDAO.deleteAll();
			System.out.println("Deleted rows count : " + numRowsDeleted+"\n");
		}
		catch (Exception e){
			System.out.println("something went wrong");
		}
	}

	private void deleteTheEmployee(EmployeeDAO employeeDAO) {

		try{
			System.out.println("Enter the employee id : ");
			empid = sc.nextInt();
			System.out.println("Deleting employee Id : " + empid);
			employeeDAO.delete(empid);
		}
		catch(Exception e){
			System.out.println("Entered employee id is not valid"+"\n");
		}
	}

	private void updateTheEmployee(EmployeeDAO employeeDAO) {

		try{
			System.out.println("1.Updating employee firstname");
			System.out.println("2.Updating employee lastname");
			System.out.println("3.Updating employee salary");
			System.out.println("4.Updating employee emailid");
			System.out.println("5.Updating whole record."+"\n");
			System.out.println("Enter the choice : ");
			int ch = sc.nextInt();
			switch (ch) {
				case 1:
					System.out.println("Enter the Employee Id : ");
					empid = sc.nextInt();
					Employee emp1 = employeeDAO.findByEmployeeId(empid);
					if (emp1 == null){
						System.out.println("Wrong ID .... ");
					}
					else{
						System.out.println("Current First Name of Employee : "+emp1.getFirstname());
						System.out.println("Enter New First Name : ");
						firstname = sc.next();
						emp1.setFirstname(firstname);
						System.out.println("Employee Record .......");
						employeeDAO.update(emp1);
						System.out.println("Employee Updated Successfully ......."+"\n");
					}
					break;
				case 2:
					System.out.println("Enter the Employee Id : ");
					empid = sc.nextInt();
					Employee emp2 = employeeDAO.findByEmployeeId(empid);
					if (emp2 == null){
						System.out.println("Wrong ID .... ");
					}
					else{
						System.out.println("Current Last Name of Employee : "+emp2.getLastname());
						System.out.println("Enter New Last Name : ");
						lastname = sc.next();
						emp2.setLastname(lastname);
						System.out.println("Updating Record .......");
						employeeDAO.update(emp2);
						System.out.println("Employee Updated Successfully ......."+"\n");
					}
					break;
				case 3:
					System.out.println("Enter the Employee Id : ");
					empid = sc.nextInt();
					Employee emp3 = employeeDAO.findByEmployeeId(empid);
					if (emp3 == null){
						System.out.println("Wrong ID .... ");
					}
					else{
						System.out.println("Current Salary of Employee : "+emp3.getSalary());
						System.out.println("Enter New Salary : ");
						salary = sc.nextInt();
						emp3.setSalary(salary);
						System.out.println("Updating Record .......");
						employeeDAO.update(emp3);
						System.out.println("Employee Updated Successfully ......."+"\n");
						break;
					}
				case 4:
					System.out.println("Enter the Employee Id : ");
					empid = sc.nextInt();
					Employee emp4 = employeeDAO.findByEmployeeId(empid);
					if (emp4 == null){
						System.out.println("Wrong ID .... ");
					}
					else{
						System.out.println("Current EmailID of Employee : "+emp4.getEmailid());
						System.out.println("Enter New Salary : ");
						emailid = sc.next();
						emp4.setEmailid(emailid);
						System.out.println("Updating Record .......");
						employeeDAO.update(emp4);
						System.out.println("Employee Updated Successfully ......."+"\n");
					}
					break;
				case 5:
					System.out.println("Enter the Employee Id : ");
					empid = sc.nextInt();
					Employee emp5 = employeeDAO.findByEmployeeId(empid);
					if (emp5 == null){
						System.out.println("Wrong ID .... ");
					}
					else{
						System.out.println(" First Name : "+emp5.getFirstname()+" Last Name : "+emp5.getLastname()+" Salary : "+emp5.getSalary()+" EmailID : "+emp5.getEmailid());
						System.out.println("Enter the Details");
						firstname = sc.next();
						lastname = sc.next();
						salary = sc.nextInt();
						emailid = sc.next();
						emp5.setFirstname(firstname);
						emp5.setLastname(lastname);
						emp5.setSalary(salary);
						emp5.setEmailid(emailid);
						System.out.println("Updating Record .......");
						employeeDAO.update(emp5);
						System.out.println("Employee Updated Successfully ......."+"\n");
					}
			}
		}
		catch (Exception e){
			System.out.println("Entered employee id is not valid");
		}

	}

	private void seeAllRecords(EmployeeDAO employeeDAO) {

		try {
			List<Employee> theEmployees = employeeDAO.findAll();

			for (Employee emp : theEmployees) {
				System.out.println(emp+"\n");
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}

	private void readEmployee(EmployeeDAO employeeDAO) {

		try {
			System.out.println("1.Find Employee by ID.");
			System.out.println("2.Find Employee by First Name.");
			System.out.println("3.Find Employee by Last Name.");
			System.out.println("4.Find Employee by EmailID.");
			System.out.println("Enter your choice : ");
			int ch = sc.nextInt();
			switch (ch) {
				case 1:
					System.out.print("Enter Employee ID: ");
					empid = sc.nextInt();
					Employee foundEmployee1 = employeeDAO.findByEmployeeId(empid);
					System.out.println(foundEmployee1.toString());
					break;
				case 2:
					System.out.print("Enter First Name: ");
					firstname = sc.next();
					List<Employee> foundEmployee2 = employeeDAO.findByFirstName(firstname);

					for (Employee emp : foundEmployee2) {
						System.out.println(emp+"\n");
					}
					break;
				case 3:
					System.out.print("Enter Last Name: ");
					lastname = sc.next();
					List<Employee> foundEmployee3 = employeeDAO.findByLastname(lastname);

					for (Employee emp : foundEmployee3) {
						System.out.println(emp+"\n");
					}
					break;
				case 4:
					System.out.print("Enter EmailID: ");
					emailid = sc.next();
					List<Employee> foundEmployee4 = employeeDAO.findByEmail(emailid);

					for (Employee emp : foundEmployee4) {
						System.out.println(emp+"\n");
					}
					break;
			}
		}
		catch (Exception e) {
			System.out.println("Entered the Wrong ID .... ");
		}

	}

	private void createMultipleEmployee(EmployeeDAO employeeDAO) {

		try {
			int Emp = 0;
			System.out.println("Enter how many employees you want to create");
			Emp = sc.nextInt();

			String[] firstnames = new String[Emp];
			String[] lastnames = new String[Emp];
			int[] salary = new int[Emp];
			String[] emails = new String[Emp];

			for (int i = 0; i < Emp; i++) {
				System.out.println("Enter the employee details : ");
				firstnames[i] = sc.next();
				lastnames[i] = sc.next();
				salary[i] = sc.nextInt();
				emails[i] = sc.next();

				Employee employee = new Employee(firstnames[i], lastnames[i], salary[i], emails[i]);
				employeeDAO.save(employee);
				System.out.println("Employee Saved");
				System.out.println("Employee Generated Id : " + employee.getEmpid()+"\n");
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong! Try again");
		}
	}

	private void createEmployee(EmployeeDAO employeeDAO) {

		try {
			System.out.println("Enter the details : ");
			firstname = sc.next();
			lastname = sc.next();
			salary = sc.nextInt();
			emailid = sc.next();

			System.out.println("Creating a new employee object ...");
			Employee emp = new Employee(firstname, lastname, salary, emailid);

			System.out.println("Saving the employee ...");
			employeeDAO.save(emp);

			System.out.println("Saved employee. Generated id" + emp.getEmpid()+"\n");
		}
		catch (Exception e) {
			System.out.println("Something went wrong");
		}
	}
}
