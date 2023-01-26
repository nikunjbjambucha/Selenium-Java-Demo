package NewPackage;

import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestRunner extends BaseClass{
	ReadExcel readdata = new ReadExcel();
	
    public static String datetime()
    {   Date date = new Date();
        String formattedTime = date.toString();
        String formattedTime1 = formattedTime.replace(" ", "_");
        formattedTime = formattedTime1.replace(":", "_");
        System.out.println(formattedTime);
        return formattedTime;
    }
    
	ExtentReports report = new ExtentReports("./Reports/TestReport_"+datetime()+".html", true);
	ExtentTest test = report.startTest("Create customers");
	@Before
	public void TestInitialize() {
		System.out.println("Test Started");
		
		// declaration and chromedriver
		System.setProperty("webdriver.chrome.driver","F:\\Driver\\chromedriver.exe");
		driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        System.out.println("Browser has been opened");
        test.log(LogStatus.PASS, "Open Browser", "Browser has been opened ");
	}
	
	@Test
	public void main() {
		try{
			for(int row=1; row<=5; row++) {
			String baseUrl = "https://www.globalsqa.com/angularJs-protractor/BankingProject/#/manager/addCust";
			
		    //redirect to the Base URL
		    driver.get(baseUrl);
		    System.out.println("Navigated to URL: "+baseUrl);
		    test.log(LogStatus.PASS, "Navigate to URL", "Navigated to URL: "+baseUrl);
		    Thread.sleep(5000);
		    
		    String Fname = readdata.getData(0, row, 0);
		    String Lname = readdata.getData(0, row, 1);
		    String Pcode = readdata.getData(0, row, 2);
		    //InitializeTestObject
		    HomePage Homepage = new HomePage(driver, test);
		    Homepage.VerifyTitleofPage();
		    Homepage.FillCustomerDetails(Fname, Lname, Pcode);
		    Homepage.VerifyCustomerIsAdded();	
			}    
		}
		catch(Exception ex) {
            ex.toString();
            System.out.println("Test case got failed as: "+ex);
            test.log(LogStatus.FAIL, "Test case got fail", ex);
    		driver.close();
		}
	}

	@After
	public void TeardownTest() {
		//Close the driver
		driver.close();
		System.out.println("Test Completed successfully");
		test.log(LogStatus.PASS, "Test case", "Pass");
		report.endTest(test);
		report.flush();
	}
}
