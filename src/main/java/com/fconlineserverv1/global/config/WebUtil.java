package com.fconlineserverv1.global.config;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Getter
@Slf4j
@Component
public class WebUtil {
    private final WebDriver driver;

    public WebUtil() throws IOException {
        ClassPathResource resource = new ClassPathResource("static/chromedriver-win64/chromedriver.exe");
        String chromedriverPath = resource.getFile().getAbsolutePath();
        System.setProperty("webdriver.chrome.driver", chromedriverPath);
        ChromeOptions options=new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-popup-blocking");//팝업 무시
        options.addArguments("headless");// 창이없이 프로세스 사용
        this.driver=new ChromeDriver(options);
    }
}
