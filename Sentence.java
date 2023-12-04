import java.util.ArrayList;
import java.util.HashMap;

class Sentence {
    private String time;
    private String author;
    private String text;
    static String[] monthList = { "January", "February", "March", "April", "May", "June", "July", "August", "September",
            "October", "November", "December" };
    static HashMap<String, Integer> printTopWords = new HashMap<>();
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

        // Extracting the three desired pieces
        if (split.length > 7) {

            String author = split[4].replaceAll("[^a-zA-Z0-9 ]", "");
            String line = split[7].replaceAll("[^A-Za-z0-9 ]", "");
            // Converting timestamp date
            String timestamp = convertDate(split[2]);

            // Create a Sentence object and add it to the ArrayList
            Sentence sentence = new Sentence(line, author, timestamp);
            return sentence;
        }

        Sentence sentence1 = new Sentence("ERROR", "ERROR", "ERROR");
        return sentence1;
    }

    public static String convertDate(String date) {
        // Check if date format
        if (date.equals("created_at"))
            return date;
        else if (date.equals(" ")) {
            return " ";
        }
        String result = "";
        String temp = date.replaceAll(" 12:00 AM", "");
        String[] splitDate = temp.split("/");

        result += monthList[Integer.parseInt(splitDate[0]) - 1] + " ";
        result += splitDate[1] + " 20" + splitDate[2]; // assuming data from 21st century
        return result;
    }

    
}