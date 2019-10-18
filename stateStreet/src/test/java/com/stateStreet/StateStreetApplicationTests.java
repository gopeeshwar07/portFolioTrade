package com.stateStreet;

import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.stateStreet.config.PortofolioIntegrationPropertiesConfig;
import com.stateStreet.service.EmailService;
import com.stateStreet.service.PortfolioService;
import com.stateStreet.service.TradeDTO;

@SpringBootTest
class StateStreetApplicationTests {

	@Autowired
	PortofolioIntegrationPropertiesConfig payrollIntegrationPropertiesConfig;

	@Autowired
	PortfolioService portfolioService;

	Set<TradeDTO> trades = new HashSet<>();

	@Before
	public void setup() {
		TradeDTO trade1 = new TradeDTO("Td1", "Buy", 100, 1.3);
		TradeDTO trade2 = new TradeDTO("Td1", "Buy", 1100, 5.9);
		TradeDTO trade3 = new TradeDTO("Td1", "Buy", 200, 10.6);
		trades.add(trade1);
	//	trades.add(trade2);
		trades.add(trade3);
	}

	@Mock
	private EmailService emailService;
	@Test
	public void testEmailNotification() {
		Mockito.doNothing().when(emailService).sendNotificationEmail();
		//portfolioService.acceptTrades(trades);
		// Assertions.assertThatThrownBy(()->payrollSFTPFileTransferResource.generateFileFromTransformedData(response)).isInstanceOf(Exception.class);
	}

	@Test
	public void calculateNetPosition() {
		TradeDTO trade1 = new TradeDTO("Td1", "Buy", 100, 1.3);
		TradeDTO trade2 = new TradeDTO("Td1", "Sale", 50, 1.5);
		TradeDTO trade3 = new TradeDTO("Td1", "Buy", 200, 10.6);
		trades.add(trade1);
		trades.add(trade2);
		trades.add(trade3);
		Assertions.assertThat(portfolioService.calculateNetPosition(trades)).isEqualTo(250);

	}
}
