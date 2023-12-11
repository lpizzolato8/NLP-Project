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
                Sentence sentence = Sentence.convertLineTagged(currentLine);
                sentences.add(sentence);
            }
            
        
            double totalSentimentTag = 0;
            double totalSentimentNoTag = 0;

            int tweetCountTag = 0;
            int tweetCountNoTag = 0;
            for (Sentence sentence : sentences) {

                boolean notLengthOrFirstElementError = sentence.getTimestamp().length() > 1 && !(sentence.getTimestamp().equals("created_at"));
                
                if (notLengthOrFirstElementError && sentence.keep("April 19 2020-April 30 2020")) 
                    
                    if (!(sentence.getText().equals(" "))) {
                        //System.out.println(sentence.toString() + "\tSentiment Score:" + sentence.getSentiment(sentence.getText()));
                        
                        int sentiment = sentence.getSentiment(sentence.getText());

                        System.out.println(sentence.toString() + "\t Has Tag?: " + sentence.getTag() +"\tSentiment Score: " + sentiment);

                        
                        if (sentence.getTag()) {
                            totalSentimentTag += sentiment;
                            tweetCountTag++;
                        } else {
                            totalSentimentNoTag += sentiment;
                            tweetCountNoTag++;
                        }
                    }
                    
            }
            System.out.println("Average Sentiment of Posts With User Mentions: " + totalSentimentTag/tweetCountTag + "\nAverage Sentiment of Posts With No User Mentions: " + totalSentimentNoTag/tweetCountNoTag);


            /* 
            for (Sentence sentence : sentences) {          

                
                

                ArrayList<String> wordsInSentence = sentence.splitSentence();
                for (String word : wordsInSentence) {
                    //System.out.println(word);
                }
                
            }
            */

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