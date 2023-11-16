import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

class Driver {
	public static void main(String[] args) {
		ArrayList<String> strings = new ArrayList<String>();
		String originalFilePath = "testdata.manual.2009.06.14.csv";
		String covidFilePath = "Covid-19 Twitter Dataset (Apr-Jun 2020).csv";
		/*
		 * Find filepath File f = new File("testdata.manual.2009.06.14.csv");
		 * System.out.println(" wtf " + f.getAbsolutePath());
		 */

		try {
			FileReader fileReader = new FileReader(covidFilePath);
			BufferedReader buffRead = new BufferedReader(fileReader);

			String currentLine;
			while ((currentLine = buffRead.readLine()) != null) {
				//String[] split = currentLine.split(",");
			
				

				System.out.println(currentLine);
			}
		} catch (Exception exception) {
			System.out.println("error reading file");
		}

	}

	public static String[] convertLine(String string) {

		String[] result = string.split("\",\"");

		String replace = "";
		for (int i = 1; i < result[0].length(); i++) {
			replace += result[0].substring(i, i + 1);
		}
		result[0] = replace;

		replace = "";
		for (int i = 0; i < result[result.length - 1].length() - 1; i++) {
			replace += result[result.length - 1].substring(i, i + 1);
		}
		result[result.length - 1] = replace;

		// write the code below, and return the correct result
		return result;
	}
}