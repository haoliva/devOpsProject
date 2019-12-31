package case_4;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import case_1.NewToursPage;


public class Case_4_Test {
	
	public WebDriver driver;
	Workbook wb;
	Sheet sh;
	int nrows;
	int ncols;
	
	String homePage = "http://newtours.demoaut.com";
	String url = "";
    HttpURLConnection huc = null;
    int respCode = 200;
	
	NewToursPage lp;
	
	@BeforeTest
	public void OpenBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Training\\Installation Stuff\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get(homePage);
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@DataProvider
	public Object[][]testDataFeed() throws Exception{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Hernan\\Selenium\\Selenium_MVN_Cert_Project\\src\\test\\resources\\data\\TestDataAMZ.xlsx");
		
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("loginData");
		
		nrows = sh.getLastRowNum()+1;
		
		ncols = sh.getRow(0).getLastCellNum();
		
		Object[][] loginData = new Object[nrows][ncols];
		
		for(int i=0;i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				loginData[i][j] = sh.getRow(i).getCell(j).toString();
			}
		}
		return loginData;	
	}
	
/*
	@Test(dataProvider = "testDataFeed")
	public void Login(String eMail, String password) throws Exception {
		lp = new NewToursPage(driver);
		lp.Login(eMail, password);		
	}
*/
	@Test()
	public void BrokenLinks(){
		List<WebElement> links = driver.findElements(By.tagName("a"));
        
        Iterator<WebElement> it = links.iterator();
        
        while(it.hasNext()){
            
            url = it.next().getAttribute("href");
            
            System.out.println(url);
        
            if(url == null || url.isEmpty()){
            	System.out.println("URL is either not configured for anchor tag or it is empty");
                continue;
            }
            
            if(!url.startsWith(homePage)){
                System.out.println("URL belongs to another domain, skipping it.");
                continue;
            }
            
            try {
                huc = (HttpURLConnection)(new URL(url).openConnection());
                
                huc.setRequestMethod("HEAD");
                
                huc.connect();
                
                respCode = huc.getResponseCode();
                
                if(respCode >= 400){
                    System.out.println(url+" is a broken link");
                }
                else{
                    System.out.println(url+" is a valid link");
                }
                    
            } catch (MalformedURLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
	}
	
	
	@AfterTest
	public void QuitBrowser() {
		driver.quit();
	}
}
