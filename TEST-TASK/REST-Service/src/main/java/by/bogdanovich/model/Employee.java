package by.bogdanovich.model;

import java.sql.Date;

public class Employee {
	private Department department;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDate;

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	/**
	 * @param department
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param birthDate
	 */
	public Employee(Department department, String firstName, String middleName, String lastName, Date birthDate) {
		this.department = department;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

	/**
	 * 
	 */
	public Employee() {

	}

}
