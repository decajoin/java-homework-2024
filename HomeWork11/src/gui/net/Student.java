package gui.net;

public class Student {
    private String id;
    private double basicScore;
    private double mainFunctionScore;
    private double function1Score;
    private double function2Score;

    public Student(String id, double basicScore, double mainFunctionScore, double function1Score, double function2Score) {
        this.id = id;
        this.basicScore = basicScore;
        this.mainFunctionScore = mainFunctionScore;
        this.function1Score = function1Score;
        this.function2Score = function2Score;
    }

    public String getId() {
        return id;
    }

    public double getTotalScore() {
        return basicScore + mainFunctionScore + function1Score + function2Score;
    }

    @Override
    public String toString() {
        return id + " " + basicScore + " " + mainFunctionScore + " " + function1Score + " " + function2Score + " " + getTotalScore();
    }
}
	