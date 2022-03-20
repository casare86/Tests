package br.com.tdd.service;

import static org.junit.Assert.assertThrows;
//static import to don´t need to call the class every time
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.tdd.model.Employee;

class BonusServiceTest {
	
	
	@Test
	@DisplayName("Assert throw exception due to high salary")
	void testCalculateBonusMustBeZero() {
		BonusService service = new BonusService();
		assertThrows(IllegalArgumentException.class, 
				()-> service.calculateBonus(new Employee("Test", LocalDate.now(), new BigDecimal("10001"))));
	}	
	
	@Test
	@DisplayName("Check exception message")
	void testCalculateBonusMustBeZeroMsgException() {
		BonusService service = new BonusService();
		try {
			service.calculateBonus(new Employee("Test", LocalDate.now(), new BigDecimal("10001")));
			fail("Didn´t lauch an exception when should have.");
		}catch (Exception e) {
			assertEquals("Employee´s salary too high. Bonus policy does not apply to it.", e.getMessage());
		}
	}	
	
	@Test
	void testCalculateBonusMustBePositive() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calculateBonus(new Employee("Test", LocalDate.now(), new BigDecimal("6000")));
		assertEquals(new BigDecimal("600.00"), bonus);
	}	
	
	@Test
	void testCalculateBonusMax() {
		BonusService service = new BonusService();
		BigDecimal bonus = service.calculateBonus(new Employee("Test", LocalDate.now(), new BigDecimal("10000")));
		assertEquals(new BigDecimal("1000.00"), bonus);
	}

}
