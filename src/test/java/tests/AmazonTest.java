package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.SearchResultsPage;
import driversSetup.DriverSetup;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class AmazonTest extends DriverSetup{

	@Parameters()
	@Test(description = "Test Description")
	public void groupSetup() throws Exception{
		HomePage.putSearch("ipad air 2 case");
		HomePage.getSearchButton().click();
		SearchResultsPage.refinePlasticCase().click();

		SearchResultsPage.refinePutPrize(20, 100);

		// get 5 items from seasrch
		int limit = 5;

		List<SearchItem> resultList = SearchResultsPage.getResultList(limit);

		// #5
		System.out.println();
		for(int i=0;i<resultList.size();i++) {
			System.out.println(resultList.get(i).name + "|" + resultList.get(i).price + "|" + resultList.get(i).score);
		}

		// #6
		for(int i=0;i<limit;i++){
			Assert.assertTrue(resultList.get(i).price >= 20 && resultList.get(i).price <= 100);
		}

		// #6
		SearchItem.sortByPrice(resultList);

		// #7
		SearchItem.sortByScore(resultList);

		// #7
		SearchItem.sortByPrice(resultList);
		for(int i = 0; i < limit - 1; i++) {
			Assert.assertTrue(resultList.get(i).price <= resultList.get(i).price);
		}

		// #8
		System.out.println("\nRecommended by Price:");
		System.out.println(resultList.get(0).name + "|" + resultList.get(0).price + "|" + resultList.get(0).score);
		SearchItem.sortByScore(resultList);
		System.out.println("\nRecommended by Score:");
		System.out.println(resultList.get(5-1).name + "|" + resultList.get(limit - 1).price + "|" + resultList.get(limit - 1).score);
	}
	

}
