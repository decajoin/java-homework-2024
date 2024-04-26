package niss.net;

public class Card {
	private int number;
	private int suit;
	// 两种构造方法
	public Card(int number, int suit) {
		super();
		this.number = number;
		this.suit = suit;
	}
	public Card() {
		super();
	}
	
	public int getNumber() {
		return number;
	}
	public int getSuit() {
		return suit;
	}
	// 返回数字
	public String getSymbol() {
	    String[] symbols = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	    return symbols[number - 1];
	}
	// 返回花色
	public String getSuitSymbol() {
	    String[] suits = {"♠", "♥", "♣", "♦"};
	    return suits[suit - 1];
	}
	// 返回数字和花色
	public String toString() {
	    return getSuitSymbol() + getSymbol();
	}
}
