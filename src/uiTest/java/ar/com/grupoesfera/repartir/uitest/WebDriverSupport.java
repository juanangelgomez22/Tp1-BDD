package ar.com.grupoesfera.repartir.uitest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import javax.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("uiTest")
public class WebDriverSupport {

    @Value("${FIREFOX_OPTIONS:}")
    String[] environmentalOptions;

    @PostConstruct
    public void downloadWebDriver() {

        WebDriverManager.firefoxdriver()
                .clearDriverCache()
                .setup();
    }

    @Bean
    public WebDriver buildWebDriver() {

        var options = new FirefoxOptions();
        options.addArguments("--headless");
        options.addArguments(environmentalOptions);

        return new FirefoxDriver(options);
    }
}
