package pt.ua.tqs.webPages;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;


public class HomePageTest {
   private WebDriver driver;

   //Page URL
   private static String PAGE_URL="https://blazedemo.com/";

   //Locators

    //Find select
    @FindBy(how = How.NAME, using = "fromPort")
    private WebElement fromSelect;

    //Find select
    @FindBy(how = How.NAME, using = "toPort")
    private WebElement toSelect;

   //Find Flights Button
   @FindBy(how = How.TAG_NAME, using = "input")
   private WebElement FindButton;

   //Constructor
   public HomePageTest(WebDriver driver){
       this.driver=driver;
       driver.get(PAGE_URL);
       //Initialise Elements
       PageFactory.initElements(driver, this);
   }

   public void clickOnFindButton(){

        FindButton.click();

   }


    public void select_list_From(String from){
        Select statusDropdown=new Select(fromSelect);
        statusDropdown.selectByVisibleText(from);
    }


    public void select_list_To(String to){
        Select statusDropdown=new Select(toSelect);
        statusDropdown.selectByVisibleText(to);
    }
}
