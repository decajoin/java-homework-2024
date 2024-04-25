package niss.net;


public class Question {
    private int questionNumber;
    private String questionType;
    private String questionContent;
    private int difficultyLevel;
    private int score;

    // 构造函数
    public Question(int questionNumber, String questionType, String questionContent, int difficultyLevel, int score) {
        this.questionNumber = questionNumber;
        this.questionType = questionType;
        this.questionContent = questionContent;
        this.difficultyLevel = difficultyLevel;
        this.score = score;
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

	public int getDifficultyLevel() {
		return difficultyLevel;
	}

	public void setDifficultyLevel(int difficultyLevel) {
		this.difficultyLevel = difficultyLevel;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	 // 设置属性值的方法
    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }
	
    // 返回属性值的方法
    public String getQuestionContent() {
        return questionContent;
    }
    
    
    // 从键盘输入题目与另一条题目比较题目内容是否相同，返回相同字符个数
    public int compareQuestionContent(Question anotherQuestion) {
        String thisQuestionContent = this.getQuestionContent();
        String anotherQuestionContent = anotherQuestion.getQuestionContent();

        int commonCharCount = 0;
        int minLength = Math.min(thisQuestionContent.length(), anotherQuestionContent.length());

        for (int i = 0; i < minLength; i++) {
            if (thisQuestionContent.charAt(i) == anotherQuestionContent.charAt(i)) {
                commonCharCount++;
            }
        }

        return commonCharCount;
    }

    // 查找题目中是否含有关键词 keyword
    public boolean containsKeyword(String keyword) {
        return questionContent.contains(keyword);
    }

}

