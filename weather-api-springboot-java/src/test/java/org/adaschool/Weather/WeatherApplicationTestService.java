package org.adaschool.Weather;
import org.adaschool.Weather.data.WeatherApiResponse;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class WeatherApplicationTestService {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherReportService weatherReportService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherReportService() {

        WeatherApiResponse response = new WeatherApiResponse();
        WeatherApiResponse.Main main = new WeatherApiResponse.Main();
        main.setTemperature(25.0);
        main.setHumidity(65.0);
        response.setMain(main);


        String url = "https://api.openweathermap.org/data/2.5/weather?lat=37.8267&lon=-122.4233&appid=2eb88c13fc0c345f998a4bcd9c316597";
        when(restTemplate.getForObject(url, WeatherApiResponse.class)).thenReturn(response);
        WeatherReport result = weatherReportService.getWeatherReport(37.8267, -122.4233);
        assertEquals(25.0, result.getTemperature());
        assertEquals(65.0, result.getHumidity());
        Mockito.verify(restTemplate).getForObject(url, WeatherApiResponse.class);
    }
}
