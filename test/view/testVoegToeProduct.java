package view;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//Test klasse voegToe.jsp
//enkel eenheid mag voorlopig leeg zijn of eender welke string

public class testVoegToeProduct {
        private WebDriver driver;
        String url = "http://localhost:8080/";

        @Before
        public void setUp () throws Exception {
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\diete\\Desktop\\Semester2\\Web\\Programmas\\chromedriver_win32\\chromedriver.exe");
            WebDriver driver = new ChromeDriver();
            driver.get(url + "productForm.jsp");
        }

        @After
        public void clean () {
            driver.quit();
        }

        //welke testen moeten er exact uitgevoerd worden?
        //waarom aparte klasse gemaakt testLeegVoegToeFormulier als het opniuew gemaakt wordt?
        @Test
        public void test_AlleVeldenLeeg_TerugOpFormulier_AlleErrors () {
            //roep testLeegVoegToeFormulier op?
            //in demo vullen ze alles in met "" overbodig?

            driver.findElement(By.id("submit")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));

            assertTrue(containsWebElementsWithText(lis, "Naam mag niet leeg zijn."));
            assertTrue(containsWebElementsWithText(lis, "Beschrijving mag niet leeg zijn."));
            assertTrue(containsWebElementsWithText(lis, "Calorieen moet een getal boven 0 zijn."));
            assertTrue(containsWebElementsWithText(lis, "Gram moet een getal boven 0 zijn."));
        }
        @Test
        public void test_NaamIsLeeg () {
            //overbodig door public void test_AlleVeldenLeeg_TerugOpFormulier_AlleErrors()?

            vullAllesJuistIn();


            WebElement naamInput = driver.findElement(By.id("naam"));
            naamInput.clear();
            naamInput.sendKeys("");

            driver.findElement(By.id("submit")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Naam mag niet leeg zijn."));
        }
        @Test
        public void test_BeschrijvingIsLeeg () {
            //overbodig door public void test_AlleVeldenLeeg_TerugOpFormulier_AlleErrors()?
            //of zie test_NaamIsLEeg
        }
        @Test
        public void test_CalorieenIsLeeg () {
            //overbodig door public void test_AlleVeldenLeeg_TerugOpFormulier_AlleErrors()?
            //of zie test_NaamIsLEeg

        }

        //Moeten de volgende test uitgevoerd worden? Geleerd bij oop maar zie het nut niet om en nul en negatieve getallen te testen indien de voorwaarde (Gram>0) is idem voor calorieen
        @Test
        public void test_CalorieenIsNul () {
            vullAllesJuistIn();

            WebElement calorieenInput = driver.findElement(By.id("calorieen"));
            calorieenInput.clear();
            calorieenInput.sendKeys("0");

            driver.findElement(By.id("submit")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Calorieen moet een getal boven 0 zijn."));

        }
        @Test
        public void test_CalorieenIsNegatief () {
            vullAllesJuistIn();

            WebElement calorieenInput = driver.findElement(By.id("calorieen"));
            calorieenInput.clear();
            calorieenInput.sendKeys("-10");

            driver.findElement(By.id("submit")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Calorieen moet een getal boven 0 zijn."));

        }
        @Test
        public void test_GramIsNul () {
            //zie calorieen
        }
        @Test
        public void test_GramIsNegatief () {
            //zie calorieen
        }


        @Test
        public void test_AllesJuistIngevoerd_GaNaarOverview () {
            //roep volledigJuistVoegTOeFOrm op?

            vullAllesJuistIn();
            driver.findElement(By.id("submit")).click();

        /* werkt niet omdat je altijd op http://localhost:8080/ProductInfo?command=voegToe uitkomt
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8080" );
        */

            //dus zoek op <h2>OVERZICHT VAN ALLE PRODUCTEN</h2>
            assertEquals("OVERZICHT VAN ALLE PRODUCTEN", driver.findElement(By.tagName("<h2>")));


        }
        @Test
        public void test_ProductWordtToegevoegdInDeLijst () {
            vullAllesJuistIn();
            driver.findElement(By.id("submit")).click();
            //vind de meegegeven data in de lijst


        }


        //methode van Voorbeeld  kan op simpelere manier?
        //moet nog uitzoeken
        private boolean containsWebElementsWithText (ArrayList < WebElement > elements, String text){
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i).getText().equals(text)) {
                    return true;
                }
            }
            return false;
        }

        private void vullAllesJuistIn(){
            WebElement naamInput = driver.findElement(By.id("naam"));
            naamInput.clear();
            naamInput.sendKeys("Wit Brood");

            WebElement beschrijvingInput = driver.findElement(By.id("beschrijving"));
            beschrijvingInput.clear();
            beschrijvingInput.sendKeys("Beschikbaar in groot of klein formaat");

            WebElement calorieenInput = driver.findElement(By.id("calorieen"));
            calorieenInput.clear();
            calorieenInput.sendKeys("77");

            WebElement eenheidInput = driver.findElement(By.id("eenheid"));
            eenheidInput.clear();
            eenheidInput.sendKeys("per snee");

            WebElement gramInput = driver.findElement(By.id("gram"));
            gramInput.clear();
            gramInput.sendKeys("35");


        }

    }
