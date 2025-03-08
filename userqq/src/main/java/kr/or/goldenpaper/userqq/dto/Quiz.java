package kr.or.goldenpaper.userqq.dto;

public class Quiz {
	private int quiz_id;
	private String user_id;
	private String title;
	private String content;
	private String created_time;
	private int solved_count;
	private String quiz_type;
	private String answer;
	public int getQuiz_id() {
		return quiz_id;
	}
	public void setQuiz_id(int post_id) {
		this.quiz_id = post_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCreated_time() {
		return created_time;
	}
	public void setCreated_time(String created_time) {
		this.created_time = created_time;
	}
	public int getSolved_count() {
		return solved_count;
	}
	public void setSolved_count(int solved_count) {
		this.solved_count = solved_count;
	}
	public String getQuiz_type() {
		return quiz_type;
	}
	public void setQuiz_type(String quiz_type) {
		this.quiz_type = quiz_type;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "Quiz [post_id=" + quiz_id + ", user_id=" + user_id + ", title=" + title + ", content=" + content
				+ ", created_time=" + created_time + ", solved_count=" + solved_count + ", quiz_type=" + quiz_type
				+ ", answer=" + answer + "]";
	}
}
