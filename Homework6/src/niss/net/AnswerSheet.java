package niss.net;

import java.util.ArrayList;
import java.util.Scanner;

public class AnswerSheet {
    private int paperNumber;
    private int studentID;
    private String studentName;
    private ArrayList<Answer> answers;

    // 构造函数
    public AnswerSheet(int paperNumber, int studentID, String studentName) {
        this.paperNumber = paperNumber;
        this.studentID = studentID;
        this.studentName = studentName;
        this.answers = new ArrayList<>();
    }
    
    

    public int getPaperNumber() {
		return paperNumber;
	}



	public void setPaperNumber(int paperNumber) {
		this.paperNumber = paperNumber;
	}



	public int getStudentID() {
		return studentID;
	}



	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}



	public String getStudentName() {
		return studentName;
	}



	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}



	public ArrayList<Answer> getAnswers() {
		return answers;
	}



	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}



	// 判断是否还有题目未做完
	public boolean isComplete() {
	    for (Answer answer : answers) {
	        // 如果有未回答的题目，返回 false
	        if (answer.getStudentAnswer() == null || answer.getStudentAnswer().isEmpty()) {
	            return false;
	        }
	    }
	    // 所有题目都已回答，返回 true
	    return true;
	}


    // 根据标准答案进行判卷
    public int gradePaper(ArrayList<Answer> answerKey) {
        int score = 0;
        // 遍历答案列表，依次判断每道题的得分情况
        for (Answer answer : answers) {
            for (Answer key : answerKey) {
                if (answer.getQuestionNumber() == key.getQuestionNumber()) {
                    if (key.isCorrect(answer)) {
                        score += key.getQuestionScore(); // 调用 getScore() 方法
                    }
                    break;
                }
            }
        }
        return score;
    }

 // 输出该同学的成绩（学号，试卷编号，成绩)
    public void printScore() {
        ArrayList<Answer> answerKey = new ArrayList<>();
        int score = gradePaper(answerKey); 
        System.out.println("Student ID: " + studentID);
        System.out.println("Paper Number: " + paperNumber);
        System.out.println("Score: " + score);
    }

    // 主方法
    public static void main(String[] args) {
    	// 创建一个答题卡对象
        AnswerSheet answerSheet = new AnswerSheet(1, 123456, "Alice");

        // 创建两道题目的答案
        Answer answer1 = new Answer(1, 5, "选择题", "", "A");
        Answer answer2 = new Answer(2, 10, "填空题", "", "Google");

        // 将答案添加到答题卡中
        answerSheet.getAnswers().add(answer1);
        answerSheet.getAnswers().add(answer2);

        // 测试isComplete()函数
        boolean complete = answerSheet.isComplete();
        System.out.println("Is the answer sheet complete? " + complete);
        
        // 创建试卷的标准答案列表
        ArrayList<Answer> answerKey = new ArrayList<>();
        answerKey.add(new Answer(1, 5, "选择题", "A", ""));
        answerKey.add(new Answer(2, 10, "填空题", "Google", ""));

        // 输出学生的成绩
        System.out.println("Student Name: " + answerSheet.getStudentName());
        System.out.println("Student ID: " + answerSheet.getStudentID());
        System.out.println("Paper Number: " + answerSheet.getPaperNumber());
        int score = answerSheet.gradePaper(answerKey);
        System.out.println("Score: " + score);
        
        // 测试compareQuestionContent()函数
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the first question content:");
        String content1 = scanner.nextLine();
        System.out.println("Enter the second question content:");
        String content2 = scanner.nextLine();
        
        // 创建两个题目对象
        Question question1 = new Question(1, "填空题", content1, 1, 5);
        Question question2 = new Question(2, "填空题", content2, 2, 10);

        // 比较题目内容并输出相同字符个数
        int sameChars = question1.compareQuestionContent(question2);
        System.out.println("Number of same characters: " + sameChars);
    	
    }
}
