package org.adnabuteststore;

import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {
	public static void main(String[] args) {

		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

		try {

			driver.get("https://adnabu-store-assignment1.myshopify.com/password");

			WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
			passwordField.sendKeys("AdNabuQA");

			WebElement enterBtn = driver.findElement(By.xpath("//button[@type='submit']"));
			enterBtn.click();

			wait.until(ExpectedConditions.urlContains("myshopify.com"));

			WebElement product = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Shop products']")));
			product.click();

			WebElement searchIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//summary[@aria-label='Search']")));
			searchIcon.click();
			WebElement searchBox = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='Search-In-Modal']")));
			searchBox.sendKeys("Selling Plans Ski Wax");
			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);

			WebElement product1 = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[contains(text(),'Selling')] )[2]")));

			JavascriptExecutor j = (JavascriptExecutor) driver;
			j.executeScript("arguments[0].scrollIntoView", product1);
			product1.click();

			WebElement addToCart = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("(//button[@type='submit'])[2]")));
			j.executeScript("arguments[0].scrollIntoView", addToCart);
			addToCart.click();

			WebElement checkOut = wait.until(ExpectedConditions.elementToBeClickable(By.id("CartDrawer-Checkout")));
			checkOut.click();

		} catch (Exception e) {
			System.out.println("Test Failed due to exception: " + e.getMessage());
		} finally {
	            driver.quit();
		}
	}

}
