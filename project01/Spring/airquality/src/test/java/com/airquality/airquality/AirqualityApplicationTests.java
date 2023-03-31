package com.airquality.airquality;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc

class AirqualityApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}

	// Test for cache APICALLS counter in AirQualityRestController
	@Test
	public void apiCallsCounterisWorking() throws Exception {
		AirQualityRestController airQualityService = new AirQualityRestController();
		airQualityService.get_air("London");
		assertEquals(1, airQualityService.getAirQualityStats().get("Total API calls"));
	}

	// Test for Latitude and Longitude in AirQualityRestController
	@Test
	public void getLatLong() throws Exception {
		AirQualityRestController airQualityService = new AirQualityRestController();
		Object result = airQualityService.get_air2("London").get(0);
		Map<String, Object> resultMap = (Map<String, Object>) result;
		String lat = (String) resultMap.get("lat");
		String lon = (String) resultMap.get("lon");
		assertEquals("51.5073219", lat);
		assertEquals("-0.1276474", lon);
	}

	// Test for AirQualityStats status of connection in AirQualityRestController.
	// 200 = OK
	@Test
	public void testAirQualityStatsEndpoint() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/airquality/stats"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}


	// Test for the Cache empty when calling the api CLEAN method
	@Test
	public void testCleanCache() throws Exception {
		AirQualityRestController airQualityService = new AirQualityRestController();
		airQualityService.get_air("London");
		airQualityService.get_air("Aveiro");
		airQualityService.resetCacheAndStats();
		assertEquals(0, airQualityService.getAirQualityStats().get("Cache hits"));
		assertEquals(0, airQualityService.getAirQualityStats().get("Cache misses"));

		assertNotEquals(0, airQualityService.getAirQualityStats().get("Total API calls"));
	}


}
