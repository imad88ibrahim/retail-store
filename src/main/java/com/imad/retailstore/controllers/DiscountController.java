package com.imad.retailstore.controllers;

import com.imad.retailstore.dto.UserDTO;
import com.imad.retailstore.error.RetailStoreException;
import com.imad.retailstore.interfaces.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("discountController")
@RequestMapping("/api-gateway")
public class DiscountController {

	@Autowired
	private IDiscountService discountService;

	@PostMapping("/user/discount")
	public ResponseEntity<Double> getDiscount(@RequestBody UserDTO userDTO, @RequestParam("bill") Double bill,
			@RequestParam("item") String item) throws RetailStoreException {
		Double discount = discountService.getDiscount(userDTO, bill, item);
		return new ResponseEntity<>(discount, HttpStatus.OK);
	}

}
