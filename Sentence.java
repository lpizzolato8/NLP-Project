import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.ejml.simple.SimpleMatrix;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.neural.rnn.RNNCoreAnnotations;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations;
import edu.stanford.nlp.sentiment.SentimentCoreAnnotations.SentimentAnnotatedTree;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.util.CoreMap;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

class Sentence {
    private String time;
    private String author;
    private String text;
    static String[] monthList = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };
    static HashMap<String, Integer> printTopWords = new HashMap<>();
    
    
    public int getSentiment(String tweet){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, parse, sentiment");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);
        Annotation annotation = pipeline.process(tweet);  // operated on getText
        CoreMap sentence = annotation.get(CoreAnnotations.SentencesAnnotation.class).get(0);
        Tree tree = sentence.get(SentimentCoreAnnotations.SentimentAnnotatedTree.class);
        return RNNCoreAnnotations.getPredictedClass(tree);
    }

    

    String[] stopwords = { "a", "about", "above", "after", "again", "against", "all", "am", "an", "and", "any",
            "are", "aren't", "as", "at", "be", "because", "been", "before", "being", "below", "between", "both",
            "but", "by", "can't", "cannot", "could", "couldn't", "did", "didn't", "do", "does", "doesn't", "doing",
            "don't", "down", "during", "each", "few", "for", "from", "further", "had", "hadn't", "has", "hasn't",
            "have", "haven't", "having", "he", "he'd", "he'll", "he's", "her", "here", "here's", "hers", "herself",
            "him", "himself", "his", "how", "how's", "i", "i'd", "i'll", "i'm", "i've", "if", "in", "into", "is",
            "isn't", "it", "it's", "its", "itself", "let's", "me", "more", "most", "mustn't", "my", "myself", "no",
            "nor", "not", "of", "off", "on", "once", "only", "or", "other", "ought", "our", "ours ourselves", "out",
            "over", "own", "same", "shan't", "she", "she'd", "she'll", "she's", "should", "shouldn't", "so", "some",
            "such", "than", "that", "that's", "the", "their", "theirs", "them", "themselves", "then", "there",
            "there's", "these", "they", "they'd", "they'll", "they're", "they've", "this", "those", "through", "to",
            "too", "under", "until", "up", "very", "was", "wasn't", "we", "we'd", "we'll", "we're", "we've", "were",
            "weren't", "what", "what's", "when", "when's", "where", "where's", "which", "while", "who", "who's",
            "whom", "why", "why's", "with", "won't", "would", "wouldn't", "you", "you'd", "you'll", "you're",
            "you've", "your", "yours", "yourself", "yourselves" }; // from https://www.ranks.nl/stopwords

    public Sentence(String text, String author, String timestamp) {
        this.text = text;
        this.author = author;
        time = timestamp;
    }

    @Override
    public String toString() {
        return "{author:" + author + ", sentence:\"" + text + ", timestamp:\"" + time + "\"}";
    }

    public String getText() {
        return text;
    }

    public void setText(String replaceText) {
        text = replaceText;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String replaceAuthor) {
        author = replaceAuthor;
    }

    public String getTimestamp() {
        return time;
    }

    public void setTimestamp(String replaceTime) {
        time = replaceTime;
    }

    public ArrayList<String> splitSentence() {
        ArrayList<String> wordsList = new ArrayList<>();
        String[] words = text.split("\\s");
        for (String word : words) {
            boolean isStopword = false;
            for (String temp : stopwords) {
                if (word.toLowerCase().equals(temp)) {
                    isStopword = true;
                    break;
                }
            }
            if (isStopword == false) {
                wordsList.add(word.toLowerCase());
            }
        }
        return wordsList;
    }

    public static Sentence convertLine(String currentLine) {

        String[] split = currentLine.split("\"\"\",\"\"\"");

        if (split.length > 7) {

            String author = split[4].replaceAll("[^a-zA-Z0-9 ]", "");
            String line = split[7].replaceAll("[^A-Za-z0-9 ]", "");
            // Convert timestamp date
            String timestamp = convertDate(split[2]);

            // Create sentence object and add it to ArrayList
            Sentence sentence = new Sentence(line, author, timestamp);
            return sentence;
        }

        Sentence sentence1 = new Sentence("ERROR", "ERROR", "ERROR");
        return sentence1;
    }

    public static String convertDate(String date) {
        if (date.equals("created_at"))
            return date;
        else if (date.equals(" ")) {
            return " ";
        }
        String result = "";
        String temp = date.replaceAll(" 12:00 AM", "");
        String[] splitDate = temp.split("/");

        result += monthList[Integer.parseInt(splitDate[0]) - 1] + " ";
        result += splitDate[1] + " 20" + splitDate[2];
        return result;
    }

    public static int convertDateToInt(String date) {
        String[] split = date.split(" ");

        for (int i = 0; i < monthList.length; i++) {
            if (split[0] == monthList[i])
                return i * 100 + Integer.parseInt(split[1]); // e.g. may 23 turns into 523
                
        }
        
        return 0;
    }
    /* 
    public boolean keep(String temporalRange) { // Format: "Month(str) day(int) year(int)"
        boolean isWithin = false;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM");
            Date date = formatter.parse(time);
            Timestamp timeStampDate = new Timestamp(date.getTime());

        } catch (Exception e) {
        }

        if((Split of day > first day && Split of day >= first month)  || (split of day < last day && split of day <= last month) ){

        }
        
        return isWithin;   
    } 
    */

    public boolean keep(String temporalRange) {
        String[] split = temporalRange.split("-");

        int minDate = convertDateToInt(split[0]);
        int maxDate = convertDateToInt(split[1]);

        if (time.)

        return false;
    }
}
