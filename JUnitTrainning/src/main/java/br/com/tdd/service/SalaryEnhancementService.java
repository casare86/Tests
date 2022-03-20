package br.com.tdd.service;

import java.math.BigDecimal;

import br.com.tdd.enums.Performance;
import br.com.tdd.enums.PerformanceStrategy;
import br.com.tdd.model.Employee;

public class SalaryEnhancementService {

	public void annualEnhancement(Employee employee, Performance performance) {
		BigDecimal raise;
		switch (performance) {
		case LOW:
			raise = new BigDecimal("1.03");
			break;
		case AVARAGE:
			raise = new BigDecimal("1.05");
			break;
		case GOOD:
			raise = new BigDecimal("1.15");
			break;
		case AWESOME:
			raise = new BigDecimal("1.20");
			break;
		default:
			raise = new BigDecimal("1");
			break;
		}
		employee.raiseSalary(raise);
	}
	
	public void annualEnhancementStrategy(Employee employee, PerformanceStrategy performance) {
		employee.raiseSalary(performance.performancePercent());
	}
}
