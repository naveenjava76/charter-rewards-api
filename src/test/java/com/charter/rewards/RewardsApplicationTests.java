package com.charter.rewards;

import com.charter.rewards.beans.CustomerReward;
import com.charter.rewards.contollers.RewardsController;
import com.charter.rewards.utils.RewardsConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@WebMvcTest(RewardsController.class)
class RewardsApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	ResourceLoader resourceLoader;

	static ObjectMapper objectMapper;

	@BeforeAll
	static void initAll() {
		objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
	}

	@Test
	public void testGetRewardsForValidRequest() throws Exception {
		Resource responseResource = resourceLoader.getResource("classpath:responses/ValidResponse.json");
		List<CustomerReward> rewards = Arrays.asList(objectMapper.readValue(responseResource.getFile(), CustomerReward[].class));

		mockMvc.perform(get(RewardsConstants.REWARDS_RESOURCE))
				.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(rewards)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testGetRewardsByCustomerIdForValidRequest() throws Exception {
		Resource responseResource = resourceLoader.getResource("classpath:responses/ValidResponseForCustomerId.json");
		List<CustomerReward> rewards = Arrays.asList(objectMapper.readValue(responseResource.getFile(), CustomerReward[].class));

		mockMvc.perform(get(RewardsConstants.REWARDS_BY_CUSTOMER_ID_RESOURCE, "1234"))
				.andExpect(MockMvcResultMatchers.content().json(objectMapper.writeValueAsString(rewards)))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
