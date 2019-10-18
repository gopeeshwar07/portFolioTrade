package com.stateStreet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PortofolioIntegrationPropertiesConfig {

	@Value("${portfoli.maxLimit}")
	private Integer maxLimit;

	/**
	 * @return the maxLimit
	 */
	public Integer getMaxLimit() {
		return maxLimit;
	}

	/**
	 * @param maxLimit the maxLimit to set
	 */
	public void setMaxLimit(Integer maxLimit) {
		this.maxLimit = maxLimit;
	}

}
