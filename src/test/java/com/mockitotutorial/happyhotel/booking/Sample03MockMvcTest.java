package com.mockitotutorial.happyhotel.booking;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class Sample03MockMvcTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private BookingService bookingServiceMock;

	@Test
	public void shouldReturnDefaultMessage() throws Exception {
		// given
		given(bookingServiceMock.getAvailablePlaceCount())
			.willReturn(10);
		
		// when
		this.mockMvc.perform(get("/greeting"))
			.andDo(print())
		
		// then
			.andExpectAll(
					status().isOk(),
					content().string("Greetings from The Happy Hotel. We've got enough beds for 10 guests!"));
	}
}