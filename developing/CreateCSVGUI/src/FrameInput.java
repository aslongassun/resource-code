import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;


public class FrameInput {

	private JFrame frame;
	private JTextField textInFolder;
	private StringBuilder msgBuilderStr;
	private JLabel lblFileOut;
	private JTextField textOutFileName;
	private JTextArea textArea;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInput window = new FrameInput();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FrameInput() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 380);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnCreateFile = new JButton("Create File");
		btnCreateFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				createCSV(textInFolder.getText(), textOutFileName.getText(), msgBuilderStr);
				textArea.setText(msgBuilderStr.toString());
			}
		});
		btnCreateFile.setBounds(56, 283, 133, 48);
		frame.getContentPane().add(btnCreateFile);
		
		textInFolder = new JTextField();
		textInFolder.setBounds(56, 11, 368, 20);
		textInFolder.setBorder(BorderFactory.createLineBorder(Color.decode("#C0C0C0")));
		frame.getContentPane().add(textInFolder);
		textInFolder.setColumns(10);
		
		JLabel lblFolder = new JLabel("Folder:");
		lblFolder.setBounds(10, 14, 49, 14);
		frame.getContentPane().add(lblFolder);
		
		lblFileOut = new JLabel("Outfile:");
		lblFileOut.setBounds(10, 43, 46, 14);
		frame.getContentPane().add(lblFileOut);
		
		textOutFileName = new JTextField();
		textOutFileName.setBounds(56, 40, 154, 20);
		textOutFileName.setBorder(BorderFactory.createLineBorder(Color.decode("#C0C0C0")));
		frame.getContentPane().add(textOutFileName);
		textOutFileName.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(221, 42, 203, 289);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBorder(BorderFactory.createLineBorder(Color.decode("#C0C0C0")));
		
		msgBuilderStr = new StringBuilder();
	}
	
	private void createCSV(String inFolder, String inOutFileName, StringBuilder inMessageBuilder) {
		inMessageBuilder.append("=======***LOG***=======\n");
		
		File fl = new File(inFolder);
		File[] files = fl.listFiles(new FileFilter() {
		    public boolean accept(File file) {
		        return file.isFile();
		    }
		});
		
		// Thu Muc File Input/Output
		inFolder = inFolder.replace("\\", "\\\\");
		
		// Create folder output
		String outFolder = inFolder + "\\out";
		File dirCreate = new File(outFolder);
		dirCreate.mkdir();
		
		// Check file output
		if(inOutFileName.isEmpty()){
			inOutFileName = "OutputFile.csv";
		}
		if(!inOutFileName.contains(".csv")){
			inOutFileName = inOutFileName + ".csv";
		}
		
		String csvFilenameOut = outFolder + "\\" + inOutFileName;
		
		Map<String, String> mapMonth = new HashMap<String, String>();
		mapMonth.put("January", "01");
		mapMonth.put("February", "02");
		mapMonth.put("March", "03");
		mapMonth.put("April", "04");
		mapMonth.put("May", "05");
		mapMonth.put("June", "06");
		mapMonth.put("July", "07");
		mapMonth.put("August", "08");
		mapMonth.put("September", "09");
		mapMonth.put("October", "10");
		mapMonth.put("November", "11");
		mapMonth.put("December", "12");
		
		Set<String> containsUID = new HashSet<String>();
		
		try{
			// Kiem tra file co chua ghi de Start:
			Path path = Paths.get(csvFilenameOut);
			Boolean isFileExists = Files.exists(path);
			if (isFileExists) {
				inMessageBuilder.append("Over write output file!" + "\n");
				inMessageBuilder.append(inOutFileName + "\n");
				inMessageBuilder.append("-----------------------");
				@SuppressWarnings("resource")
				CSVReader csvReaderWritedFile = new CSVReader(new InputStreamReader(new FileInputStream(csvFilenameOut), "UTF-8"));
				
				List<String[]> contentWritedFile = csvReaderWritedFile.readAll();
				
				// Xoa record header dau tien
				if(contentWritedFile.size() > 0){
					contentWritedFile.remove(0);
				}
				
				for (String[] row : contentWritedFile) {
					containsUID.add(row[0]);
				}
				// Kiem tra file co chua ghi de End:
			} else {
				inMessageBuilder.append("Create new output file" + "\n");
				inMessageBuilder.append(inOutFileName + "\n");
				inMessageBuilder.append("-----------------------");
			}
			
			
			// Phan ghi file
			CSVWriter writer = new CSVWriter(new OutputStreamWriter(new FileOutputStream(csvFilenameOut,true), "UTF-8"));
			
			List<String[]> data = new ArrayList<String[]>();
			
			CSVReader csvReader;
			
			if(!isFileExists){
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
						"isFromUpload",
						"currentCity"
						}
						);
			}
			
			for(File item : files){
				inMessageBuilder.append("\nFile: " + item.getName() + "\n");
				String csvFilenameIn = inFolder + "\\" + item.getName();
				csvReader = new CSVReader(new InputStreamReader(new FileInputStream(csvFilenameIn), "UTF-8"));
				
				List<String[]> content = csvReader.readAll();
				
				// Xoa record header dau tien
				if(content.size() > 0){
					content.remove(0);
				}
				
				inMessageBuilder.append("Record input: " + content.size() + "\n");
				
				Integer numberOfRecordNew = 0;
				for (String[] row : content) {
					if(row.length < 2){
						inMessageBuilder.append("Error: row.length < 2" + "\n");
						continue;
					}
					if(row[0] == "" || row[1] == ""){
						inMessageBuilder.append("Error: row_value_blank" + "\n");
						continue;
					}
					// Check neu ton tai keyUID roi thi ko them vao file nua
					if(containsUID.contains(row[0])){
						continue;
					}
					
					// Skip nhung record khong co nam sinh va ko co gioi tinh
					String[] birthday = row[5].split(" ");
					if(birthday.length < 3){
						continue;
					}
					
					String currSex = row[6].toLowerCase();
					if(!currSex.equals("male") && !currSex.equals("female")){
						continue;
					}
					
					data.add(new String[] {
								row[0],	 // Id_facebook
								row[0],	 // Id_facebook
								row[1],  // Name_facebook
								currSex, // Sex_facebook
								convertToDay(birthday[1]) + "/" + mapMonth.get(birthday[0]) + "/" + birthday[2],
								"https://graph.facebook.com/"+ row[0] +"/picture?type=large&width=250&height=250",
								"VN",
								String.valueOf(birthday[2]),
								String.valueOf(true),
								row[4]
								});
					numberOfRecordNew ++;
				}
				
				inMessageBuilder.append("Record write:" + numberOfRecordNew + "\n");
			}
			writer.writeAll(data);
			
			Integer recordWrited = Math.max(0, data.size() -1);
			
			inMessageBuilder.append("-----------------------\n");
			
			inMessageBuilder.append("Total new record writed: " + recordWrited + "\n");
			
			inMessageBuilder.append("\n=====***COMPLETE***=====\n\n");
			
			writer.close();
		} catch(Exception ex){
			inMessageBuilder.append("=====***EXCEPTION***=====\n\n");
		}
		
	}
	
	private String convertToDay(String inDate){
		inDate = inDate.replace(",", "");
		if(inDate.length() < 2){
			return "0"+inDate;
		} else {
			return inDate;
		}
	}
}
