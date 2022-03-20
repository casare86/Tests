package br.com.tdd.service;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.tdd.enums.Performance;
import br.com.tdd.enums.PerformanceStrategy;
import br.com.tdd.model.Employee;

public class SalaryEnhancementServiceTest {
	
	private SalaryEnhancementService service;
	private Employee employee;
	
	@Before
	public void before() {
		System.out.println("Before");
	}
	
	@After
	public void after() {
		System.out.println("After");
	}
	
	@BeforeEach
	public void setup() {
		System.out.println("Before each - called before for every test");
		service = new SalaryEnhancementService();
		employee = new Employee("Test", LocalDate.now(), new BigDecimal("1000"));
	}
	
	@AfterEach
	public void afterEach() {
		System.out.println("After each - called after for every test.");
	}
	
	@BeforeAll
	public static void beforeAll() {
		System.out.println("Before All - called once at beggining of all tests.");
	}
	
	@AfterAll
	public static void afterAll() {
		System.out.println("After All - called once finished all tests.");
	}

	@Test
	public void enhancementForLowPerformance() {
		service.annualEnhancement(employee, Performance.LOW);
		assertEquals(new BigDecimal("1030.00"), employee.getSalary());
	}
	
	@Test
	public void enhancementForAvaragePerformance() {
		service.annualEnhancement(employee, Performance.AVARAGE);
		assertEquals(new BigDecimal("1050.00"), employee.getSalary());
	}
	
	@Test
	public void enhancementForGoodPerformance() {
		service.annualEnhancementStrategy(employee, PerformanceStrategy.GOOD);
		assertEquals(new BigDecimal("1150.00"), employee.getSalary());
	}
	
	@Test
	public void enhancementForAwesomePerformance() {
		service.annualEnhancementStrategy(employee, PerformanceStrategy.AWESOME);
		assertEquals(new BigDecimal("1200.00"), employee.getSalary());
	}
}
