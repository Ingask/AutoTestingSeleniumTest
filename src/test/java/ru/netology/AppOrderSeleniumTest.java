package ru.netology;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppOrderSeleniumTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
// убедитесь, что файл chromedriver.exe расположен именно в каталоге C:\tmp
        System.setProperty("webdriver.chrome.driver", "driver/win/chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldSubmitRequest() {
        driver.get("http://localhost:9999");
        List<WebElement> elements = driver.findElements(By.className("input__control"));
        elements.get(0).sendKeys("Иванов Иван");
        elements.get(1).sendKeys("+11111111111");
        driver.findElement(By.className("checkbox__box")).click();
        driver.findElement(By.cssSelector("[type='button']")).click();
        String text = driver.findElement(By.cssSelector("[data-test-id='order-success']")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }
}
