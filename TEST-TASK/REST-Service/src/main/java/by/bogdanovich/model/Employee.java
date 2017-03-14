package by.bogdanovich.model;

import java.sql.Date;

public class Employee {
	private Integer id;
	private Integer departmentID;
	private Integer salary;
	private String firstName;
	private String middleName;
	private String lastName;
	private Date birthDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
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

	public Employee() {

	}

	/**
	 * @param id
	 * @param departmentID
	 * @param salary
	 * @param firstName
	 * @param middleName
	 * @param lastName
	 * @param birthDate
	 */
	public Employee(Integer id, Integer departmentID, Integer salary, String firstName, String middleName,
			String lastName, Date birthDate) {
		this.id = id;
		this.departmentID = departmentID;
		this.salary = salary;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

}
