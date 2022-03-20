package br.com.tdd;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import br.com.courses.tests.Calculator;

public class CalculatorTest {
	
	@Test
	public void sumIntegerTest() {
		Calculator calc = new Calculator();
		Assert.assertEquals(7, calc.sumInteger(2, 5));
		
	}
	
	@Test
	public void sumZeroTest() {
		Calculator calc = new Calculator();
		Assert.assertEquals(2, calc.sumInteger(2, 0));
		Assert.assertEquals(2, calc.sumInteger(0, 2));
		Assert.assertEquals(0, calc.sumInteger(0, 0));
	}
	
}
