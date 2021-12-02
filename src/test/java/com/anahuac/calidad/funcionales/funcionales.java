//Entrega Final Miguel Lezama
//Calidad y Pruebas de Software

//TEST Funcional con SELENIUM

package com.anahuac.calidad.funcionales;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;


@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class funcionales {
	private WebDriver driver;
	private String url;
	JavascriptExecutor js;
	
	//Bofore, After
	@SuppressWarnings("deprecation")
	@Before
	public void setUp() throws Exception {
		WebDriverManager.chromedriver().setup();
	    driver = new ChromeDriver();
	    url = "https://mern-crud.herokuapp.com";
	    driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	    js = (JavascriptExecutor) driver;
	  }
	  
	@After
	public void tearDown() throws Exception {
		driver.quit();
	 }
	 
	
	
	 // CREATE
	@Test
	public void testACreate() {
		driver.get(url);
		
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button"));
		elementBtn.click();
		
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.sendKeys("Mike 1");
		
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.sendKeys("lezama1@gmail.com");
		
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.sendKeys("23");
		
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		WebElement elementOptionMale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		elementOptionMale.click();
		
		WebElement elementAdd = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementAdd.click();
		sleep(1.2);
		
		String correct = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
		
		assertEquals("Successfully added!", correct);
		System.out.print(correct + "\nUsuario correctamente añadido.");				
	}
	 
	 
	 
	 
	 // DELETE
	 @Test
	 public void testMDelete() {
		
		fallbackUser();
		driver.get(url);
		
		WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody"));
		WebElement registro = table.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		WebElement email = registro.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[2]"));
		
		String emailABorrar = email.getText();
		System.out.print("Email a borrar: " + emailABorrar);
		
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[2]"));
		elementBtn.click();
		sleep(1.2);
		
		WebElement elementConfirm = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]"));
		elementConfirm.click();
		sleep(1.2);
		

		// Bool de lo que vamos a borrar.
		boolean deleted = false;

		// Si la tabla NO contiene el email borrado, entonces se borro.
		if (!table.getText().contains(emailABorrar)) {
			
			// Cambiamos el bool a true.
			deleted = true;
			
			// Imprimimos lo que sucede.
			System.out.print("\nUsuario borrado correctamente.");
		}
		
		assertTrue(deleted);
	}

	 
	 // UPDATE
	 @Test
	public void testCUpdate() {
		driver.get(url);
		fallbackUser();
		
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[1]"));
		elementBtn.click();
		sleep(1.2);

		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.click();
		elementNameInput.clear();
		elementNameInput.sendKeys("Mike 2");

		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.click();
		elementEmailInput.clear();
		elementEmailInput.sendKeys("lezama2@gmail.com");

		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.click();
		elementAge.clear();
		elementAge.sendKeys("24");

		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();

		WebElement elementOptionFemale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[2]"));
		elementOptionFemale.click();

		WebElement elementSave = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementSave.click();

		sleep(1.2);

		String correct = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();

		assertEquals("", correct);
		System.out.print(correct + "\nUsuario correctamente actualizado.");	
	}

	 
	 
	 
	 
	// RETRIEVE
	 @Test
	 public void testGRetrieve() throws Exception {
		 fallbackUser();

		 String Name = "Mike Fallback";
		 String Email = "mikefallback@gmail.com";
		 String Edad = "20";
		 String Genero = "m"; 

		 driver.get(url);

		String RetrievedName = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]")).getText();
		String RetrievedEmail = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[2]")).getText(); 
		String RetrievedEdad = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[3]")).getText(); 
		String RetrievedGenero = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[4]")).getText(); 
		sleep(5);

	    assertThat(Name, is(RetrievedName));
	    assertThat(Email, is(RetrievedEmail));
	    assertThat(Edad, is(RetrievedEdad));
	    assertThat(Genero, is(RetrievedGenero));

	 }
	 
	 
	 
	 
	 
	 
	 public void fallbackUser() {

		driver.get(url);

		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button"));
		elementBtn.click();

		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.sendKeys("Mike Fallback");

		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.sendKeys("mikefallback@gmail.com");

		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.sendKeys("20");

		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();

		WebElement elementOptionMale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		elementOptionMale.click();

		WebElement elementAdd = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementAdd.click();
		
		WebElement closeBtn = driver.findElement(By.xpath("/html/body/div[2]/div/i"));
		closeBtn.click();
	}


	public void sleep(double d) {
		
		try {
	        Thread.sleep((long) (d * 1000));
	    } catch (InterruptedException ex) {}
	    
	}


}






