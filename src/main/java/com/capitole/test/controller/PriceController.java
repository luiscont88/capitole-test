package com.capitole.test.controller;

import com.capitole.test.controller.request.PriceRequest;
import com.capitole.test.controller.response.PriceResponse;
import com.capitole.test.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PriceController {

    @Autowired
    private PriceService priceService;

    @RequestMapping(value = "/price", method = RequestMethod.POST)
    public ResponseEntity<PriceResponse> getPrice(@RequestBody PriceRequest priceRequest) {
        PriceResponse priceResponse = priceService.getPrice(priceRequest);
        if (priceResponse != null) {
            return ResponseEntity.ok(priceResponse);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}