package DataProviders;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import Utils.ExcelUtils;

public class DataProviders 
{
	@DataProvider(name = "register")
    public Object[][] getLoginData() {
        String path = "src\\test\\resources\\testData\\Registration.xlsx";
        List<Map<String, String>> list = ExcelUtils.getData(path, "register");

        Object[][] data = new Object[list.size()][1]; // one Map per test case
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i);  // put each Map into Object[][]
        }
        return data;
    }
	
	
	@DataProvider(name = "transferData")
    public Object[][] getTransferData() 
	{
        return new Object[][] {
            // username, password, accountType, amount
            {"SampleTestUser7", "Password@1231", "SAVINGS", "200"}
        };
    }
	
	
    @DataProvider(name = "loanRequestData")
    public Object[][] getLoanRequestData() {
        return new Object[][]{
            // username, password, loanAmount, downPayment, accountIndex
            {"SampleTestUser7", "Password@1231", "200", "20", 0}
        };
    }
    
    @DataProvider(name = "updateProfileData")
    public Object[][] getUpdateProfileData() {
        return new Object[][]{
            // username, password, phone
           
            {"TestUser7", "Password@123", "8765432876"}
            
        };
    }

	
}