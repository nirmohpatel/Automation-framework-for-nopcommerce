package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class Test_Home_Page_NopCommerc
{
    static WebDriver driver;

    public static void clickonElement(By by)
    {
        driver.findElement(by).click();
    }

    public static void setBrowser() {
        // setting up chrome driver path
        System.setProperty("webdriver.chrome.driver","C:\\Soft\\Chrome_Driver\\83\\chromedriver.exe");
        // create chrome driver object to open Google Chrome browser
        driver=new ChromeDriver();
        // maximising screen
        driver.manage().window().maximize();
        // Appling implicity wait of 30 sec to driver instance
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // open the URL
        driver.get("https://demo.nopcommerce.com/");
    }
    public static void closeBrowser()
    {
        driver.close();
    }

    public static void main(String[] args) {
        setBrowser();
        closeBrowser();
    }


    public static void UserShouldBeAbleToVerifyErrorMessage()
    {
        setBrowser();
        clickonElement(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li[1]/a"));
    }
}
