
public class Part1Tester{

	public static void main(String[] args){
		Sentence sent1 = new Sentence("@stellargirl I loooooooovvvvvveee my Kindle2. Not that the DX is cool,"
			+" but the 2 is fantastic in its own right.", "tpryan", "Mon May 11 03:17:40 UTC 2009");

		boolean test1 = sent1.getText().equals("@stellargirl I loooooooovvvvvveee my Kindle2. Not that the DX is cool,"
			+" but the 2 is fantastic in its own right.");
		boolean test2 = sent1.getAuthor().equals("tpryan");
		boolean test3 = sent1.getTimestamp().equals("Mon May 11 03:17:40 UTC 2009");

		sent1.setText("Hello world!");
		boolean test4 = sent1.getText().equals("Hello world!");
		boolean test5 = sent1.getAuthor().equals("tpryan");
		boolean test6 = sent1.getTimestamp().equals("Mon May 11 03:17:40 UTC 2009");

		sent1.setAuthor("AdaLovelace");
		boolean test7 = sent1.getText().equals("Hello world!");
		boolean test8 = sent1.getAuthor().equals("AdaLovelace");
		boolean test9 = sent1.getTimestamp().equals("Mon May 11 03:17:40 UTC 2009");

		sent1.setTimestamp("Mon May 11 03:17:40 UTC 1843");
		boolean test10 = sent1.getText().equals("Hello world!");
		boolean test11 = sent1.getAuthor().equals("AdaLovelace");
		boolean test12 = sent1.getTimestamp().equals("Mon May 11 03:17:40 UTC 1843");

		String result = "{author:AdaLovelace, sentence:\"Hello world!\", timestamp:\"Mon May 11 03:17:40 UTC 1843\"}";
		boolean test13 = result.equals(sent1.toString());

		System.out.println("test1: " + test1);
		System.out.println("test2: " + test2);
		System.out.println("test3: " + test3);
		System.out.println("test4: " + test4);
		System.out.println("test5: " + test5);
		System.out.println("test6: " + test6);
		System.out.println("test7: " + test7);
		System.out.println("test8: " + test8);
		System.out.println("test9: " + test9);
		System.out.println("test10: " + test10);
		System.out.println("test11: " + test11);
		System.out.println("test12: " + test12);
		System.out.println("test13: " + test13);
	}
}