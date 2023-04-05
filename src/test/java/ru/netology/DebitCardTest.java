package ru.netology ;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DebitCardTest {

    private ChromeDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setupEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--sandbox");
        options.addArguments("--headless");
        options.addArguments("--remote-allow-origin=*");
        driver = new ChromeDriver(options);
        driver.get("http://0.0.0.0:999");


    }
    @AfterEach
    public  void afterEach() {
        driver.quit();
        driver = null;

    }

    @Test
    public void shouldBeSucsessfulFofm() {
        driver.findElement(By.cssSelector("[data-test-id=name input")).sendKeys("Петров Павел");
        driver.findElement(By.cssSelector("[data-test-id-phone] input")).sendKeys("+79505005050");
        driver.findElement(By.cssSelector("[data-test-id-agreement]")).click();
        driver.findElement(By.cssSelector("button.button")).click();
        var actalText = driver.findElement(By.cssSelector("[data-test-id=order-sucsess]")).getText().trim();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", actalText);

    }

    private void assertEquals(String s, String actalText) {
    }

}
