package com.microservice.currencyexchangeservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RefreshScope
public class CurrencyExchengeController {
    @Autowired
    private Environment environment;
    @Autowired
    private ExchangeValueRepo repo;
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retreiveExchangeValue(@PathVariable String from,@PathVariable String to){
        ExchangeValue exchangeValue=repo.findByFromAndTo(from,to);
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
