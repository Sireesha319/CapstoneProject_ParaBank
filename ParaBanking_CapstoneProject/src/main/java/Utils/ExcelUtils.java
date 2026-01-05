package Utils;
import java.io.FileInputStream;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

    /**
     * Reads Excel data and returns it as a List of Maps.
     * Each row = one Map<String, String>
     * Each column header = key, Cell value = value
     */
    public static List<Map<String, String>> getData(String filePath, String sheetName) {
        
        // Stores all rows (each row is represented as a Map)
        List<Map<String, String>> data = new ArrayList<>();
        
        // DataFormatter ensures cell values (numbers, dates, text) are converted to string safely
        DataFormatter formatter = new DataFormatter();

        try (
            // Open Excel file input stream
            FileInputStream fis = new FileInputStream(filePath);
            
            // Create workbook instance for .xlsx file
            Workbook workbook = new XSSFWorkbook(fis)
        ) {

            // Get the required sheet
            Sheet sheet = workbook.getSheet(sheetName);
            
            // Read the first row (header row) to use as keys
            Row headerRow = sheet.getRow(0);

            // Loop through all rows starting from row 1 (row 0 = header)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                
                // Skip if row is empty
                if (row == null) continue;

                // Create a Map for current row
                Map<String, String> rowData = new HashMap<>();

                // Loop through all columns in the header row
                for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                    
                    // Get header name (key)
                    String key = formatter.formatCellValue(headerRow.getCell(j)).trim();
                    
                    // Get cell value for this row under the same column
                    String value = formatter.formatCellValue(row.getCell(j)).trim();
                    
                    // Put key-value pair into map
                    rowData.put(key, value);
                }

                // Add this row's map to the list
                data.add(rowData);
            }

        } catch (Exception e) {
            // If anything goes wrong, throw runtime exception with cause
            throw new RuntimeException("Failed to read Excel data", e);
        }
        
        // Return final list of maps (all rows)
        return data;
    }
}