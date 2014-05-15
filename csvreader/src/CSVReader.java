

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

	public static void main(String[] args) {

		CSVReader obj = new CSVReader();
		obj.run();

	}

	public void run() {

		String csvFile = "D:\\projects\\csvws\\csvreader\\src\\Share.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));
			List<Company> companyList = new ArrayList<Company>();
			int i = 0;
			String[] companyNames = null;
			while ((line = br.readLine()) != null) {
				Integer maxPrice=new Integer(0);
				
				Company company=new Company();
				
				if(i == 0)
				{
					// use comma as separator
					companyNames=line.split(cvsSplitBy);
				}
				
				if (i >= 1) {
					// use comma as separator
					String[] record = line.split(cvsSplitBy);
					for (int j = 2; j < record.length; j++) {
						if(record[j] != null)
						{
							Integer sharePrice= Integer.parseInt(record[j]);
							if(sharePrice >= maxPrice)
							{
								maxPrice=sharePrice;
								company.setCompanyName(companyNames[j]);
								company.setMonth(record[1]);
								company.setYear(record[0]);
								company.setPrice(record[j]);
							}
							
						}
					}
					companyList.add(company);
				}
				i++;

			}
			
			for(Company company:companyList)
			{
				System.out.println(company.getCompanyName() + " have highest share price "
						+ company.getPrice() + " in " + company.getMonth()+"/"+company.getYear());
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

		System.out.println("Done");
	}

}
