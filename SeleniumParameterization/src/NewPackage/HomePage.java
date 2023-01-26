package NewPackage;

import static org.junit.Assert.assertEquals;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class HomePage extends BaseClass{

	public HomePage(WebDriver driver, ExtentTest test){
		this.driver = driver;
		this.test = test;
        PageFactory.initElements(driver, this);
	}
	
	public static String generateRandomname(int len) {
		String chars = "abcdefghijklmnopqrstuvwxyz";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
	
	public static String generateRandomPostCode() {
		String chars = "0123456789";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(6);
		for (int i = 0; i < 6; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}
	
	@FindBy(xpath=".//input[@ng-model='fName']")
	WebElement FristName;
	
	@FindBy(xpath=".//input[@ng-model='lName']")
	WebElement LastName;
	
	@FindBy(xpath=".//input[@ng-model='postCd']")
	WebElement PostCode;
	
	@FindBy(xpath=".//button[@type='submit']")
	WebElement Addcustomer;
	
	@FindBy(xpath=".//strong[@class='mainHeading']")
	WebElement Actualtitle;

	public void VerifyTitleofPage() {
		String ActualTitle = Actualtitle.getText();
	    String ExpectedTitle = "XYZ Bank";
	    assertEquals(ExpectedTitle,ActualTitle);
	    System.out.println("Title Verified succefully as: "+ActualTitle);
	    test.log(LogStatus.PASS, "Verify Title", "Title verified successfully"+ActualTitle);
	}

	public void FillCustomerDetails(String Fn, String Ln, String Pc) throws InterruptedException {
		//String Fname = "Test" + generateRandomname(5);
	    //String Lname = "Test" + generateRandomname(4);
	    //String Pcode = generateRandomPostCode();
		
		String Fname = Fn;
	    String Lname = Ln;
	    String Pcode = Pc;
	    
	    FristName.click();
	    FristName.clear();
	    FristName.click();
	    FristName.sendKeys(Fname);
	    Thread.sleep(2000);
	    
	    LastName.click();
	    LastName.clear();
	    LastName.click();
	    LastName.sendKeys(Lname);
	    Thread.sleep(2000);
	    
	    PostCode.click();
	    PostCode.clear();
	    PostCode.click();
	    PostCode.sendKeys(Pcode);
	    Thread.sleep(2000);
	    
	    System.out.println("Customer details filled successfully");
	    System.out.println("First name as: "+Fname);
	    System.out.println("last name succefully as: "+Lname);
	    System.out.println("post code succefully as: "+Pcode);
	    test.log(LogStatus.PASS, "Fill customer details", "Customer details filled successfully");
	    test.log(LogStatus.PASS, "First Name", ""+Fname);
	    test.log(LogStatus.PASS, "Last Name", ""+Lname);
	    test.log(LogStatus.PASS, "Post Code", ""+Pcode);
	    
	    
	    
	    Addcustomer.click();
	    Thread.sleep(4000);
	    System.out.println("Clicked on Add Customer button");
	    test.log(LogStatus.PASS, "Click on ADD Customer button", "Clicked on Add Customer button");
	}

	public void VerifyCustomerIsAdded() throws InterruptedException {
	    // Switching to Alert and Capturing alert message. 
        Alert alert = driver.switchTo().alert();
        String alertMessage= driver.switchTo().alert().getText();
        
        // Displaying alert message		
        System.out.println("Alert message as:"+alertMessage);
        test.log(LogStatus.PASS, "Verify Alert message", "Alert Message"+alertMessage);
        Thread.sleep(1000);
      		
        // Accepting alert		
        alert.accept();	
        Thread.sleep(3000);
        System.out.println("Click Ok button of alert button");
        test.log(LogStatus.PASS, "Accept the Alert", "Click on Okay button of Alert");
	}
}
