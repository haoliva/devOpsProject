package case_1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.PageFactory;

public class NewToursPage {
	
	// Constructor
	public NewToursPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	

	@FindBy(name="userName")
	WebElement UserName;
	
	@FindBy(name="password")
	WebElement Password;
	
	@FindBy(name="login")
	WebElement LoginButton;
	
	@FindBy(name="toPort")
	WebElement ToPort;
	
	@FindBy(xpath="//input[@value=\"Business\"]")
	WebElement ServiceClass;
	
	@FindBy(name="findFlights")
	WebElement Continue;

	@FindBy(xpath="//input[@value='Blue Skies Airlines$361$271$7:10']")
	WebElement Flight;
	
	@FindBy(xpath="//input[@value='Blue Skies Airlines$631$273$14:30']")
	WebElement FlightReturn;
	
	@FindBy(name="reserveFlights")
	WebElement ReserveFlights;

	@FindBy(linkText="Cruises")
	WebElement Cruises;

	@FindBy(linkText="SIGN-OFF")
	WebElement Logout;
	
	@FindBy(name="passFirst0")
	WebElement passFirst;
	
	@FindBy(name="passLast0")
	WebElement passLast;
	
	@FindBy(name="creditnumber")
	WebElement creditNumber;
	
	@FindBy(name="buyFlights")
	WebElement buyFlights;
	
	@FindBy(xpath="//span[contains(text(),'Cruise Itinerary')]")
	WebElement cruiseItinerary;
		

	public void Login(String eMail, String password) throws Exception {
		UserName.clear();
		UserName.sendKeys(eMail);
		
		Password.clear();
		Password.sendKeys(password);
		
		LoginButton.click();
	}

	
	public void FlightDetails() {	
		Select ArrivingIn = new Select(ToPort);
		ArrivingIn.selectByVisibleText("New York");
		ServiceClass.click();
		Continue.click();
	}
	
	public void Depart() {		
		Flight.click();
	}
	
	public void Return() {		
		FlightReturn.click();
		ReserveFlights.click();
	}
	
	public void Purchase() {
		passFirst.clear();
		passFirst.sendKeys("John");
		passLast.clear();
		passLast.sendKeys("Smith");
		creditNumber.clear();
		creditNumber.sendKeys("1111111111111");
		buyFlights.click();
	}
	
	public void Cruises() {
		Cruises.click();
		if (cruiseItinerary.isDisplayed()) {
			System.out.println("Great, you are on Cruises page!");
		}
		else {
			System.out.println("Something is wrong, you should be on Cruises page.");
		}
	}
	
	public void Logout() {
		Logout.click();
	}
	
}
