package com.excel;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.core.tools.picocli.CommandLine.Help.Column;
import org.apache.poi.ss.formula.functions.Columns;
import org.apache.poi.ss.usermodel.AutoFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFAutoFilter;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONObject;
import org.json.XML;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.CTAutoFilter;
//import com.spire.xls.Worksheet;
//import com.spire.xls.collections.AutoFiltersCollection;


import com.spire.xls.Worksheet;
import com.spire.xls.collections.AutoFiltersCollection;

import io.restassured.path.json.JsonPath;

import com.spire.xls.*;




public class Time_ExcelUpdate {
	private static String currentDate;
	private static String currentTime;
//	private static String parameter;
	public static Date date = new Date();
	
//	public static String getParameter() {
//		return parameter;
//	}

//	public static void setParameter(String Parameter) {
//		parameter = Parameter;
//	}

	
	
	public static String  PresentDate() {
	DateFormat dateFormat  = new SimpleDateFormat("dd/MM/yyyy");
		
		currentDate = dateFormat.format(date).toString().replaceFirst(" ", "_").replaceAll("/", "_").replaceAll(":",
				"_");
		return currentDate;
}	
	public static String  CurrentTime() {
		DateFormat dateFormat  = new SimpleDateFormat("HH:mm:ss");
			
			currentTime = dateFormat.format(date).toString().replaceFirst(" ", "_").replaceAll("/", "_").replaceAll(":",
					"_");
			return currentTime;
	}	
	
	public static   String time()  {
		SimpleDateFormat simple= new SimpleDateFormat("HH:mm:ss");
		String dateString = simple.format(date).toString().replaceAll(":", "_");;
		return dateString;
	}	
	
	//static String xlpath1 = System.getProperty("user.dir") + "/Excel_Reports"+"/"+ PresentDate() + "/"+ time()+ ".xlsx";
	public static String xlpath1 = System.getProperty("user.dir") + "\\Excel_Reports\\"+ PresentDate() + "\\"+time()+ ".xlsx";
	
	 // static String sheet2 = CurrentTime();
	//static String sheet3 = "processing result";
	public static String ModuleName = "NA";
	static int row = (getRowCount1() + 1);
	static int counter = 0;
	public static int passCounter = 0;
	public static int failCounter = 0;
	public static int warningCounter = 0;
	
	public static void creatExcel1() {
		try {
			//File dir = new File(System.getProperty("user.dir") + "\\Excel_Reports\\"+PresentDate());
			File dir = new File(System.getProperty("user.dir") + "/Excel_Reports" + "/" +PresentDate());
			System.out.println("===creating Excel");
			if (!dir.isDirectory()) {
				dir.mkdir();
			}
			File file = new File(xlpath1);
			if (!file.exists()) {
				XSSFWorkbook workbook = new XSSFWorkbook();
				workbook.createSheet(CurrentTime());
				//workbook.createSheet(sheet3);
				
				FileOutputStream fos = new FileOutputStream(new File(xlpath1));
				workbook.write(fos);
				workbook.close();
				System.out.println("file not exist");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	public static void TestCaseSummaryNode1(String Summary) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			// Update the value of cell
			if (cell == null) {
				cell = xrow.createCell(6);
				cell.setCellValue(Summary);
				System.out.println(Summary);
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
		}
	}
	public static void timeStampNode(String time) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
		
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
			 XSSFCellStyle style = myExcelBook.createCellStyle();
			XSSFFont font= myExcelBook.createFont();
//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			// Update the value of cell
			if (cell == null) {
				// style.setFillBackgroundColor(IndexedColors.BRIGHT_GREEN.getIndex()); 
				//  style.setFillPattern(FillPatternType.LEAST_DOTS); 
				cell = xrow.createCell(3);
				font.setBold(true);
				cell.setCellValue(time);
				style.setFont(font);
				cell.setCellStyle(style);
				
				
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
		}
	}

	
	public static void TestCaseIDNode(String tc) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			// Update the value of cell
			if (cell == null) {
				cell = xrow.createCell(0);
				cell.setCellValue(tc);
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
		}
	}
//	public static
//	public static void SlNoNode(String no) {
//		try {
//			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
//			FileOutputStream output = new FileOutputStream(xlpath1);
//			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
////			myExcelSheet
//			XSSFRow xrow = myExcelSheet.getRow(row);
//			if (xrow == null) {
//				xrow = myExcelSheet.createRow(row);
//			}
//			Cell cell = null;
//			// Update the value of cell
//			if (cell == null) {
//				cell = xrow.createCell(0);
//				cell.setCellValue(no);
//			}
//			myExcelBook.write(output);
//			myExcelBook.close();
//		} catch (Exception e) {
//		}
//	}
	
	public static void ModuleNode(String name) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			// Update the value of cell
			if (cell == null) {
				cell = xrow.createCell(5);
				cell.setCellValue(name);
//				System.out.println("Module name--"+name);
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
		}
	}
	
	
	public static void Parameters(String name) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			// Update the value of cell
			if (cell == null) {
				cell = xrow.createCell(8);
				cell.setCellValue(name);
//				System.out.println("Module name--"+name);
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
		}
	}
	
	public static void values(String name) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			// Update the value of cell
			if (cell == null) {
				cell = xrow.createCell(9);
				cell.setCellValue(name);
//				System.out.println("Module name--"+name);
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
		}
	}
	public static void ExpectedProcessingTime(String name) {
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			// Update the value of cell
			if (cell == null) {
				cell = xrow.createCell(4);
				cell.setCellValue(name);
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
		}
	}
	
	public static void writeData1(String Validation, String result, String error) {
		try {
			
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
	
			FileOutputStream output = new FileOutputStream(xlpath1);
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
			
			 XSSFCellStyle style = myExcelBook.createCellStyle();
				XSSFFont font= myExcelBook.createFont();
			//			myExcelSheet
			XSSFRow xrow = myExcelSheet.getRow(row);
			if (xrow == null) {
				xrow = myExcelSheet.createRow(row);
			}
			Cell cell = null;
			if (counter == 0) {
				xrow = myExcelSheet.getRow(0);
				if (xrow == null) {
					xrow = myExcelSheet.createRow(0);
				}
				if (cell == null) {
					style.setFillBackgroundColor(IndexedColors.LIGHT_BLUE.getIndex());
					  style.setFillPattern(FillPatternType.LEAST_DOTS); 
					font.setFontName("Arial");
					font.setFontHeight(12);
					font.getBold();
					
					cell = xrow.createCell(0);
					cell.setCellValue("TC_ID");
					cell.setCellStyle(style);
					style.setFont(font);
					
//					cell = xrow.createCell(0);
//					cell.setCellValue("Sl.no");
					cell = xrow.createCell(1);
					cell.setCellValue("Time");
					cell.setCellStyle(style);
					style.setFont(font);
					
//					cell = xrow.createCell(2);
//					cell.setCellValue("TC_ID");
					cell = xrow.createCell(2);
					cell.setCellValue("Date");
					cell.setCellStyle(style);
					style.setFont(font);
					
					cell = xrow.createCell(3);
					cell.setCellValue("Actual_Processing_Time");
					cell.setCellStyle(style);
					style.setFont(font);
					
					cell = xrow.createCell(4);
					cell.setCellValue("Expected_Processing_Time");
					cell.setCellStyle(style);
					style.setFont(font);
					
					cell = xrow.createCell(5);
					cell.setCellValue("Module");
					cell.setCellStyle(style);
					style.setFont(font);
					
					cell = xrow.createCell(6);
					cell.setCellValue("Test_case_Summary");
					cell.setCellStyle(style);
					style.setFont(font);
					
					cell = xrow.createCell(7);
					cell.setCellValue("TC_result");
					cell.setCellStyle(style);
					style.setFont(font);
					
					cell = xrow.createCell(8);
					cell.setCellValue("Parameters");
					cell.setCellStyle(style);
					style.setFont(font);
					
					cell = xrow.createCell(9);
					cell.setCellValue("Values");
					cell.setCellStyle(style);
					style.setFont(font);
//					
//					cell = xrow.createCell(10);
//					cell.setCellValue("Remarks");
//					cell.setCellStyle(style);
//					style.setFont(font);
					counter++;
				}
			}
			// Update the value of cell
			if (result.equals("Pass")) {
				if (cell == null) {
//					cell = xrow.createCell(0);
//					cell.setCellValue(SlNoNode(no));
					
					cell = xrow.createCell(1);
					cell.setCellValue(time());
					cell = xrow.createCell(2);
					cell.setCellValue(currentDate);
					cell = xrow.createCell(7);
					cell.setCellValue(result);
					
					
					
				
					row++;
					passCounter++;
				}
			} else if (result.equals("Fail")) {
				if (cell == null) {
					style.setFillBackgroundColor(IndexedColors.RED.getIndex());
					  style.setFillPattern(FillPatternType.LEAST_DOTS); 
					cell = xrow.createCell(1);
					cell.setCellValue(time());
					cell = xrow.createCell(2);
					cell.setCellValue(currentDate);
					cell = xrow.createCell(7);
					cell.setCellValue(result);
					cell.setCellStyle(style);
					style.setFont(font);
					cell = xrow.createCell(10);
					cell.setCellValue(error);
					row++;
					failCounter++;
				}
				
				
				
			} else if (result.equals("Warning")) {
				if (cell == null) {
					cell = xrow.createCell(1);
					cell.setCellValue(time());
					cell = xrow.createCell(9);
					cell.setCellValue(result);
					cell = xrow.createCell(9);
					cell.setCellValue(error);

					row++;
					warningCounter++;
				}
			}
			myExcelBook.write(output);
			myExcelBook.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	

	@SuppressWarnings("resource")
	public int getMatchRow1(String matchData) {
		String data = "";
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
			for (int i = 0; i < getRowCount1(); i++) {
				data = myExcelSheet.getRow(i).getCell(0).toString();
				if (data.equals(matchData)) {
					return i;
				}
			}
			myExcelBook.close();
		} catch (Exception e) {
		}
		return 0;
	}

	// Generic method to return the column values in the sheet.
	@SuppressWarnings("resource")
	public static String getCellValue1(int row, int col) {
		String data = "";
		try {
			XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
			
			XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
			data = myExcelSheet.getRow(row).getCell(col).toString();
			myExcelBook.close();
		} catch (Exception e) {
		}
		return data;
	}

	// Generic method to return the number of rows in the sheet.
	public static int getRowCount1() {
		int rc = 0;
		try {
			FileInputStream fis = new FileInputStream(xlpath1);
			
			Workbook wb = WorkbookFactory.create(fis);
			Sheet s = wb.getSheet(CurrentTime());
			rc = s.getLastRowNum();
			fis.close();
			wb.close();
		} catch (Exception e) {
		}
		return rc;
	}

	@SuppressWarnings("resource")
	public static String Iterator1(String toFind) throws IOException {
//		String toFind = ID;
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
	
		
		XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
		for (Row row : myExcelSheet) {
			for (Cell cell : row) {
				CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
				DataFormatter formatter = new DataFormatter();
				String text = formatter.formatCellValue(cell);
				if (toFind.equals(text)) {
					return cellRef.formatAsString().replaceAll("\\D+", "");
				} else if (text.contains(toFind)) {
					System.out.println("Text found as part of " + cellRef.formatAsString());
				}
			}
		}
		return "";
	}

	
//	public static void updateResult1() {
//		if (ExtentReporter.mailBodyPart.size() > 0) {
//			for (int i = 0; i < ExtentReporter.mailBodyPart.size(); i++) {
//				String result[] = ExtentReporter.mailBodyPart.get(i).toString().split(",");
////				System.out.println(result[0]+result[1]+result[2]);
//				try {
//					XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
//					FileOutputStream output = new FileOutputStream(xlpath1);
//					XSSFSheet myExcelSheet = myExcelBook.getSheet(sheet3);
//					// Update the value of cell
//					if (i == 0) {
//						XSSFRow xrow = myExcelSheet.getRow(i);
//						if (xrow == null) {
//							xrow = myExcelSheet.createRow(i);
//						}
//
//						Cell cell = null;
//						if (cell == null) {
//							cell = xrow.createCell(1);
//							cell.setCellValue("Module Name, APP verison - 20.21106.3");
//							cell = xrow.createCell(2);
//							cell.setCellValue("Module Result");
//						}
//					}
////						myExcelSheet
//					XSSFRow xrow = myExcelSheet.getRow((i + 1));
//					if (xrow == null) {
//						xrow = myExcelSheet.createRow((i + 1));
//					}
//
//					Cell cell = null;
//					if (cell == null) {
//						cell = xrow.createCell(1);
//						cell.setCellValue(result[0]);
//						cell = xrow.createCell(2);
//						if (failCounter == 0) {
//							cell.setCellValue("Pass");
//						} else {
//							cell.setCellValue("Fail");
//						}
//					}
//					myExcelBook.write(output);
//					myExcelBook.close();
//				} catch (Exception e) {
//				}
//			}
//		}
//	}
	
	public static void AutoFitColumn() {
		  
		 com.spire.xls.Workbook workbook = new com.spire.xls.Workbook();

	        //Load a sample Excel document
	        workbook.loadFromFile(xlpath1);
	        System.out.println(xlpath1);

	        //Get the first worksheet
	        Worksheet worksheet = workbook.getWorksheets().get(0);

	        //AutoFit column width and row height
	        worksheet.getAllocatedRange().autoFitColumns();
	        worksheet.getAllocatedRange().autoFitRows();

	        //Save the document
	        workbook.saveToFile(xlpath1);
	    
//    
}
	
	
	
	
public void FilterColumn(String column) throws FileNotFoundException, IOException {
		
		XSSFWorkbook myExcelBook = new XSSFWorkbook(new FileInputStream(xlpath1));
		
		FileOutputStream output = new FileOutputStream(xlpath1);
		XSSFSheet myExcelSheet = myExcelBook.getSheet(CurrentTime());
		 
//		myExcelSheet.setAutoFilter(CellRangeAddress())
//		 AutoFilter filter = myExcelSheet.setAutoFilter(Col);
//	     filter.applyFilter(0, FilterOperator.GreaterThanOrEqual", "0.2");
//	       filter.applyFilter(1, FilterOperator.LessThanOrEqual"", "0.5");
//		
		
	}




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
public static InetAddress localhost;
public static String ipAdress = "";
//public static String charleslogsName = "";
public static String charlesName = "";
public static String filePath = "";

public static void openCharles() {
	try {
		// print a message
		System.out.println("Executing notepad.exe");
		// create a process and execute notepad.exe
		//		         Process process = Runtime.getRuntime().exec("notepad.exe");
		Process process = Runtime.getRuntime().exec("\"C:\\Program Files\\Charles\\Charles.exe\"");
		localhost = InetAddress.getLocalHost();
		ipAdress = localhost.getHostAddress().trim();
		System.out.println("System IP Address : " + (localhost.getHostAddress()).trim());
		Thread.sleep(20000);

		Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/session/clear");
		Thread.sleep(2000);
		Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/recording/start");


	} catch (Exception ex) {
		ex.printStackTrace();
	}
}
public static String charleslogsName = "";

static String filePathxml = System.getProperty("user.dir") + "/Excel_Reports/" + charlesName;
public static void saveandcloseCharles(String fileName) {
	try {
		DateFormat date = new SimpleDateFormat("ddmmyyHHMMss");
		Date date2 = new Date();
		String date1 = date.format(date2);
		
		charlesName = fileName + date1 + ".xml";
		System.out.println(charlesName);
		charleslogsName = fileName + date1 + ".chls";
		System.out.println(charleslogsName);
		 String filePathxml1 = System.getProperty("user.dir") + "\\Excel_Reports\\" + charlesName;
		 System.out.println(filePathxml1);
//		String filePathlogs = System.getProperty("user.dir") + "/Excel_Reports/" + charleslogsName;
		
		ipAdress = localhost.getHostAddress().trim();
		Thread.sleep(10000);
		Runtime.getRuntime().exec("curl -o " + filePathxml1 + " -x http://" + ipAdress + ":8888 http://control.charles/session/export-xml");
		
		Thread.sleep(5000);
//		Runtime.getRuntime().exec("curl -o " + filePathlogs + " -x http://" + ipAdress + ":8888 http://control.charles/session/download");
		Thread.sleep(10000);
		Runtime.getRuntime().exec("curl -v -x http://" + ipAdress + ":8888 http://control.charles/quit");
		Thread.sleep(2000);
		System.out.println("Close Charles");
	} catch (Exception e) {
		
	}
}

public static File Newest() {
	File fl = new File("C:/New/CharlesAndroidHipiNeo/Charles");
	File choice = null;
	if (fl.listFiles().length>0) {
		File[] files = fl.listFiles(new FileFilter() {
			public boolean accept(File file) {
				return file.isFile();
			}
		});
		long lastMod = Long.MIN_VALUE;

		for (File file : files) {
			if (file.lastModified() > lastMod) {
				choice = file;
				lastMod = file.lastModified();
			}
		}
		System.out.println(choice);
	}
	return choice;
}   

public static  void Filereader() throws IOException
{
	File file = Newest();
	BufferedReader br = new BufferedReader(new FileReader(file));
	JSONObject json=XML.toJSONObject(br); 
	String nn = json.toString();
	System.out.println(nn);
	List<Object> jsonList= JsonPath.from(nn).getList("charles-session.transaction");
//	System.out.println(jsonList);
	System.out.println(jsonList.size());
	Map<String, String> map = new HashMap<String, String>();
	for(int i=0;i<jsonList.size();i++)
	{
		String jsonPathNew = "charles-session.transaction[" + i + "].request.body[1]";
		System.out.println("************" +jsonPathNew + "***********");
		String str = JsonPath.from(nn).get(jsonPathNew);
		str = str.substring(1, str.length() - 1);
		String[] keyValuePairs = str.split(",");
		String[] entry = null;
		for (int j = 0; j < keyValuePairs.length; j++)
		{
			entry = keyValuePairs[j].split(":");
			map.put(entry[0].trim(), entry[1].trim());
		}
		Set set = map.entrySet();
		Iterator iew =set.iterator();
		
		while(iew.hasNext()) {
			Map.Entry me = (Map.Entry)iew.next();
			if(me.getKey().toString().contains("event") | me.getKey().toString().contains("correlation_id")) {
				System.out.println(me.getKey() + ": " + me.getValue());
//				Events.add((String) me.getValue());
			}
//								 System.out.print(me.getKey() + ": ");
//								 System.out.print(me.getValue());
			
		}
	}

}

//public static void main(String[] arg) throws IOException, InterruptedException{
//
//	openCharles();
////		LaunchGudmedicproApp();
//	Thread.sleep(5000);
//	saveandcloseCharles(charlesName);
////	quit();
////	ListOfEvents1();
//	Filereader();
//
//}
public static void MappingCharles() throws Exception 
{
	 List<String> ListofEvents= new ArrayList<String>();
	 List<String> Events=new ArrayList<String>();
	 
	 
	 File file = Newest();

	 BufferedReader br = new BufferedReader(new FileReader(file));
	 JSONObject json=XML.toJSONObject(br); 
	 String nn = json.toString();
	 System.out.println(nn);
	 List<Object> jsonList= JsonPath.from(nn).getList("charles-session.transaction");
	 //			System.out.println(jsonList);
	 System.out.println(jsonList.size());
	 Map<String, String> map = new HashMap<String, String>();

	 for(int i=0;i<jsonList.size();i++)
	 {
		 String jsonPathNew = "charles-session.transaction[" + i + "].request.body[1]";
		 //				System.out.println("************" +jsonPathNew + "***********");
		 String str = JsonPath.from(nn).get(jsonPathNew);
		 str = str.substring(1, str.length() - 1);
		 String[] keyValuePairs = str.split(",");
		 String[] entry = null;
		 for (int j = 0; j < keyValuePairs.length; j++)
		 {
			 entry = keyValuePairs[j].split(":");
			 map.put(entry[0].trim(), entry[1].trim());
		 }
		 Set set = map.entrySet();
		 Iterator iew =set.iterator();

		 while(iew.hasNext()) {
			 Map.Entry me = (Map.Entry)iew.next();
			 if(me.getKey().toString().contains("assetId") | me.getKey().toString().contains("correlation_id") | me.getKey().toString().contains("profile_id") | me.getKey().toString().contains("event") | me.getKey().toString().contains("source") | me.getKey().toString().contains("page") | me.getKey().toString().contains("timeSpent") | me.getKey().toString().contains("duration") | me.getKey().toString().contains("updateParam") | me.getKey().toString().contains("updateValue") | me.getKey().toString().contains("profile_id") | me.getKey().toString().contains("user_id") | me.getKey().toString().contains("explain") | me.getKey().toString().contains("tab")) {
				 System.out.println(me.getKey() + ": " + me.getValue()); 
				 String events = me.getValue().toString().replace("\"", "");
				 Events.add(events);
			 }

//			 					 System.out.print(me.getKey() + ": ");
//			 					 System.out.print(me.getValue());
		 }
	 }



	 HashSet<String> removeDuplicateEvent=new LinkedHashSet<String>();
	 removeDuplicateEvent.addAll(Events);
	 Events.clear();
	 Events.addAll(removeDuplicateEvent);
	 System.out.println(Events.size());

	 //List Of Events 
	 
//	 ListofEvents.add("launch"); 	
	 ListofEvents.add("user_video_start");
	 ListofEvents.add("completed");
	 ListofEvents.add("view");
	 ListofEvents.add("like");
	 ListofEvents.add("unlike");
	
	 ListofEvents.add("user_video_end");
	 ListofEvents.add("shop_button_click");
	 ListofEvents.add("shoppable_product_click");
	 ListofEvents.add("share");
	 ListofEvents.add("follow");
	 ListofEvents.add("comment");
	 ListofEvents.add("favorite");
	 ListofEvents.add("unfavorite");
	 ListofEvents.add("notinterested");
	 ListofEvents.add("download");
	 ListofEvents.add("skip");
	 ListofEvents.add("no decision");	
	 ListofEvents.add("unfollow");	
	 ListofEvents.add("videoreported");
	 ListofEvents.add("search_event");
	 ListofEvents.add("suggestion_result_click_event");
	 ListofEvents.add("shoppable_product_impression");

	 
	 
	 System.out.println("Start");
	 for(int i=0;i<ListofEvents.size();i++)
	 {
		 if(Events.contains(ListofEvents.get(i))) {
			 System.out.println(ListofEvents.get(i)+" Event is triggered");
////			 extent.HeaderChildNode(ListofEvents.get(i)+" Event");
//			 Time_ExcelUpdate.TestCaseIDNode("TC0"+i+"");
//			 Time_ExcelUpdate.ModuleNode(ListofEvents.get(i)+" Event");
////			 extent.extentLoggerPass("", Events.get(i)+" Event");
//			 Time_ExcelUpdate.TestCaseSummaryNode1(ListofEvents.get(i)+" Event is triggered");
////			 extent.extentLoggerPass("", ListofEvents.get(i)+" Event is triggered");
		 }else {
			 System.out.println(ListofEvents.get(i)+" Event is not triggered");
////			 extent.HeaderChildNode(ListofEvents.get(i)+" Event");
//			 Time_ExcelUpdate.TestCaseIDNode("TC0"+i+"");
//			 Time_ExcelUpdate.ModuleNode(ListofEvents.get(i)+" Event");
//			 //					 extent.extentLoggerPass("", Events.get(i)+" Event");
//			 Time_ExcelUpdate.TestCaseSummaryNode1(ListofEvents.get(i)+" Event is not triggered");
////			 extent.extentLoggerFail("", ListofEvents.get(i)+" Event is not triggered");
		 }
	 }
	 
}
	

	

	public static void main(String[] args) throws Exception {
//		creatExcel1();
//		System.out.println(xlpath1);
		MappingCharles();

	}

}
