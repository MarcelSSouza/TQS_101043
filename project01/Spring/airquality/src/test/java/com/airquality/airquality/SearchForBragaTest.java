package com.airquality.airquality;

import static org.hamcrest.CoreMatchers.containsString;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SearchForBragaTest {

  private final WebDriver driver;

  @Autowired
  public SearchForBragaTest(WebDriver webDriver) {
    this.driver = webDriver;
  }

/*   @Test
  public void searchForBraga() {
    driver.get("http://localhost:5173/");
    driver.manage().window().setSize(new Dimension(1521, 798));
    driver.findElement(By.cssSelector("input")).click();
    driver.findElement(By.cssSelector("input")).sendKeys("Braga");
    driver.findElement(By.cssSelector(".btn:nth-child(5)")).click();
    driver.findElement(By.cssSelector(".btn:nth-child(14)")).click();
  }
 */
  
}
