import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;


class Driver {
    public static void main(String[] args) {
        ArrayList<Sentence> sentences = new ArrayList<>();
       // String covidFilePath = "OneDrive/PersonalOnedrive/OneDrive/cs1111/NLP-Rebuild/Covid-19_Twitter_Dataset_Apr-Jun_2020_modifiedWITHQUOTES (1).csv";
        String covidFilePath = "Covid-19_Twitter_Dataset_Apr-Jun_2020_modifiedWITHQUOTES.csv";
        try {
            FileReader fileReader = new FileReader(covidFilePath);
            BufferedReader buffRead = new BufferedReader(fileReader);

            String currentLine;
            while ((currentLine = buffRead.readLine()) != null) {
                sentences.add(convertLine(currentLine));
            }

            // Print the extracted sentences
            for (Sentence sentence : sentences) {
                
                System.out.println(sentence.toString());
                if (sentence.getAuthor() == "ERROR") {
                    break;
                }
            }


        } catch (Exception exception) {
            System.out.println("Error reading file: " + exception.getMessage());
        }
    }

	public static Sentence convertLine(String currentLine) {

        String[] split = currentLine.split("\"\"\",\"\"\"");

            // Extracting the three desired pieces
            if (split.length > 7) {
                String timestamp = split[7].replaceAll("[^a-zA-Z0-9 ]", "");
                String author = split[4].replaceAll("[^a-zA-Z0-9 ]", "");
                String line = split[2].replaceAll("[^A-Za-z0-9 ]", "");

                // Create a Sentence object and add it to the ArrayList
                Sentence sentence = new Sentence(timestamp, author, line);
                return sentence;
            }		

        Sentence sentence1 = new Sentence("ERROR", "ERROR", "ERROR");
        return sentence1;
	}
}