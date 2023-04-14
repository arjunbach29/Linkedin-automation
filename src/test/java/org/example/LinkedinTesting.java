package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.sdk.resources.ResourceBuilder;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.ResourceBundle;


public class LinkedinTesting {

    WebDriver driver;

    @BeforeClass
    @Parameters({"browser", "url"})
    public void UdemyTest(String browser, String Link) {

        if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            WebDriverManager.edgedriver().setup();
            driver = new EdgeDriver();
        }

        driver.get(Link);
        driver.manage().window().maximize();

    }

    @Test
    void accessLogin() throws InterruptedException {

        String email = " ";
        String password = "";
        WebElement e = driver.findElement(By.id("session_key"));

        WebElement p = driver.findElement(By.id("session_password"));
        e.sendKeys(email);
        Thread.sleep(2000);
        p.sendKeys(password);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
        Thread.sleep(2000);

    }
    @Test
    void wrongLogin() throws InterruptedException {
        WebElement e = driver.findElement(By.id("session_key"));

        WebElement p = driver.findElement(By.id("session_password"));

        e.sendKeys("raghav@gmail.com");
        Thread.sleep(2000);
        p.sendKeys("dxfdx");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
        Thread.sleep(2000);
        e.clear();
        Thread.sleep(2000);
        p.clear();
        Thread.sleep(2000);


    }

    @Test
    void CorrectLogin() throws InterruptedException {
        WebElement e = driver.findElement(By.id("session_key"));

        WebElement p = driver.findElement(By.id("session_password"));

        ResourceBundle r = ResourceBundle.getBundle("config");
        String a = r.getString("email");
        String b = r.getString("password");

        e.sendKeys(a);
        Thread.sleep(2000);
        p.sendKeys(b);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//button[contains(text(),'Sign in')]")).click();
        Thread.sleep(2000);
    }


    @Test
    void searchTest() throws InterruptedException {
        WebElement s = driver.findElement(By.xpath("//input[@placeholder='Search']"));
        s.sendKeys("yashwanth");
        s.click();

        Thread.sleep(2000);


    }

    @AfterClass
    void closeDriver(){
        driver.close();
    }





}
