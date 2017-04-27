package by.bogdanovich.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import by.bogdanovich.model.Department;
import by.bogdanovich.model.Employee;

/**
 * This is data access class to manage employees and departments stored in database.
 * @author Alexander Bogdanovich 
 * @version 1.0.0
 */
public class DepartmentAppDAO implements AppDAO {

	@Autowired
	@Qualifier(value = "dataSource")
	private DriverManagerDataSource datasource;
	private JdbcTemplate template;
	@Autowired
	private Logger logger;

	@PostConstruct
	public void init() {
		template = new JdbcTemplate(datasource);
		template.execute("CREATE TABLE IF NOT EXISTS DEPARTMENTS(DEPARTMENTID INT NOT NULL, DEPARTMENTNAME VARCHAR(255))");

	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#getAllEmployees()
	 **/
	public List<Employee> getAllEmployees() {
		logger.debug(this.getClass().getName()+" getAllEmployees method executed.");
		return template.query(
				"SELECT * FROM EMPLOYEES JOIN DEPARTMENTS ON EMPLOYEES.DEPARTMENTID = DEPARTMENTS.DEPARTMENTID",
				employeeMapper);
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#getAllEmployeesInDepartment(java.lang.Integer)
	 **/
	public List<Employee> getAllEmployeesInDepartment(Integer departmentID) {
		logger.debug(this.getClass().getName()+" getAllEmployeesInDepartment method executed with param 'departmentID' = " + departmentID);
		return template
				.query("SELECT * FROM EMPLOYEES  JOIN DEPARTMENTS ON EMPLOYEES.DEPARTMENTID = DEPARTMENTS.DEPARTMENTID  WHERE EMPLOYEES.DEPARTMENTID = "
						+ departmentID, employeeMapper);
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#getAllEmployeesByDate(java.sql.Date)
	 **/
	public List<Employee> getAllEmployeesByDate(Date date) {
		logger.debug(this.getClass().getName() + " getAllEmployeesByDate method executed with param 'date' = " + date.toString());
		return template
				.query("SELECT * FROM EMPLOYEES  JOIN DEPARTMENTS ON EMPLOYEES.DEPARTMENTID = DEPARTMENTS.DEPARTMENTID  WHERE EMPLOYEES.BIRTHDATE = '"
						+ date + "'", employeeMapper);
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#getAllEmployeesBetwenDates(java.sql.Date, java.sql.Date)
	 **/
	public List<Employee> getAllEmployeesBetwenDates(Date date1, Date date2) {
		logger.debug(this.getClass().getName() + " getAllEmployeesBetwenDates method executed with params 'date1' = "+date1.toString()+" 'date2' = "+date2.toString());
		return template
				.query("SELECT * FROM EMPLOYEES  JOIN DEPARTMENTS ON EMPLOYEES.DEPARTMENTID = DEPARTMENTS.DEPARTMENTID  WHERE EMPLOYEES.BIRTHDATE BETWEEN '"
						+ date1 + "' AND '" + date2 + "'", employeeMapper);
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#getAllDepartments()
	 **/
	public List<Department> getAllDepartments() {
		logger.debug(this.getClass().getName()+" getAllDepartments method executed.");
		return template.query("SELECT * FROM DEPARTMENTS", departmentMapper);
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#addEmployee(by.bogdanovich.model.Employee)
	 **/
	public void addEmployee(Employee employee) {
		logger.debug(this.getClass().getName()+" addEmployee method executed with param 'employee' = "+employee.toString());
		employee.setDepartment(findDepartmentById(employee.getDepartment().getDepartmentID()));
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO EMPLOYEES VALUES(");
		sb.append(employee.getId() + ", '");
		sb.append(employee.getFirstName() + "' ,'");
		sb.append(employee.getPatronymic() + "', '");
		sb.append(employee.getLastName() + "', ");
		sb.append(employee.getSalary() + ", '");
		sb.append(employee.getBirthDate() + "', ");
		sb.append(employee.getDepartment().getDepartmentID() + ")");
		

		template.execute(sb.toString());

	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#updateEmployee(by.bogdanovich.model.Employee)
	 **/
	public void updateEmployee(Employee employee) {
		employee.setDepartment(findDepartmentById(employee.getDepartment().getDepartmentID()));
		logger.debug(this.getClass().getName()+" updateEmployee method executed with param 'employee' = "+employee.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE EMPLOYEES SET DEPARTMENTID = ");
		sb.append(employee.getDepartment().getDepartmentID() + ", SALARY = ");
		sb.append(employee.getSalary() + ", FIRSTNAME = '");
		sb.append(employee.getFirstName() + "', PATRONYMIC = '");
		sb.append(employee.getPatronymic() + "', LASTNAME = '");
		sb.append(employee.getLastName() + "', BIRTHDATE = '");
		sb.append(employee.getBirthDate());
		sb.append("' WHERE ID = " + employee.getId());

		template.update(sb.toString());
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#deleteEmployee(java.lang.Integer)
	 **/
	public void deleteEmployee(Integer id) {
		logger.debug(this.getClass().getName()+" deleteEmployee method executed with param 'id' = "+id);
		template.execute("DELETE FROM EMPLOYEES WHERE ID = " + id);
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#addDepartment(by.bogdanovich.model.Department)
	 **/
	public void addDepartment(Department department) {
		logger.debug(this.getClass().getName() + " addDepartment method executed with param 'department' = "+department.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("INSERT INTO DEPARTMENTS VALUES(");
		sb.append(department.getDepartmentID() + ", '");
		sb.append(department.getDepartmentName() + "')");
		template.execute(sb.toString());
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#updateDepartment(by.bogdanovich.model.Department)
	 **/
	public void updateDepartment(Department department) {
		logger.debug(this.getClass().getName() + " updateDepartment method executed with param 'department' = "+department.toString());
		StringBuilder sb = new StringBuilder();
		sb.append("UPDATE DEPARTMENTS SET DEPARTMENTNAME = '");
		sb.append(department.getDepartmentName() + "' WHERE DEPARTMENTID = " + department.getDepartmentID());

		template.update(sb.toString());
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#deleteDepartment(by.bogdanovich.model.Department)
	 **/
	public void deleteDepartment(Department department) {
		logger.debug(this.getClass().getName() + " deleteDepartment method executed with param 'department' = "+department.toString());
		template.execute("DELETE FROM DEPARTMENTS WHERE DEPARTMENTID = " + department.getDepartmentID());
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#findEmployeeById(java.lang.Integer)
	 **/
	public Employee findEmployeeById(Integer id) {
		logger.debug(this.getClass().getName() + " findEmployeeById method executed with param 'id' = "+id);
		return template.query("SELECT * FROM EMPLOYEES WHERE ID = " + id, employeeMapper).get(0);
	}

	/**
	 * @see by.bogdanovich.dao.AppDAO#findDepartmentById(java.lang.Integer)
	 **/
	public Department findDepartmentById(Integer departmentId) {
		logger.debug(this.getClass().getName() + " findDepartmentById method executed with param 'departmentID' = "+departmentId);
		return template.query("SELECT * FROM DEPARTMENTS WHERE DEPARTMENTID = " + departmentId, departmentMapper)
				.get(0);
	}

	private RowMapper<Employee> employeeMapper = new RowMapper<Employee>() {

		public Employee mapRow(ResultSet rs, int numRow) throws SQLException {
			Employee employee = new Employee();
			employee.setId(rs.getInt("ID"));
			employee.setDepartment(
					new Department(rs.getInt("DEPARTMENTS.DEPARTMENTID"), rs.getString("DEPARTMENTS.DEPARTMENTNAME")));
			employee.setSalary(rs.getInt("SALARY"));
			employee.setFirstName(rs.getString("FIRSTNAME"));
			employee.setPatronymic(rs.getString("PATRONYMIC"));
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
