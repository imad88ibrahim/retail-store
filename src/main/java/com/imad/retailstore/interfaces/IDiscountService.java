package com.imad.retailstore.interfaces;

import com.imad.retailstore.dto.UserDTO;
import com.imad.retailstore.error.RetailStoreException;

public interface IDiscountService {

	Double getDiscount(UserDTO userDTO, Double bill, String item) throws RetailStoreException;

}
