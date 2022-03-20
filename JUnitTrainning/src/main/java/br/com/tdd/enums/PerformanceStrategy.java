package br.com.tdd.enums;

import java.math.BigDecimal;

public enum PerformanceStrategy {
	LOW {
		@Override
		public BigDecimal performancePercent() {
			return new BigDecimal("1.03");
		}
	}, GOOD {
		@Override
		public BigDecimal performancePercent() {
			return new BigDecimal("1.15");
		}
	}, AWESOME {
		@Override
		public BigDecimal performancePercent() {
			return new BigDecimal("1.20");
		}
	};
	
	public abstract BigDecimal performancePercent();

}
