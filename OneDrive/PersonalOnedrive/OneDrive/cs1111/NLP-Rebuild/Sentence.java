public class Sentence {
	private String time;
	private String author;
	private String text;

	public Sentence(String text, String author, String timestamp) {
		this.text = text;
		this.author = author;
		time = timestamp;
	}

	public String toString() {
		return "{author:" + author + ", sentence:\"" + text + "\", timestamp:\"" + time + "\"}";
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

}
