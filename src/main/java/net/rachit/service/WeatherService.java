package net.rachit.service;

import lombok.extern.slf4j.Slf4j;
import net.rachit.Cache.AppCache;
import net.rachit.apiResponse.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
@Slf4j
@Component
public class WeatherService {
    @Value("${weather.api.key}")
     private String apiKey;
    private  final String API ="https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";
    @Autowired
    private  RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;
    public WeatherResponse getWeather(String city)
    {
     String finalAPI = appCache.app_Cache.get(AppCache.keys.weather_api.toString()).replace("CITY",city).replace("API_KEY",apiKey);
        ResponseEntity<WeatherResponse> response =  restTemplate.exchange(finalAPI, HttpMethod.GET,null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        log.info("API response"+body);
        return body;
    }
}
