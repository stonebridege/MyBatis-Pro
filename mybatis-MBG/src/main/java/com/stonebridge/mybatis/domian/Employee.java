package com.stonebridge.mybatis.domian;

public class Employee {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.emp_id
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    private Integer empId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.emp_name
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    private String empName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_emp.emp_salary
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    private Double empSalary;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.emp_id
     *
     * @return the value of t_emp.emp_id
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    public Integer getEmpId() {
        return empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.emp_id
     *
     * @param empId the value for t_emp.emp_id
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    public void setEmpId(Integer empId) {
        this.empId = empId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.emp_name
     *
     * @return the value of t_emp.emp_name
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    public String getEmpName() {
        return empName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.emp_name
     *
     * @param empName the value for t_emp.emp_name
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    public void setEmpName(String empName) {
        this.empName = empName == null ? null : empName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_emp.emp_salary
     *
     * @return the value of t_emp.emp_salary
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    public Double getEmpSalary() {
        return empSalary;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_emp.emp_salary
     *
     * @param empSalary the value for t_emp.emp_salary
     *
     * @mbggenerated Wed Nov 03 23:02:35 CST 2021
     */
    public void setEmpSalary(Double empSalary) {
        this.empSalary = empSalary;
    }
}