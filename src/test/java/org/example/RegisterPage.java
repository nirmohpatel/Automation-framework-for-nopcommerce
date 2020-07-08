package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

public class RegisterPage<clickOnElement>
{

    static WebDriver driver;

    public static void waitUntilElementIsClickAble(By by,int TimeInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver,TimeInSeconds);

    }
    public static void clickOnElement(By by)
    {
        driver.findElement(by).click();
    }

    public static String getTextFromElement(By by) {
        return driver.findElement(by).getText();
    }

    public static void typeText(By by, String text)
    {
        driver.findElement(by).sendKeys(text);
    }
    public static void selectFromDropDownByVisibleText(By by,String text)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByVisibleText(text);
    }
    public static void selectFromDropDownByIndex(By by,int n)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByIndex(n);
    }
    public static void selectFromDropDownByValue(By by,String value)
    {
        Select select = new Select(driver.findElement(by));
        select.selectByValue(value);
    }
    public static long timestamp()
    {
        return(System.currentTimeMillis());
    }

    @BeforeMethod
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
    @Test (priority = 0)
    public void usreShouldBeAbleToRegisterSuccessfully()
    {
        waitUntilElementIsClickAble(By.xpath("//a[@class=\"ico-register\"]"),10);
        clickOnElement(By.xpath("//a[@class=\"ico-register\"]"));
        waitUntilElementIsClickAble(By.xpath("input#register-button"),20);
       waitUntilElementIsClickAble(By.xpath("//input[@id=\"gender-male\"]"),40);
        clickOnElement(By.xpath("//input[@id=\"gender-male\"]"));
        waitUntilElementIsClickAble(By.xpath("//input [@id=\"FirstName\"]"),40);
        typeText(By.xpath("//input [@id=\"FirstName\"]"),"Nirmoh");
        waitUntilElementIsClickAble(By.xpath("//input[@id=\"LastName\"]"),40);
        typeText(By.xpath("//input[@id=\"LastName\"]"),"Patel");
        waitUntilElementIsClickAble(By.xpath("//select[@name=\"DateOfBirthDay\"]"),20);
        selectFromDropDownByIndex(By .xpath("//select[@name=\"DateOfBirthDay\"]"),1);
        selectFromDropDownByVisibleText(By.xpath("//select[@name=\"DateOfBirthMonth\"]"),"July");
        selectFromDropDownByValue(By.xpath("//select[@name=\"DateOfBirthYear\"]"),"1983");
        String email="test+"+timestamp()+"@gmail.com";
        typeText(By.xpath("//input[@id=\"Email\"]"), email);
        typeText(By.xpath("//input[@id=\"Company\"]"),"Atmiya");
        clickOnElement(By.xpath("//input[@id=\"Newsletter\"]"));
        typeText(By.xpath("//input[@id=\"Password\"]"),"Nnstncv@5");
        typeText(By .xpath("//input[@id=\"ConfirmPassword\"]"),"Nnstncv@5");
        clickOnElement(By.xpath("//input[@id=\"register-button\"]"));
        // print Registration completed message
        System.out.println(getTextFromElement(By.xpath("//div/div[text()=\"Your registration completed\"]")));
        // store expected Text value in string
        String expectedtext1 = "Your registration completed";
        // store actual Text value in string
        String actualtext1 = driver.findElement(By.xpath("//div/div[text()=\"Your registration completed\"]")).getText();
        // compare actual text and Expected Text by using Assert
        Assert.assertEquals(actualtext1,expectedtext1);
        // clicking on continue button
        clickOnElement(By.xpath("//input[contains(@value,\"Continue\")]"));

    }

    @Test (priority = 1)
    public void usreShouldBeAbleToReferAProductToAFriendSuccessfilly()
    {       // click on "Computer" from Header menu
        clickOnElement(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li[1]/a"));
        waitUntilElementIsClickAble(By.xpath("//div[@class=\"side-2\"]/div/div[2]/ul/li/ul/li[1]/a"),40);
        // click on "Desktop" from left side menu
        clickOnElement(By.xpath("//div[@class=\"side-2\"]/div/div[2]/ul/li/ul/li[1]/a"));
        waitUntilElementIsClickAble(By.xpath("//div[@class=\"item-grid\"]/div[2]/div/div[2]/h2/a"),30);
        // click on product name "Digital Storm VANQUISH3"
        clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[2]/div/div[2]/h2/a"));
        // click on "Email a friend" button
        clickOnElement(By.xpath("//input[@value=\"Email a friend\"]"));
        // to check correct page is open or not open,To get a unique text from page and compare with expected text
        System.out.println(getTextFromElement(By.xpath("//div/h1[text()=\"Email a friend\"]")));
        // store expected Text value in string
        String expectedtext = "Email a friend";
        // store actual Text value in string
        String actualtext = driver.findElement(By.xpath("//div/h1[text()=\"Email a friend\"]")).getText();
        // compare actual text and Expected Text by using Assert
       Assert.assertEquals(actualtext,expectedtext);
       // store friend email ID in string
       String friendEmail ="patelnirmoh"+timestamp()+"@gmail.com";
       // Enter the Friends email by using timestamp
        typeText(By.xpath("//input[@id=\"FriendEmail\"]"),friendEmail);
        // store user email ID in string
        String userEmail= "nirmohpatelunique1"+timestamp()+"@gmail.com";
        // Enter the user email by using timestamp
        typeText(By.xpath("//input[@id=\"YourEmailAddress\"]"), userEmail);
        // Wright personal message
        typeText(By.xpath("//textarea[@class=\"your-email\"]"),"This product is very Good with best price.");
        // click send Email
        clickOnElement(By .xpath("//input[contains(@value,\"Send email\")]"));
        //To print message "Your message has been sent."
        System.out.println(getTextFromElement(By.xpath("//div/div[@class=\"result\"]")));
        // store expected Text value in string
        String expectedtext2 = "Your message has been sent.";
        // store actual Text value in string
        String actualtext2 = driver.findElement(By.xpath("//div/div[@class=\"result\"]")).getText();
        // compare actual text and Expected Text by using Assert
        Assert.assertEquals(actualtext2,expectedtext2);

    }
        @Test (priority = 2)

    public void userShouldBeAbleToAddProductToBasketSuccessfully()
     {  // click on "Electronics" from Header Menu
         clickOnElement(By.xpath("//div[@class=\"header-menu\"]/ul[1]/li[2]/a"));
         waitUntilElementIsClickAble(By.xpath("//h2/a[text()=\" Cell phones \"]"),40);
         // click on Cell phones
         clickOnElement(By.xpath("//h2/a[text()=\" Cell phones \"]"));
         waitUntilElementIsClickAble(By.xpath("//div[@data-productid=\"18\"]/div[2]/div[3]/div[2]/input[1]"),20);
         // click on Add to cart button for "HTC One Mb Android L 5.0 Lollipop"
         clickOnElement(By.xpath("//div[@data-productid=\"18\"]/div[2]/div[3]/div[2]/input[1]"));
         // click on X button to close page
         clickOnElement(By.xpath("span.close"));
         // add another product to shopping list
         clickOnElement(By.xpath("//div[@class=\"item-grid\"]/div[2]/div/div[2]/div[3]/div[2]/input[1]"));
         // click on X button to close page
         clickOnElement(By.xpath("Span.close"));
         // Click on Shopping cart button top right conner
         clickOnElement(By.xpath("span.cart-label"));
         // store expected Text value in string
         String expectedtext3 = "HTC One Mb Android L 5.0 Lollipop";
         // store actual Text value in string
         String actualtext3 = driver.findElement(By.xpath("//td[4]/a[text()=\"HTC One M8 Android L 5.0 Lollipop\"]")).getText();
         // compare actual text and Expected Text by using Assert
         Assert.assertEquals(actualtext3,expectedtext3);
         // store expected Text value in string
         String expectedtext4 = "HTC One Mini Blue";
         // store actual Text value in string
         String actualtext4 = driver.findElement(By.xpath("//tr[2]/td[4]/a[text()=\"HTC One Mini Blue\"]")).getText();
         // compare actual text and Expected Text by using Assert
         Assert.assertEquals(actualtext4,expectedtext4);
     }

//    @AfterMethod
//    public static void closeBrowser()
//    {
//        driver.close();
//    }

//    //public static void main(String[] args) {
//        //setBrowser();
//    }
}
