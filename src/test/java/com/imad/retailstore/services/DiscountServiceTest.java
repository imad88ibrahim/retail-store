package com.imad.retailstore.services;

import com.imad.retailstore.constants.AppConstants;
import com.imad.retailstore.constants.UserType;
import com.imad.retailstore.dto.UserDTO;
import com.imad.retailstore.entity.UserEntity;
import com.imad.retailstore.error.RetailStoreException;
import com.imad.retailstore.interfaces.IDiscountService;
import com.imad.retailstore.repository.UserRepository;
import java.time.LocalDate;
import java.util.Optional;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DiscountServiceTest {

	@Autowired
	private IDiscountService discountService;
	@MockBean
	private UserRepository userRepository;

	@Test
	public void getDiscountForGroceries() throws RetailStoreException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1100l);
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(LocalDate.now().minusYears(3));
		userDTO.setUserType(UserType.EMPLOYEE);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
		assertEquals(Double.valueOf(0.0),
				discountService.getDiscount(userDTO, 90.00, AppConstants.ITEM_TYPE_GROCERIES));
	}
	
	@Test
	public void getDiscountForGroceriesAndForEvery100OnTheBill() throws RetailStoreException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1100l);
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(LocalDate.now().minusYears(3));
		userDTO.setUserType(UserType.EMPLOYEE);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
		assertEquals(Double.valueOf(45.0),
				discountService.getDiscount(userDTO, 990.00, AppConstants.ITEM_TYPE_GROCERIES));
	}

	@Test
	public void getDiscountForEmployee() throws RetailStoreException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1100l);
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(LocalDate.now().minusYears(1));
		userDTO.setUserType(UserType.EMPLOYEE);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
		assertEquals(Double.valueOf(27.0), discountService.getDiscount(userDTO, 90.00, "books"));
	}

	@Test
	public void getDiscountForAffiliate() throws RetailStoreException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1100l);
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(LocalDate.now().minusYears(1));
		userDTO.setUserType(UserType.AFFILIATE);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
		assertEquals(Double.valueOf(9.0), discountService.getDiscount(userDTO, 90.00, "books"));
	}

	@Test
	public void getDiscountForCustomer() throws RetailStoreException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1100l);
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(LocalDate.now().minusYears(3));
		userDTO.setUserType(UserType.CUSTOMER);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
		assertEquals(Double.valueOf(4.5), discountService.getDiscount(userDTO, 90.00, "books"));
	}

	@Test
	public void getDiscountForEvery100OnTheBill() throws RetailStoreException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1100l);
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(LocalDate.now().minusYears(1));
		userDTO.setUserType(UserType.CUSTOMER);
		when(userRepository.findById(anyLong())).thenReturn(Optional.of(userEntity));
		assertEquals(Double.valueOf(45.0), discountService.getDiscount(userDTO, 990.00, "books"));
	}

	@Test(expected = RetailStoreException.class)
	public void getDiscountExceptionTest() throws RetailStoreException {
		UserDTO userDTO = new UserDTO();
		userDTO.setUserId(1100l);
		UserEntity userEntity = new UserEntity();
		userEntity.setCreatedDate(LocalDate.now().minusYears(1));
		userDTO.setUserType(UserType.CUSTOMER);
		when(userRepository.findById(anyLong())).thenThrow(RuntimeException.class);
		assertEquals(Double.valueOf(45.0), discountService.getDiscount(userDTO, 990.00, "books"));
	}

}
