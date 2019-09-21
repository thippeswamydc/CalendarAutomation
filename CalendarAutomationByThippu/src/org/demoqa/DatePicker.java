package org.demoqa;
/*
 * 
 * author: thippu
 * email: thippeswamydcts@gmail.com
 * 
 * 
 * imp: donot forget to set the chrome driver path in the begining and add testng jar
 */
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.*;

public class DatePicker 
{
	WebDriver driver;
	String website="https://demoqa.com/datepicker/";
	WebDriverWait pageLoad=null;
	@FindBy(how=How.XPATH,using="//input[@id='datepicker']")
	WebElement pickerTxtbx;
	@FindBy(how=How.XPATH,using="//div[@id='ui-datepicker-div']//child::div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//child::a[@data-handler='prev']")
	WebElement prevBtn;
	@FindBy(how=How.XPATH,using="//div[@id='ui-datepicker-div']//child::div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//child::a[@data-handler='next']")
	WebElement nextBtn;
	@FindBy(how=How.XPATH,using="//div[@id='ui-datepicker-div']")
	WebElement pickerDiv;
	@FindBy(how=How.XPATH,using="//div[@id='ui-datepicker-div']//child::div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//descendant::span[@class='ui-datepicker-month']")
	WebElement cmonthLbl;
	@FindBy(how=How.XPATH,using="//div[@id='ui-datepicker-div']//child::div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//descendant::span[@class='ui-datepicker-year']")
	WebElement cyearLbl;
	int eYear;
	int eMonth;
	int eDay;
	int cYear;
	int cMonth;
	String dayXpath;
	By dayClicker;
	
	public DatePicker(WebDriver driver)
	{
		this.driver=driver;
		pageLoad=new WebDriverWait(driver,20);
		driver.navigate().to(website);
		PageFactory.initElements(this.driver, this);
	}
	
	public void ChooseDate(int eDay,int eMonth,int eYear)
	{
		initPicker(eDay, eMonth, eYear);
		chooseYear(eYear);
		chooseMonth(eMonth);
		driver.findElement(dayClicker).click();
	}

	/**
	 * @param eYear
	 */
	private void chooseYear(int eYear)
	{
		int tempCYear=cYear;
		while(tempCYear!=eYear)
		{
			if(tempCYear>eYear)
			{
				prevBtn.click();
				--tempCYear;
			}
			else 
			{
				nextBtn.click();
				++tempCYear;
			}
			tempCYear=Integer.parseInt(cyearLbl.getText());
			//System.out.println("Iteration year of calendar is"+tempCYear);
		}
	}

	private void chooseMonth(int eMonth)
	{
		
		cMonth=MonthToNumber(cmonthLbl.getText());
		int currMonth=cMonth;
		while(currMonth!=eMonth)
		{
			if(currMonth>eMonth)
			{
				prevBtn.click();
				--currMonth;
			}
			else
			{
				nextBtn.click();
				++currMonth;
			}
		//	System.out.println("iteration month"+currMonth);
		}
	}
	/**
	 * @param eDay
	 * @param eMonth
	 * @param eYear
	 */
	private void initPicker(int eDay, int eMonth, int eYear)
	{
		this.eYear=eYear;
		this.eMonth=eMonth;
		this.eDay=eDay;
		dayXpath="//div[@id='ui-datepicker-div']"+"//descendant::a[contains(text(),"+eDay+")]";
		dayClicker= By.xpath(dayXpath);
		pageLoad.until(ExpectedConditions.visibilityOf(pickerTxtbx));
		pickerTxtbx.click();
		//driver.findElement(dayClicker).click();
		pageLoad.until(ExpectedConditions.visibilityOf(pickerDiv));
		pageLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ui-datepicker-div']//child::div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//descendant::span[@class='ui-datepicker-year']")));
		//System.out.println("year is"+cyearLbl.getText());
		cYear=Integer.parseInt(cyearLbl.getText());
		//cMonth=Integer.parseInt(cmonthLbl.getText());
		pageLoad.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='ui-datepicker-div']//child::div[@class='ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all']//descendant::span[@class='ui-datepicker-month']")));
		pageLoad.until(ExpectedConditions.visibilityOf(prevBtn));
		pageLoad.until(ExpectedConditions.visibilityOf(nextBtn));
		//System.out.println("current month and year is "+cMonth+ " "+cYear);
		
	}
	
	private int  MonthToNumber(String Month)
	{
		int month_num=0;
		if(Month==null)
			return month_num=0;
		switch(Month)
		{
		case "January": month_num=1;
		break;
		case "Febrary":month_num= 2;
		break;
		case "March": month_num= 3;
		break;
		case "April": month_num=4;
		break;
		case "May": month_num=5;
		break;
		case "June": month_num= 6;
		break;
		case "July": month_num= 7;
		break;
		case "August":month_num= 8;
		break;
		case "September":month_num= 9;
		break;
		case "October":month_num=10;
		break;
		case "November": month_num=11;
		break;
		case "December": month_num=12;
		break;
		default: month_num=-1;
		break;
		}
		return month_num;
	}
	
	
	
}
