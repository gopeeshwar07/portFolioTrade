package com.stateStreet.service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stateStreet.config.PortofolioIntegrationPropertiesConfig;


@Service
public class PortfolioService {

	@Autowired
	PortofolioIntegrationPropertiesConfig payrollIntegrationPropertiesConfig;

	@Autowired
	private EmailService emailService;

	private String ID;
	private String name;
	private String creationTime;
	private Set<TradeDTO> trades = new HashSet<>();

	public static final int minAmount = 1;
	public static final int maxAmount = 1000;

	int portfolioPosition = 0;

	/**
	 * Accept trades whether buy or sale
	 * 
	 * @param trades
	 * @return profit/loss status
	 */
	public void acceptTrades(final Set<TradeDTO> trades) {
		trades.forEach(trade -> {
			calculateNetPosition(trades);
			if (portfolioPosition > payrollIntegrationPropertiesConfig.getMaxLimit()) {
				emailService.sendNotificationEmail();
			}
		});

	}

	public int calculateNetPosition(Set<TradeDTO> tradeSet) {
		tradeSet.stream().map(trade->{
			if(trade.getDirection().equalsIgnoreCase("Buy"))
				portfolioPosition=portfolioPosition+trade.getAmount();
			else if (trade.getDirection().equalsIgnoreCase("Sale"))
					portfolioPosition = portfolioPosition - trade.getAmount();	
		return trade;
		}).collect(Collectors.toSet());
		if (portfolioPosition > payrollIntegrationPropertiesConfig.getMaxLimit()) {
			emailService.sendNotificationEmail();
		}
		return portfolioPosition;
	}

}
