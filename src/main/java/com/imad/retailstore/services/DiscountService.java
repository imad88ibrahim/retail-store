package com.imad.retailstore.services;

import com.imad.retailstore.constants.AppConstants;
import com.imad.retailstore.constants.UserType;
import com.imad.retailstore.dto.UserDTO;
import com.imad.retailstore.entity.UserEntity;
import com.imad.retailstore.error.RetailStoreException;
import com.imad.retailstore.interfaces.IDiscountService;
import com.imad.retailstore.repository.UserRepository;
import java.time.LocalDate;
import java.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("discountService")
public class DiscountService implements IDiscountService {

	@Autowired
	private UserRepository userRepository;
	private static final Logger LOGGER = LoggerFactory.getLogger(DiscountService.class);

	/**
	 * @author IMAD
	 * @param userDTO UserDTO user information transfer object
	 * @param bill    Double bill amount
	 * @param item    String bill item
	 * @return discount Double calculated discount
	 * @exception RetailStoreException in case of no user found
	 */
	@Override
	public Double getDiscount(UserDTO userDTO, Double bill, String item) throws RetailStoreException {
		try {
			Double discount = 0.0;
			// retrieve user info from DB or else throw exception
			UserEntity userEntity = userRepository.findById(userDTO.getUserId())
					.orElseThrow(() -> new RetailStoreException("User Not Found"));
			// period from been a customer till now
			Period period = Period.between(userEntity.getCreatedDate(), LocalDate.now());
			// check if the item is groceries
			if (AppConstants.ITEM_TYPE_GROCERIES.equalsIgnoreCase(item)) {
				// The percentage based discounts do not apply
			} else {
				// check if the user is an employee of the store
				if (UserType.EMPLOYEE.equals(userDTO.getUserType())) {
					discount = (bill * 30) / 100;
				} else
				// check if the user is an affiliate of the store
				if (UserType.AFFILIATE.equals(userDTO.getUserType())) {
					discount = (bill * 10) / 100;
				} else
				// check if the user has been a customer for over 2 years
				if (period.getYears() >= 2) {
					discount = (bill * 5) / 100;
				}
			}
			// for every $100 on the bill, there would be a $ 5 discount
			discount += (int) (bill / 100) * 5;
			return discount;
		} catch (Exception e) {
			LOGGER.error("couldn't find a discount for this user {}", userDTO);
			throw new RetailStoreException(e);
		}
	}

}
