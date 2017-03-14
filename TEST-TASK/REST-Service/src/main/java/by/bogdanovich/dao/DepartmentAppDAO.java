package by.bogdanovich.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

public class DepartmentAppDAO implements AppDAO {

	@Autowired
	@Qualifier(value = "dataSource")
	private DriverManagerDataSource datasource;
	private JdbcTemplate template = new JdbcTemplate(datasource);

	@PostConstruct
	public void init() {

	}

	public List<Employee> getAllEmployees() {
		return template.query("SELECT * FROM EMPLOYEES", employeeMapper);
	}

	public List<Employee> getAllEmployeesInDepartment(Integer departmentID) {
		return template.query("SELECT * FROM EMPLOYEES WHERE DEPARTMENTID = " + departmentID, employeeMapper);
	}

	public List<Employee> getAllEmployeesByDate(Date date) {
		return template.query("SELECT * FROM EMPLOYEES WHERE DATE = " + date, employeeMapper);
	}

	public List<Employee> getAllEmployeesBetwenDates(Date date1, Date date2) {
		return template.query("SELECT * FROM EMPLOYEES WHERE BIRTHDATE BETWEEN " + date1 + " AND " + date2,
				employeeMapper);
	}

	public List<Department> getAllDepartments() {
		return template.query("SELECT * FROM DEPARTMENTS", departmentMapper);
	}

	public void addEmployee(Employee employee) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO EMPLOYEES VALUES(");
		sb.append(employee.getId() + ", ");
		sb.append(employee.getDepartmentID() + ", ");
		sb.append(employee.getSalary() + ", '");
		sb.append(employee.getFirstName() + "', '");
		sb.append(employee.getMiddleName() + "', '");
		sb.append(employee.getLastName() + "', ");
		sb.append(employee.getBirthDate());

		template.execute(sb.toString());

	}

	public void updateEmployee(Employee employee) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE EMPLOYEES SET DEPARTMENTID = ");
		sb.append(employee.getDepartmentID() + ", SALARY = ");
		sb.append(employee.getSalary() + ", FIRSTMAME = '");
		sb.append(employee.getFirstName() + "', MIDDLENAME = '");
		sb.append(employee.getMiddleName() + "', LASTNAME = '");
		sb.append(employee.getLastName() + "', BIRTHDATE = ");
		sb.append(employee.getBirthDate());
		sb.append("WHERE ID = " + employee.getId());

		template.update(sb.toString());
	}

	public void deleteEmployee(Employee employee) {
		template.execute("DELETE FROM EMPLOYEES WHERE ID = " + employee.getId());
	}

	public void addDepartment(Department department) {
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO DEPARTMENTS VALUES(");
		sb.append(department.getDepartmentID() + ", '");
		sb.append(department.getDepartmentName() + "')");
		template.execute(sb.toString());
	}

	public void updateDepartment(Department department) {
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE DEPARTMENTS SET DEPARTMENTNAME = '");
		sb.append(department.getDepartmentName() + "' WHERE DEPARTMENTID = " + department.getDepartmentID());

		template.update(sb.toString());
	}

	public void deleteDepartment(Department department) {
		template.execute("DELETE FROM DEPARTMENTS WHERE DEPARTMENTID = " + department.getDepartmentID());
	}

	private RowMapper<Employee> employeeMapper = new RowMapper<Employee>() {

		public Employee mapRow(ResultSet rs, int numRow) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.findColumn("ID"));
			employee.setDepartmentID(rs.getInt("DEPARTMENTID"));
			employee.setSalary(rs.getInt("SALARY"));
			employee.setFirstName(rs.getString("FIRSTNAME"));
			employee.setMiddleName(rs.getString("MIDDLENAME"));
			employee.setLastName(rs.getString("LASTNAME"));
			employee.setBirthDate(rs.getDate("BIRTHDATE"));

			return employee;
		}
	};

	private RowMapper<Department> departmentMapper = new RowMapper<Department>() {

		public Department mapRow(ResultSet rs, int numRow) throws SQLException {
			Department department = new Department();
			department.setDepartmentID(rs.getInt("DEPARTMENTID"));
			department.setDepartmentName(rs.getString("DEPARTMENTNAME"));
			return department;
		}
	};

}
