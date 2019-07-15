package Calc.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.winium.WiniumDriver;

public class Calculator_Function {
	
	public  final String CALCULATOR_NAVIGATION = "Open Navigation";
	public  final String CALCULATOR_SCIENTIFIC = "Scientific Calculator";
	public  final String CALCULATOR_HISTORY = "There’s no history yet";
	public final String CALCULATOR_EDITMODE = "Scientific Calculator mode";
	public final String CALCULATOR_RESULT = "ResultTextBlock";
	public final String CALCULATOR_EQUAL = "Equals";
	public final String CALCULATOR_CLOSE = "Close Calculator";
	
	@FindBy(how=How.NAME,using = CALCULATOR_NAVIGATION )
	WebElement calculatorNavigation;
	
	@FindBy(how=How.NAME,using = CALCULATOR_SCIENTIFIC )
	WebElement calculatorScientific;
	
	@FindBy(how=How.NAME,using = CALCULATOR_HISTORY)
	WebElement calculatorHistory;
	
	
	@FindBy(how=How.NAME,using = CALCULATOR_EQUAL)
	WebElement calculatorEqual;
	
	@FindBy(how=How.NAME,using = CALCULATOR_EDITMODE)
	WebElement calculatorEditMode;
	
	@FindBy(how=How.NAME,using = CALCULATOR_CLOSE)
	WebElement calculatorClose;
	
	@FindBy(how=How.ID,using = CALCULATOR_RESULT)
	WebElement calculatorResulte;
	
	
	public void Calc_Navigation() {
		calculatorNavigation.click();
	}
	public void Calc_Scientific() {
		calculatorScientific.click();
	}
	public void Calc_History() {
		calculatorHistory.click();
	}
	
	public void Calc_Operator(WiniumDriver windowsDriverclient,String operator) {
		windowsDriverclient.findElement(By.name(operator)).click();
	}
	
	public void Calc_EditMode(String num) {
		calculatorEditMode.sendKeys(num);	
	}
	
	public void Calc_Equal() {
		calculatorEqual.click();
	}
	
	public void Calc_Close() {
		calculatorClose.click();
	}
	
	public String Calc_Result() {
		String val = calculatorResulte.getText();
		return val;
	}
}
