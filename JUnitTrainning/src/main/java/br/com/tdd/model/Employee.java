package br.com.tdd.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class Employee {
	
	private String name;
	private LocalDate addmissionDate;
	private BigDecimal salary;
	
	public Employee(String name, LocalDate addmissionDate, BigDecimal salary) {
		this.name = name;
		this.addmissionDate = addmissionDate;
		this.salary = salary;
	}
	
	public void raiseSalary(BigDecimal raise) {
		this.salary = this.salary.multiply(raise);
		roundSalary();
	}

	private void roundSalary() {
		this.salary = this.salary.setScale(2, RoundingMode.HALF_UP);
	}

	public String getName() {
		return name;
	}

	public LocalDate getAddmissionDate() {
		return addmissionDate;
	}

	public BigDecimal getSalary() {
		return salary;
	}

}