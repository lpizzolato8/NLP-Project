public class Part2Tester{

	public static void main(String[] args){

		// Test case for Twitter Political dataset
		String line = "\"4\",\"3\",\"Mon May 11 03:17:40 UTC 2009\",\"kindle2\",\"tpryan\",\"@stellargirl I " +
			"loooooooovvvvvveee my Kindle2. Not that the DX is cool, but the 2 is fantastic in its own right.\"";
		Sentence expected = new Sentence("@stellargirl I " +
			"loooooooovvvvvveee my Kindle2 Not that the DX is cool but the 2 is fantastic in its own right", 
			"tpryan", "May 11 2009");
		Sentence result = Sentence.convertLine(line);
		System.out.println("test1: " + expected.getText().equals(result.getText()));
		System.out.println("test2: " + expected.getAuthor().equals(result.getAuthor()));
		System.out.println("test3: " + expected.getTimestamp().equals(result.getTimestamp()));

		// Test case for EBook dataset
		Sentence sent = new Sentence("Example sentence from a book", "Ada Lovelace", "Chapter 1");
		sent.addToEnd(" continues, and then ends, here.");
		System.out.println("test1: " + sent.getText().equals("Example sentence from a book continues and then ends here"));


		// Test cases for Twitter COVID19 dataset
		String line = "0,119,4/19/20 12:00 AM,,agostinhozinga,,\"London, England\",taiwan vice presid chen chien jen countri fight covid19";
		Sentence expected = new Sentence("taiwan vice presid chen chien jen countri fight covid19", 
			"agostinhozinga", "April 19 2020");
		Sentence result = Sentence.convertLine(line);
		System.out.println("test1: " + expected.getText().equals(result.getText()));
		System.out.println("test2: " + expected.getAuthor().equals(result.getAuthor()));
		System.out.println("test3: " + expected.getTimestamp().equals(result.getTimestamp()));

		line = "0,0,4/22/20 12:00 AM,NWILife,NWINews,,Northwest Indiana,horizon bank donat urban leagu covid19 outreach";
		expected = new Sentence("horizon bank donat urban leagu covid19 outreach", 
			"NWINews", "April 22 2020");
		result = Sentence.convertLine(line);
		System.out.println("test4: " + expected.getText().equals(result.getText()));
		System.out.println("test5: " + expected.getAuthor().equals(result.getAuthor()));
		System.out.println("test6: " + expected.getTimestamp().equals(result.getTimestamp()));
	}
}