package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import driversSetup.DriverSetup;
import tests.SearchItem;

import java.util.ArrayList;
import java.util.List;

public class SearchResultsPage extends DriverSetup{

    private static WebElement element = null;

    public static WebElement refinePlasticCase() {
        WebElement element = driver.findElement(By.xpath("//*[@id='leftNavContainer']/ul[9]/div/li[1]/span/span/div/label/span/span"));
        return element;
    }

    public static void refinePutPrize(int min, int max) throws InterruptedException {
        boolean stop = true;
        while(stop) {
            try{
                driver.findElement(By.cssSelector("#leftNavContainer form[method=\"get\"] input#low-price[type=\"text\"][name=\"low-price\"]")).sendKeys(String.valueOf(min));
                driver.findElement(By.cssSelector("#leftNavContainer form[method=\"get\"] input#high-price[type=\"text\"][name=\"high-price\"]")).sendKeys(String.valueOf(max));
                driver.findElement(By.cssSelector("#leftNavContainer form[method=\"get\"] input.a-button-input[value=\"Go\"]")).click();
                stop = false;
            } catch( Exception e) {
                System.out.println("Oops... Do it again...");
            }
        }
    }

    public static List<SearchItem> getResultList(int numb) {
        List<WebElement> resultList = driver.findElements(By.xpath("//li[contains(@id,'result_')]")).subList(0, numb);
        List<SearchItem> searchList= new ArrayList<>();
        for(int i=0;i<resultList.size();i++){
            searchList.add(new SearchItem(
                    resultList.get(i).findElement(By.xpath("./div/div[3]/div[1]/a/h2")).getText(),
                    Float.valueOf(resultList.get(i).findElement(By.xpath("./div/div[4]/div[1]/a/span[1]")).getAttribute("innerHTML").substring(1)),
                    Float.valueOf(resultList.get(i).findElement(By.xpath("./div/div[6]/span/span/a/i[1]/span")).getAttribute("innerHTML").split(" ")[0]))
            );
        }
        return searchList;
    }



}
