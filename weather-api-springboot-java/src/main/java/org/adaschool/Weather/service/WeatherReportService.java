package org.adaschool.Weather.service;

import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherReportService {

    private static final String API_KEY = "2eb88c13fc0c345f998a4bcd9c316597";
    private static final String API_URL = "https://api.openweathermap.org/data/2.5/weather";

    private final RestTemplate restTemplate; 

    public WeatherReportService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherReport getWeatherReport(double latitude, double longitude) {
        String url = API_URL + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + API_KEY;
        WeatherApiResponse response = restTemplate.getForObject(url, WeatherApiResponse.class);
    
        WeatherReport report = new WeatherReport();
        if (response != null && response.getMain() != null) {
            WeatherApiResponse.Main main = response.getMain();
            report.setTemperature(main.getTemperature());
            report.setHumidity(main.getHumidity());
        } else {
            report.setTemperature(0.0); 
            report.setHumidity(0);
        }
    
        return report;
    }
}
