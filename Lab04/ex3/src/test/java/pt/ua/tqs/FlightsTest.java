// Generated by Selenium IDE
package pt.ua.tqs;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import pt.ua.tqs.webPages.ChoosePage;
import pt.ua.tqs.webPages.ConfirmationPage;
import pt.ua.tqs.webPages.FormPage;
import pt.ua.tqs.webPages.HomePageTest;

import java.util.concurrent.TimeUnit;



@ExtendWith(SeleniumJupiter.class)
public class FlightsTest {
  WebDriver driver;

  @BeforeEach
  public void setup(){
      //use FF Driver
      driver = new FirefoxDriver();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void newTest() throws InterruptedException {
      //Create object of HomePage Class
      HomePageTest home = new HomePageTest(driver);

      home.select_list_From("Portland");

      home.select_list_To("Dublin");

      home.clickOnFindButton();

      ChoosePage choosePage = new ChoosePage(driver);

      choosePage.clickOnFindButton("UA234");

      FormPage formPage = new FormPage(driver);

      formPage.setName("Tom");
      formPage.setAddress("123 London UK");
      formPage.setCity("London");
      formPage.setState("London");
      formPage.setZip("123456");
      formPage.select_Card_Tpe("Diner's Club");
      formPage.setCardNumber("123456");
      formPage.setCardMonth("11");
      formPage.setcreditCardYear("2023");
      formPage.setNameOnCard("Tom");
      formPage.clickButton();

      ConfirmationPage confirmationPage = new ConfirmationPage(driver);

      Thread.sleep(4000);
      
      confirmationPage.assertTitle("BlazeDemo Confirmation");

      return;
  }

   @AfterEach
   public void close(){
         driver.close();
      }
  }