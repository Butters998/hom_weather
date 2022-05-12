package com.example.hom_weather.controller;

import com.example.hom_weather.model.City;
import com.example.hom_weather.model.Weather;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.client.RestTemplate;

@Controller
public class WeatherController {

    Weather weather = new Weather();
    City city = new City();



    public Weather getWeather() {
        RestTemplate restTemplate = new RestTemplate();

        Weather weather =
                restTemplate.getForObject("https://goweather.herokuapp.com/weather/" + city.getCity(),
                        Weather.class);
        weather.setCity(city.getCity());
        return weather;
    }


    @GetMapping("/weather")
    public String get(Model model) {
        model.addAttribute("weather", getWeather());
        model.addAttribute("city", new City());
        return "weatherView";
    }

    @GetMapping("/show-weather")
    public String showWeather(@ModelAttribute City city) {
        this.city = city;
        return "redirect:/weather";
    }
}
