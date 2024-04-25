package niss.net;

import java.util.ArrayList;

public class Paper {
    private int paperNumber;
    private String paperHeader;
    private String examiner;
    private int totalScore;
    private ArrayList<Question> questionSet;
    private ArrayList<Answer> answerKey;

    // 构造函数
    public Paper(int paperNumber, String paperHeader, String examiner, int totalScore) {
        this.paperNumber = paperNumber;
        this.paperHeader = paperHeader;
        this.examiner = examiner;
        this.totalScore = totalScore;
        this.questionSet = new ArrayList<>();
        this.answerKey = new ArrayList<>();
    }
    
    
    
    public int getPaperNumber() {
		return paperNumber;
	}



	public void setPaperNumber(int paperNumber) {
		this.paperNumber = paperNumber;
	}



	public String getPaperHeader() {
		return paperHeader;
	}



	public void setPaperHeader(String paperHeader) {
		this.paperHeader = paperHeader;
	}



	public String getExaminer() {
		return examiner;
	}



	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}



	public int getTotalScore() {
		return totalScore;
	}



	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}



	public ArrayList<Answer> getAnswerKey() {
		return answerKey;
	}



	public void setAnswerKey(ArrayList<Answer> answerKey) {
		this.answerKey = answerKey;
	}



	// 添加试题
    public void addQuestion(Question question) {
        questionSet.add(question);
    }

    // 删除试题
    public void removeQuestion(Question question) {
        questionSet.remove(question);
    }

    // 返回试题个数
    public int getQuestionCount() {
        return questionSet.size();
    }

}

