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


//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class funcionales {
	private WebDriver driver;
	private String url;
	JavascriptExecutor js;
	
	//Bofore, After
	//@SuppressWarnings("deprecation")
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
	 // Test para agregar CORRECTAMENTE un usuario.
	@Test
	public void testACreate() {
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de boton verde Add New.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button"));
		elementBtn.click();
		
		// Elemento de input Name.
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.sendKeys("Mike 1");
		
		// Elemento de input Email.
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.sendKeys("lezama1@gmail.com");
		
		// Elemento de input Age (solo acepta numeros).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.sendKeys("23");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de male
		WebElement elementOptionMale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		elementOptionMale.click();
		
		// Elemento boton verde Add.
		WebElement elementAdd = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementAdd.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de texto que indica que se añadio exitosamente un usuario.
		String correct = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
		
		// Comprobamos que fue exitoso el registro.
		assertEquals("Successfully added!", correct);
		System.out.print(correct + "\nUsuario correctamente añadido.");
		
		// Al finalizar aparecera un nuevo usuario (Mike 1).
		
	}
	 
	 
	 
	 
	 // DELETE
	 @Test
	 public void testMDelete() {
		
		// Funcion para siempre tener un usuario, y no tener error en las pruebas de Edit, Delete
		fallbackUser();
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de la tabla
		WebElement table = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody"));
		WebElement registro = table.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr"));
		WebElement email = registro.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr/td[2]"));
		
		// El email que vamos a borrar
		String emailABorrar = email.getText();
		System.out.print("Email a borrar: " + emailABorrar);
		
		
		// Elemento de boton negro Delete (debe existir al menos un usuario para que funcione).
		// Se borrara el registro mas reciente.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[2]"));
		elementBtn.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de boton rojo Yes para confirmar el borrado.
		WebElement elementConfirm = driver.findElement(By.xpath("/html/body/div[2]/div/div[3]/button[1]"));
		elementConfirm.click();
		
		// Paramos 1.2 segs para ver que sucede
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
		
		// Hacemos un assertTrue con el bool creado.
		assertTrue(deleted);
	
		
		// Al finalizar el usuario deberá dejar de existir.
	}

	 
	 // UPDATE
	 @Test
	public void testCUpdate() {
		
		// Funcion para siempre tener un usuario, y no tener error en las pruebas de Edit, Delete
		fallbackUser();
		
		// Iniciar driver con URL dada.
		driver.get(url);
		
		// Elemento de boton azul Edit (debe existir al menos un usuario para que funcione).
		// Se editara el registro mas reciente.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/table/tbody/tr[1]/td[5]/button[1]"));
		elementBtn.click();
		
		// Esperamos 1.2 segs para que aparezca la informacion previa.
		sleep(1.2);
		
		// Elemento de input Name (lo seleccionamos y borramos todo su interior).
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.click();
		elementNameInput.clear();
		elementNameInput.sendKeys("Mike 2");
		
		// Elemento de input Email (lo seleccionamos y borramos todo su interior).
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.click();
		elementEmailInput.clear();
		elementEmailInput.sendKeys("lezama2@gmail.com");
		
		// Elemento de input Age (lo seleccionamos y borramos todo su interior).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.click();
		elementAge.clear();
		elementAge.sendKeys("24");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de female (para cambiar).
		WebElement elementOptionFemale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[2]"));
		elementOptionFemale.click();
		
		// Elemento boton azul Save (guardamos al usuario editado).
		WebElement elementSave = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementSave.click();
		
		// Paramos 1.2 segs para ver que sucede
		sleep(1.2);
		
		// Elemento de texto que indica que se actualizo exitosamente un usuario.
		String correct = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[4]/div/p")).getText();
		
		// Comprobamos que fue exitoso la actualizacion del registro.
		assertEquals("Successfully updated!", correct);
		System.out.print(correct + "\nUsuario correctamente actualizado.");
		
		// Al finalizar el usuario cambiara de ser Elon Musk a Jeff Bezos.
		
	}

	 
	 
	 
	 
	// RETRIEVE
	 @Test
	 public void testGRetrieve() throws Exception {
		 // Include a Student for the search
		 fallbackUser();
		 // Expected values
		 String Name = "Mike Fallback";
		 String Email = "mikefallback@gmail.com";
		 String Edad = "20";
		 String Genero = "m"; 
		 
		 // Get the base URL
		 driver.get(url);
		 
		 // Retrieve values of the table
		 String RetrievedName = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[1]")).getText();
		 String RetrievedEmail = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[2]")).getText(); 
		 String RetrievedEdad = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[3]")).getText(); 
		 String RetrievedGenero = driver.findElement(By.xpath("/html/body/div/div/div[2]/table/tbody/tr[1]/td[4]")).getText(); 
	    
	    // Make a pause in order for the server to catch up with the code
	    sleep(5);
	    // Verify presence of texts
	    assertThat(Name, is(RetrievedName));
	    assertThat(Email, is(RetrievedEmail));
	    assertThat(Edad, is(RetrievedEdad));
	    assertThat(Genero, is(RetrievedGenero));

	 }
	 
	 
	 
	 
	 
	 
	 public void fallbackUser() {
		
		// Añadir un usuario siempre, para nunca dar error.
		driver.get(url);
		
		// Elemento de boton verde Add New.
		WebElement elementBtn = driver.findElement(By.xpath("//*[@id=\"root\"]/div/div[2]/button"));
		elementBtn.click();
		
		// Elemento de input Name.
		WebElement elementNameInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[1]/div/input"));
		elementNameInput.sendKeys("Mike Fallback");
		
		// Elemento de input Email.
		WebElement elementEmailInput = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[2]/div/input"));
		elementEmailInput.sendKeys("mikefallback@gmail.com");
		
		// Elemento de input Age (solo acepta numeros).
		WebElement elementAge = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[1]/div/input"));
		elementAge.sendKeys("20");
		
		// Elemento de lista Gender (despliega una lista con Male, Female, Do Not Disclose).
		WebElement elementGenre = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div"));
		elementGenre.click();
		
		// Pre-seleccionamos la opcion de male
		WebElement elementOptionMale = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/div[3]/div[2]/div/div[2]/div[1]"));
		elementOptionMale.click();
		
		// Elemento boton verde Add.
		WebElement elementAdd = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/form/button"));
		elementAdd.click();
		
		WebElement closeBtn = driver.findElement(By.xpath("/html/body/div[2]/div/i"));
		closeBtn.click();
	}





// Funcion para detener el selenium y ver lo que esta sucediendo.
	public void sleep(double d) {
		
		try {
	        // Tiempo que va a dormir las funciones en milisegundos
	        Thread.sleep((long) (d * 1000));
	    } catch (InterruptedException ex) {}
	    
	}


}






