package com.imad.retailstore.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.imad.retailstore.dto.UserDTO;
import com.imad.retailstore.interfaces.IDiscountService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DiscountController.class)
public class DiscountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private IDiscountService discountService;
	private ObjectMapper mapper = new ObjectMapper();
	private ObjectWriter ow;

	@Before
	public void setVariable() {
		mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
		ow = mapper.writer().withDefaultPrettyPrinter();
	}

	@Test
	public void getDiscountTest() throws Exception {
		UserDTO userDTO = new UserDTO();
		String jsonRequest = ow.writeValueAsString(userDTO);
		when(discountService.getDiscount(any(UserDTO.class), anyDouble(), anyString())).thenReturn(45.0);
		mockMvc.perform(post("/api-gateway/user/discount?bill=990.0&item=books").contentType(MediaType.APPLICATION_JSON)
				.content(jsonRequest)).andExpect(status().isOk()).andReturn();
	}

}
