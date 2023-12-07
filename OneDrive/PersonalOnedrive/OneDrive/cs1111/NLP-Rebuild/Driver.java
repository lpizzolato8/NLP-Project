import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

class Sentence {
    private String timestamp;
    private String author;
    private String line;

    public Sentence(String timestamp, String author, String line) {
        this.timestamp = timestamp;
        this.author = author;
        this.line = line;
    }

    // Getters and setters for timestamp, author, and line
    public String gettimestamp() {
        return timestamp;
    }

    public void settimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getauthor() {
        return author;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public String getline() {
        return line;
    }

    public void setline(String line) {
        this.line = line;
    }

    public ArrayList<String> splitSentence() {
        ArrayList<String> wordsList = new ArrayList<>();
        String[] words = this.timestamp.split("\\s");

        for (String word : words) {
            wordsList.add(word);
        }
        return wordsList;
    }

    @Override
    public String toString() {
        return "Timestamp: " + line + " Author: " + author + " Line: " + timestamp;
    }
}

class Driver {
    public static void main(String[] args) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        ArrayList<Sentence> words = new ArrayList<>();
        String covidFilePath = "Covid-19_Twitter_Dataset_Apr-Jun_2020_fixedSpacing.csv";
        // String covidFilePath = "Part2Tester.java";
        try {
            FileReader fileReader = new FileReader(covidFilePath);
            BufferedReader buffRead = new BufferedReader(fileReader);

            String currentLine;
            while ((currentLine = buffRead.readLine()) != null) {
                String[] split = currentLine.split("\",\"");

                // Extracting the three desired pieces
                if (split.length > 7) {
                    String timestamp = split[7].replaceAll("\"", "").trim();
                    String author = split[4].replaceAll("\"", "").trim();
                    String line = split[2].replaceAll("\"", "").trim();

                    // Create a Sentence object and add it to the ArrayList
                    Sentence sentence = new Sentence(timestamp, author, line);
                    sentences.add(sentence);

                }
            }

            for (Sentence sentence : sentences) {
                System.out.println(sentence.toString());

                ArrayList<String> wordsInSentence = sentence.splitSentence();
                for (String word : wordsInSentence) {
                    System.out.println(word);
                }
            }

        } catch (Exception exception) {
            System.out.println("Error reading file: " + exception.getMessage());
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