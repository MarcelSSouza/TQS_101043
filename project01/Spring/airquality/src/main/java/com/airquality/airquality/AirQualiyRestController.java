package com.airquality.airquality;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

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
        public List<Object> get_air2(@PathVariable(name = "city") String city) throws Exception {
                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create("http://api.openweathermap.org/geo/1.0/direct?q=" + city
                                                + "&limit=1&appid=3e56ac9f515ce67c8d613f033e5655b5"))
                                .header("Content-type", "application/json")
                                .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
                String responseBody = response.body();
                ObjectMapper mapper = new ObjectMapper();
                String lat = mapper.readTree(responseBody).get(0).get("lat").asText();
                String lon = mapper.readTree(responseBody).get(0).get("lon").asText();

                String url = "http://api.openweathermap.org/data/2.5/air_pollution/forecast?lat=" + lat + "&lon=" + lon
                                + "&appid=3e56ac9f515ce67c8d613f033e5655b5";

                HttpRequest request2 = HttpRequest.newBuilder()
                                .uri(URI.create(url))
                                .header("Content-type", "application/json")
                                .build();
                HttpResponse<String> response2 = client.send(request2, HttpResponse.BodyHandlers.ofString());
                String responseBody2 = response2.body();

                JsonNode root = mapper.readTree(responseBody2);
                List<Object> results = new ArrayList<>();
                for (JsonNode node : root.get("list")) {
                        long dt = node.get("dt").asLong();
                        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC);
                        LocalDateTime twoDaysLater = LocalDateTime.now().plusDays(1);

                        if (dateTime.toLocalDate().isEqual(twoDaysLater.toLocalDate())) {
                                results.add(node);
                                if (results.size() == 1) {
                                        break;
                                }

                        }
                }

                for (JsonNode node : root.get("list")) {
                        long dt = node.get("dt").asLong();
                        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC);
                        LocalDateTime threeDaysLater = LocalDateTime.now().plusDays(2);
                        if (dateTime.toLocalDate().isEqual(threeDaysLater.toLocalDate())) {
                                results.add(node);
                                if (results.size() == 2) {
                                        break;
                                }

                        }
                }

                for (JsonNode node : root.get("list")) {
                        long dt = node.get("dt").asLong();
                        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC);
                        LocalDateTime fourDaysLater = LocalDateTime.now().plusDays(3);
                        if (dateTime.toLocalDate().isEqual(fourDaysLater.toLocalDate())) {
                                results.add(node);
                                if (results.size() == 3) {
                                        break;
                                }
                        }
                }

                for (JsonNode node : root.get("list")) {
                        long dt = node.get("dt").asLong();
                        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC);
                        LocalDateTime fiveDaysLater = LocalDateTime.now().plusDays(4);

                        if (dateTime.toLocalDate().isEqual(fiveDaysLater.toLocalDate())) {
                                results.add(node);
                                if (results.size() == 4) {
                                        break;
                                }
                        }
                }

                for (JsonNode node : root.get("list")) {
                        long dt = node.get("dt").asLong();
                        LocalDateTime dateTime = LocalDateTime.ofEpochSecond(dt, 0, ZoneOffset.UTC);
                        LocalDateTime sixDaysLater = LocalDateTime.now().plusDays(5);
                        if (dateTime.toLocalDate().isEqual(sixDaysLater.toLocalDate())) {
                                results.add(node);
                                if (results.size() == 5) {
                                        break;
                                }
                        }
                }
                String jsonResults = mapper.writeValueAsString(results);
                List<Object> jsonObjList = mapper.readValue(jsonResults, List.class).subList(0, 5);
                return jsonObjList;

        }

}
