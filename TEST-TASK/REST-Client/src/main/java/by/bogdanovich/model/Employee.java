package by.bogdanovich.model;

import java.sql.Date;

public class Employee {
	private Integer id;
	private Department department;
	private Integer salary;
	private String firstName;
	private String patronymic;
	private String lastName;
	private Date birthDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
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

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
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
	 * @param department
	 * @param salary
	 * @param firstName
	 * @param patronymic
	 * @param lastName
	 * @param birthDate
	 */
	public Employee(Integer id, Department department, Integer salary, String firstName, String patronymic,
			String lastName, Date birthDate) {
		this.id = id;
		this.department = department;
		this.salary = salary;
		this.firstName = firstName;
		this.patronymic = patronymic;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

}
