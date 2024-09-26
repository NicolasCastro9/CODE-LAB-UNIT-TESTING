package org.adaschool.Weather;

import org.adaschool.Weather.controller.WeatherReportController;
import org.adaschool.Weather.data.WeatherReport;
import org.adaschool.Weather.service.WeatherReportService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = WeatherApplication.class)
public class WeatherApplicationTestsController {

    @Mock
    private WeatherReportService weatherReportService; 

    @InjectMocks
    private WeatherReportController weatherReportController;

    @Test
    public void testGetWeatherReportController() {
        WeatherReport weatherReport = new WeatherReport();
        weatherReport.setTemperature(25.0);
        weatherReport.setHumidity(65.0);
        when(weatherReportService.getWeatherReport(37.8267, -122.4233)).thenReturn(weatherReport);
        WeatherReport result = weatherReportController.getWeatherReport(37.8267, -122.4233);
        assertEquals(25.0, result.getTemperature());
        assertEquals(65.0, result.getHumidity());
        Mockito.verify(weatherReportService).getWeatherReport(37.8267, -122.4233);
    }

    

}

