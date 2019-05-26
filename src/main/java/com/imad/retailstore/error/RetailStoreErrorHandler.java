package com.imad.retailstore.error;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RetailStoreErrorHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(RetailStoreErrorHandler.class);

	@ExceptionHandler(RetailStoreException.class)
	public void handleRetailStoreException(RetailStoreException retailStoreException) {
		LOGGER.error("handle errro here {}", retailStoreException);
	}

}
