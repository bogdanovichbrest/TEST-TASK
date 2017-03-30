package by.bogdanovich.model;

public class Department {
	private Integer departmentID;
	private String departmentName;

	public Integer getDepartmentID() {
		return departmentID;
	}

	public void setDepartmentID(Integer departmentID) {
		this.departmentID = departmentID;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Department() {
	}

	public Department(Integer departmentID, String departmentName) {
		this.departmentID = departmentID;
		this.departmentName = departmentName;
	}
	
	@Override
	public String toString()
	{
		return "departmentID = "+departmentID+", departmentName = "+departmentName;
	}

}
