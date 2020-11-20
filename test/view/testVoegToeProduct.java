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
        String juisteNaam,juisteBeschrijving,juisteEenheid,juisteCalorieen,juisteGram;

        @Before
        public void setUp () throws Exception {
             juisteNaam = "Wit Brood";
             juisteBeschrijving = "Beschikbaar in groot of klein formaat";
             juisteEenheid = "per snee";
             juisteCalorieen = "77";
             juisteGram = "35";
            System.setProperty("webdriver.chrome.driver", "C:\\Users\\diete\\Desktop\\Semester3\\Web2\\Programmas\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
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
            //in demo vullen ze alles in met "" overbodig?
            WebElement knop = driver.findElement(By.id("vind"));
            knop.click();
            //driver.findElement(By.id("vind")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));

            assertTrue(containsWebElementsWithText(lis, "Naam mag niet leeg zijn."));
            assertTrue(containsWebElementsWithText(lis, "Beschrijving mag niet leeg zijn."));
            assertTrue(containsWebElementsWithText(lis, "Calorieen moet een getal boven 0 zijn."));
            assertTrue(containsWebElementsWithText(lis, "Gram moet een getal boven 0 zijn."));
            driver.quit();
        }
        @Test
        public void test_NaamIsLeeg () {
            //overbodig door public void test_AlleVeldenLeeg_TerugOpFormulier_AlleErrors()?

            vullAllesJuistIn();


            WebElement naamInput = driver.findElement(By.id("naam"));
            naamInput.clear();
            naamInput.sendKeys("");

            driver.findElement(By.id("vind")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Naam mag niet leeg zijn."));
            //driver.quit();
        }
        /* overbodig?
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
        */
        //Moeten de volgende test uitgevoerd worden? Geleerd bij oop maar zie het nut niet om en nul en negatieve getallen te testen indien de voorwaarde (Gram>0) is idem voor calorieen
        @Test
        public void test_CalorieenIsNul () {
            vullAllesJuistIn();

            WebElement calorieenInput = driver.findElement(By.id("calorieen"));
            calorieenInput.clear();
            calorieenInput.sendKeys("0");

            driver.findElement(By.id("vind")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Calorieen moet een getal boven 0 zijn."));
            driver.quit();

        }
        @Test
        public void test_CalorieenIsNegatief () {
            vullAllesJuistIn();

            WebElement calorieenInput = driver.findElement(By.id("calorieen"));
            calorieenInput.clear();
            calorieenInput.sendKeys("-10");

            driver.findElement(By.id("vind")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Calorieen moet een getal boven 0 zijn."));
            driver.quit();
        }
        @Test
        public void test_GramIsNul () {
            vullAllesJuistIn();

            WebElement gramInput = driver.findElement(By.id("gram"));
            gramInput.clear();
            gramInput.sendKeys("0");

            driver.findElement(By.id("vind")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Gram moet een getal boven 0 zijn."));
        }
        @Test
        public void test_GramIsNegatief () {
            vullAllesJuistIn();

            WebElement gramInput = driver.findElement(By.id("gram"));
            gramInput.clear();
            gramInput.sendKeys("-3");

            driver.findElement(By.id("vind")).click();

            ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
            assertTrue(containsWebElementsWithText(lis, "Gram moet een getal boven 0 zijn."));
        }


        @Test
        public void test_AllesJuistIngevoerd_GaNaarOverview () {
            vullAllesJuistIn();
            driver.findElement(By.id("vind")).click();

        /* werkt niet omdat je altijd op http://localhost:8080/ProductInfo?command=voegToe uitkomt
        String URL = driver.getCurrentUrl();
        assertEquals(URL, "http://localhost:8080" );
        */
            //dus zoek op <h2>OVERZICHT VAN ALLE PRODUCTEN</h2>
            ArrayList<WebElement> h2s = (ArrayList<WebElement>) driver.findElements(By.tagName("h2"));
            assertTrue(containsWebElementsWithText(h2s,"OVERZICHT VAN ALLE PRODUCTEN"));
            //vind het net toegevoegd product door naar de juiste "td"s te zoeken

            ArrayList<WebElement> tds = (ArrayList<WebElement>) driver.findElements(By.tagName("td"));
            assertTrue(containsWebElementsWithText(tds,juisteNaam));
            assertTrue(containsWebElementsWithText(tds, juisteBeschrijving));
            assertTrue(containsWebElementsWithText(tds, juisteCalorieen +" " + juisteEenheid));
            assertTrue(containsWebElementsWithText(tds, juisteGram));

        }
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
            naamInput.sendKeys(juisteNaam);

            WebElement beschrijvingInput = driver.findElement(By.id("beschrijving"));
            beschrijvingInput.clear();
            beschrijvingInput.sendKeys(juisteBeschrijving);

            WebElement calorieenInput = driver.findElement(By.id("calorieen"));
            calorieenInput.clear();
            calorieenInput.sendKeys(juisteCalorieen);

            WebElement eenheidInput = driver.findElement(By.id("eenheid"));
            eenheidInput.clear();
            eenheidInput.sendKeys(juisteEenheid);

            WebElement gramInput = driver.findElement(By.id("gram"));
            gramInput.clear();
            gramInput.sendKeys(juisteGram);


        }

    }