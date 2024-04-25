package niss.net;

public class Answer {
    private int questionNumber;
    private String questionType;
    private String correctAnswer;
    private String studentAnswer;
    private int questionScore;

    // 构造函数
    public Answer(int questionNumber, int questionScore,String questionType, String correctAnswer, String studentAnswer) {
        this.questionNumber = questionNumber;
        this.questionType = questionType;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
        this.questionScore = questionScore;
    }

    // 设置学生答案
    public void setStudentAnswer(String studentAnswer) {
        this.studentAnswer = studentAnswer;
    }
    
    public String getStudentAnswer() {
		return studentAnswer;
	}
    

    public int getQuestionNumber() {
		return questionNumber;
	}

	public void setQuestionNumber(int questionNumber) {
		this.questionNumber = questionNumber;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getCorrectAnswer() {
		return correctAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		this.correctAnswer = correctAnswer;
	}

	
	
	public int getQuestionScore() {
		return questionScore;
	}

	public void setQuestionScore(int questionScore) {
		this.questionScore = questionScore;
	}

	// 判断另外一个答案是否正确
    public boolean isCorrect(Answer anotherAnswer) {
        return this.correctAnswer.equals(anotherAnswer.studentAnswer);
    }

    // 输出标准答案
    public void printCorrectAnswer() {
        System.out.println("Correct Answer: " + correctAnswer);
    }

}

