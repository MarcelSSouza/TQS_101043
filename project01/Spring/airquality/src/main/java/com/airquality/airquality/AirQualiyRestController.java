package com.airquality.airquality;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@RestController
@RequestMapping("/")
public class AirQualiyRestController {
	

	
	@GetMapping("/airquality/{city}")
	public Object get_air(@PathVariable(name = "city") String city) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.ambeedata.com/latest/by-city?city=" + city))
                .header("x-api-key", "2513ec5fd9098f394d9304ab71f19faefc4a6306f45784003ed8f20eeae8ea70")
                .header("Content-type", "application/json")
                .build();
        
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                Object json = mapper.readValue(responseBody, Object.class);
                return json;
	}
        @GetMapping("/airquality/predictions/{city}")
	public void get_air2(@PathVariable(name = "city") String city) throws Exception {
        
	}

}

