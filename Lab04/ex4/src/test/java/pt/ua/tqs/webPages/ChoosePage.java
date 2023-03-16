package pt.ua.tqs.webPages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;


public class ChoosePage {
   private WebDriver driver;

   //Page URL

   //Locators


   //Constructor
   public ChoosePage(WebDriver driver){
    this.driver=driver;

    //Initialise Elements
    PageFactory.initElements(driver, this);
   }

   public void clickOnFindButton(String value){
        

        // Gets the form element from an ID and submits
        driver.findElement(By.name(value)).submit();


   }
}

