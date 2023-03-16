package pt.ua.tqs.webPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ConfirmationPage {
   private WebDriver driver;

   //Constructor
   public ConfirmationPage(WebDriver driver){
    this.driver=driver;

    //Initialise Elements
    PageFactory.initElements(driver, this);
   }

   public void assertTitle(String Title){
        String currentTitle = driver.getTitle();

        assertThat(currentTitle, is(Title));
   }

}
