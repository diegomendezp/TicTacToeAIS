package es.codeurjc.ais.tictactoe;


import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TicTacToeSystemTest {
	protected WebDriver driver1, driver2;
	
	@BeforeClass
    public static void setupClass() { 
		System.setProperty("webdriver.chrome.driver","/Users/Diegomendez1997/Documents/GitHub/TicTacToeAIS/tic-tac-toe-enunciado/src/test/java/es/codeurjc/ais/tictactoe/chromedriver");
		WebApp.start();	
    }
    
    @AfterClass
    public static void teardownClass() { 
    		WebApp.stop();
    }
    
    @Before
	public void setupTest() {
		driver1 = new ChromeDriver();
		driver2 = new ChromeDriver();
	}

	@After
	public void teardown() {
		if (driver1 != null) {
			driver1.quit();
		}
		if (driver2 != null) {
			driver2.quit();
		}
	}

	@Test
	public void playerOneWinTest() throws InterruptedException{
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		
		driver1.findElement(By.id("nickname")).sendKeys("jorge");
		driver1.findElement(By.id("startBtn")).click();
		
		driver2.findElement(By.id("nickname")).sendKeys("diego");
		driver2.findElement(By.id("startBtn")).click();
		
		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-1")).click();
		driver2.findElement(By.id("cell-7")).click();
		driver1.findElement(By.id("cell-2")).click();
		Thread.sleep(5000);
		String mensaje1 = driver1.switchTo().alert().getText();
		Thread.sleep(5000);
		String mensaje2 = driver2.switchTo().alert().getText();
		assertTrue(mensaje1.contains("jorge wins! diego looses."));	
		assertTrue(true);
	}
	
	@Test
	public void playerTwoWinTest() throws InterruptedException {
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");

		driver1.findElement(By.id("nickname")).sendKeys("jorge");
		driver1.findElement(By.id("startBtn")).click();

		driver2.findElement(By.id("nickname")).sendKeys("diego");
		driver2.findElement(By.id("startBtn")).click();

		driver1.findElement(By.id("cell-5")).click();
		driver2.findElement(By.id("cell-0")).click();
		driver1.findElement(By.id("cell-7")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-4")).click();
		driver2.findElement(By.id("cell-2")).click();
		Thread.sleep(5000);
		String mensaje1 = driver1.switchTo().alert().getText();
		Thread.sleep(5000);
		String mensaje2 = driver2.switchTo().alert().getText();
		assertTrue(mensaje1.contains("diego wins! jorge looses."));
		assertTrue(mensaje2.contains("diego wins! jorge looses."));

	}
	
	@Test
	public void checkDrawTest() throws InterruptedException{
		driver1.get("http://localhost:8080/");
		driver2.get("http://localhost:8080/");
		driver1.findElement(By.id("nickname")).sendKeys("jorge");
		driver1.findElement(By.id("startBtn")).click();

		driver2.findElement(By.id("nickname")).sendKeys("diego");
		driver2.findElement(By.id("startBtn")).click();

		driver1.findElement(By.id("cell-0")).click();
		driver2.findElement(By.id("cell-4")).click();
		driver1.findElement(By.id("cell-8")).click();
		driver2.findElement(By.id("cell-1")).click();
		driver1.findElement(By.id("cell-7")).click();
		driver2.findElement(By.id("cell-6")).click();
		driver1.findElement(By.id("cell-2")).click();
		driver2.findElement(By.id("cell-5")).click();
		driver1.findElement(By.id("cell-3")).click();
		Thread.sleep(5000);
		String mensaje1= driver1.switchTo().alert().getText();
		Thread.sleep(5000);
		String mensaje2 = driver2.switchTo().alert().getText();
		assertTrue(mensaje1.contains("Draw!"));
		assertTrue(mensaje2.contains("Draw!"));
	}

}
