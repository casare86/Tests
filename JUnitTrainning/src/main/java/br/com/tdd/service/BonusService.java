package br.com.tdd.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import br.com.tdd.model.Employee;

public class BonusService {
	
	public BigDecimal calculateBonus(Employee employee) {
		 BigDecimal value = employee.getSalary().multiply(new BigDecimal("0.1"));
		 if(value.compareTo(new BigDecimal("1000")) > 0) {
			 //value = BigDecimal.ZERO;
			 throw new IllegalArgumentException("Employee´s salary too high. Bonus policy does not apply to it.");
		 }
		 //2 decimal cases using scientific rounding (up if 5 or more)
		 return value.setScale(2, RoundingMode.HALF_UP);
	}
}
