package com.airquality.airquality;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import java.util.Map;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class AirqualityApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void contextLoads() {
	}
	@Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	AirQualityRestController airQualityRestControllerMock = Mockito.mock(AirQualityRestController.class);

    }
	

	// Test for cache APICALLS counter in AirQualityRestController
	@Test
	public void apiCallsCounterisWorking() throws Exception {
		AirQualityRestController airQualityService = new AirQualityRestController();
		airQualityService.get_air("London");
		airQualityService.get_air("London");
		airQualityService.get_air("London");
		assertEquals(3, airQualityService.getAirQualityStats().get("Total API calls"));
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

	@Test
	public void cacheCalled() throws Exception {
		AirQualityRestController airQualityService = new AirQualityRestController();
		airQualityService.get_air("London");
		airQualityService.get_air("London");
		airQualityService.get_air("London");
		assertEquals(2, airQualityService.getAirQualityStats().get("Cache hits"));
		assertEquals(1, airQualityService.getAirQualityStats().get("Cache misses"));
		assertEquals(3, airQualityService.getAirQualityStats().get("Total API calls"));
	}

/* 	@Test
	public void cacheClearedAfter15Minutes() throws Exception {
		AirQualityRestController airQualityService = new AirQualityRestController();
		airQualityService.get_air("London");
		assertEquals(0, airQualityService.getAirQualityStats().get("Cache hits"));
		assertEquals(1, airQualityService.getAirQualityStats().get("Cache misses"));
		assertEquals(1, airQualityService.getAirQualityStats().get("Total API calls"));
		airQualityService.get_air("London");
		assertEquals(1, airQualityService.getAirQualityStats().get("Cache hits"));
		assertEquals(1, airQualityService.getAirQualityStats().get("Cache misses"));
		assertEquals(2, airQualityService.getAirQualityStats().get("Total API calls"));
		// Wait for 15 minutes
		Thread.sleep(15 * 60 * 1000);
		// Verify that the cache has been cleared
		airQualityService.get_air("London");
		assertEquals(0, airQualityService.getAirQualityStats().get("Cache hits"));
		assertEquals(1, airQualityService.getAirQualityStats().get("Cache misses"));
		assertEquals(3, airQualityService.getAirQualityStats().get("Total API calls"));
	}

 */












 //INTEGRATION TESTS
	// Test if the API is working for the endpoint /airquality/stats and if the response is a number for each of the 3 keys
    @Test
    public void testGetAirQualityStats() throws Exception {
        mockMvc.perform(get("/airquality/stats"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.['Cache hits']").isNumber())
		.andExpect(jsonPath("$.['Cache misses']").isNumber())
		.andExpect(jsonPath("$.['Total API calls']").isNumber());
    }


	@Test
public void testGetInvalidCityAirQuality() throws Exception {
    mockMvc.perform(get("/airquality/city?city=38dnaasdasdasdasdasdasdsndasd"))
            .andExpect(status().isNotFound());
}


@Test
public void testCleanPath() throws Exception {
	mockMvc.perform(get("/airquality/London"));
	mockMvc.perform(get("/airquality/Aveiro"));

	mockMvc.perform(get("/airquality/reset"));

	mockMvc.perform(get("/airquality/stats"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.['Cache hits']").value(0))
			.andExpect(jsonPath("$.['Cache misses']").value(0)) 
			.andExpect(jsonPath("$.['Total API calls']").value(2));
}



@Test
public void testInvalidHttpMethod() throws Exception {
    mockMvc.perform(post("/airquality/city?city=London"))
            .andExpect(status().isMethodNotAllowed());
}







 


}
