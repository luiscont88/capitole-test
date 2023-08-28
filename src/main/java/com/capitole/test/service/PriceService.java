package com.capitole.test.service;

import java.util.Comparator;
import java.util.List;

import com.capitole.test.controller.request.PriceRequest;
import com.capitole.test.controller.response.PriceResponse;
import com.capitole.test.entity.Price;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capitole.test.repository.PriceRepository;

@Service
public class PriceService {

    @Autowired
    private PriceRepository priceRepository;

    public PriceResponse getPrice(PriceRequest priceRequest) {
        List<Price> prices = priceRepository.findByBrandProductAndDate(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getAppDate());
        PriceResponse priceResponse = null;
        if (!prices.isEmpty()) {
            Price price = prices.stream().max(Comparator.comparing(Price::getPriority)).get();
            priceResponse = makePriceResponse(priceRequest, price);
        }
        return priceResponse;
    }

    private PriceResponse makePriceResponse(PriceRequest priceRequest, Price price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setAppDate(priceRequest.getAppDate());
        priceResponse.setBrandId(price.getBrand().getId());
        priceResponse.setFinalPrice(price.getPrice());
        priceResponse.setPrice(price.getPriceList());
        priceResponse.setProductId(price.getProductId());
        return priceResponse;
    }

}
