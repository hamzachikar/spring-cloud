package com.microservice.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExchangeValueRepo extends JpaRepository<ExchangeValue,Long> {
    public ExchangeValue findByFromAndTo(String from,String to);
}
