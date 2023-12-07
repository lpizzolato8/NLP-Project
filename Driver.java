import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;

class Driver {

    public static HashMap<String, Integer> printTopWords(ArrayList<Sentence> sentenceList) {
        HashMap<String, Integer> wordCounts = new HashMap<>();

        for (Sentence sentence : sentenceList) {
            ArrayList<String> wordsInSentence = sentence.splitSentence();
            for (String word : wordsInSentence) {
                if (wordCounts.containsKey(word)) {
                    int count = wordCounts.get(word);
                    wordCounts.put(word, count + 1);
                } else {
                    wordCounts.put(word, 1);
                }
            }
        }
        return wordCounts;
    }

    public static void main(String[] args) {
        ArrayList<Sentence> sentences = new ArrayList<>();
        ArrayList<Sentence> words = new ArrayList<>();
        String covidFilePath = "Covid-19_Twitter_Dataset_Apr-Jun_2020_fixedSpacing.csv";
        try {
            FileReader fileReader = new FileReader(covidFilePath);
            BufferedReader buffRead = new BufferedReader(fileReader);

            String currentLine;
            while ((currentLine = buffRead.readLine()) != null) {
                String[] split = currentLine.split("\",\"");

                if (split.length > 7) {
                    String text = split[7].replaceAll("\"", "").trim();
                    String author = split[4].replaceAll("\"", "").trim();
                    String timestamp = split[2].replaceAll("\"", "").trim();

                    Sentence sentence = new Sentence(text, author, timestamp);
                    sentences.add(sentence);

                }
            }

            for (Sentence sentence : sentences) {
                
                if (!sentence.getText().equals("")) {
                    System.out.println(sentence.toString() + "\tSentiment Score:" + sentence.getSentiment(sentence.getText()));
                }
                

                ArrayList<String> wordsInSentence = sentence.splitSentence();
                for (String word : wordsInSentence) {
                    //System.out.println(word);
                }
                
            }

            HashMap<String, Integer> topWords = printTopWords(sentences);
            Map.Entry<String, Integer> maxEntry = null;
            for (Map.Entry<String, Integer> entry : topWords.entrySet()) {
                if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                    maxEntry = entry;
                }
            }

            int maxValueLen = maxEntry != null ? maxEntry.getValue().toString().length() : 0;
            ArrayList<String> results = new ArrayList<String>();
            for (Map.Entry<String, Integer> set : topWords.entrySet()) {
                String value = set.getValue().toString();
                while (value.length() < maxValueLen)
                    value = " " + value;
                results.add(value + " of " + set.getKey());
            }
            Collections.sort(results);
            Collections.reverse(results);

            for (int i = 0; i < results.size() && i < 100; i++) {
                System.out.println(results.get(i));
            }
        } catch (Exception exception) {
            System.out.println("Error reading file: " + exception.getMessage());
        }
    }
}