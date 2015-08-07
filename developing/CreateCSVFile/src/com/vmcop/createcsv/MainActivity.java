package com.vmcop.createcsv;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

public class MainActivity {
	
	public static void main(String[] args) {
		
		// Nam Sinh
		Integer bornYear = 1991;
		// Gioi Tinh
		String userSex = "female";
		// Ten File Input
		String fileNameIn = "FB_File_Woman_1991.csv";
		String fileNameOut = "FB_File_Woman_1991_out.csv";
		
		// Thu Muc File Input/Output
		String urlInFolder = "C:\\androidapp\\resource-code\\developing\\upload_findlover\\createcsvfile";
		
		String csvFilenameIn = urlInFolder + "\\" + fileNameIn;
		String csvFilenameOut = urlInFolder + "\\" + fileNameOut;
		
		CSVReader csvReader;
		try {
			// Phan doc file
			csvReader = new CSVReader(new FileReader(csvFilenameIn));
			List<String[]> content = csvReader.readAll();
			
			// Xoa record header dau tien
			if(content.size() > 0){
				content.remove(0);
			}
			
			// Phan ghi file
			CSVWriter writer = new CSVWriter(new FileWriter(csvFilenameOut));
			 
			List<String[]> data = new ArrayList<String[]>();
			
			data.add(
					new String[] {
					"key",
					"fuid",
					"userName",
					"userSex",
					"birthday",
					"urlImageProfile",
					"locale",
					"bornYear",
					"isFromUpload"}
					);
			
			for (String[] row : content) {
				if(row.length < 2){
					System.out.println("row_length_error");
					continue;
				}
				if(row[0] == "" || row[1] == ""){
					System.out.println("row_value_error");
					continue;
				}
				data.add(new String[] {
							row[0],	 // Id_facebook
							row[0],	 // Id_facebook
							row[1],  // Name_facebook
							userSex, // Sex_facebook
							"01/01/" + bornYear,
							"https://graph.facebook.com/"+ row[0] +"/picture?type=large&width=250&height=250",
							"VN",
							String.valueOf(bornYear),
							String.valueOf(true)
							});
			    
			}
			
			
			writer.writeAll(data);
			 
			writer.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
