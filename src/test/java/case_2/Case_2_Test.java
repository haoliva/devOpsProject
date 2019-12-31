package case_2;

import java.io.FileInputStream;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import case_1.NewToursPage;

public class Case_2_Test {

	WebDriver driver;
	Workbook wb;
	Sheet sh;
	int nrows;
	int ncols;
	
	NewToursPage lp;
	
	@BeforeTest
	public void OpenBrowser() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Selenium Training\\Installation Stuff\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();

		driver.get("http://www.newtours.demoaut.com/");
		driver.manage().timeouts().implicitlyWait(85, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	
	@DataProvider
	public Object[][]testDataFeed() throws Exception{
		
		FileInputStream fis = new FileInputStream("C:\\Users\\Hernan\\Selenium\\ContTestingInDevOps_Cert_Project\\src\\test\\resources\\data\\TestData.xlsx");
		
		wb = WorkbookFactory.create(fis);
		sh = wb.getSheet("loginData");
		
		nrows = sh.getLastRowNum()+1;
//		System.out.println(nrows);
		
		ncols = sh.getRow(0).getLastCellNum();
//		System.out.println(ncols);
		
		Object[][] loginData = new Object[nrows][ncols];
		
		for(int i=0;i<nrows; i++) {
			for(int j=0; j<ncols; j++) {
				loginData[i][j] = sh.getRow(i).getCell(j).toString();
				//System.out.println(loginData[i][j]);
			}
		}
		return loginData;	
	}
	
	
	@Test(dataProvider = "testDataFeed")
	public void Login(String eMail, String password) throws Exception {
		lp = new NewToursPage(driver);
		lp.Login(eMail, password);		
	}
	
	@Test(dependsOnMethods="Login")
	public void FlightDetails(){
		lp.FlightDetails();
	}
	
	@Test(dependsOnMethods="FlightDetails")
	public void Depart(){
		lp.Depart();
	}
	
	@Test(dependsOnMethods="Depart")
	public void Return() {
		lp.Return();
	}
	
	@Test(dependsOnMethods="Return")
	public void Purchase() {
		lp.Purchase();
	}	
	
	
	@Test(dependsOnMethods="Purchase")
	public void Logout(){
		lp.Logout();
	}
	
	
	@AfterTest
	public void QuitBrowser() {
		driver.quit();
	}
	
}
