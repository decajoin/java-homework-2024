package niss.net;

import java.util.*;

import niss.xxx.Card;

class Card {
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
class Player {
	private int playerId;
	private String playerName;
	private ArrayList<Card> cards;
	private int maxCardNumber;
	private int handCardNumber;
	private int totalScore;
	private int num1, num2, num3, num4;
	// 两种构造函数
	public Player(int playerId, String playerName, ArrayList<Card> cards, int maxCardNumber) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
		this.cards = cards;
		this.maxCardNumber = maxCardNumber;
	}
	public Player() {
		super();
	}
	
	public void takeACard(Card card) {
		cards.add(card);
	}
	// 统计玩家手中每种花色牌的数量
	public void countTheNumberOfCardsInEachSuit() {
		handCardNumber = cards.size();
		for (Card card : cards) {
			if (card.getSuit() == 1) num1++;
			else if (card.getSuit() == 2) num2++;
			else if (card.getSuit() == 3) num3++;
			else num4++;
		}
	}
	// 整理牌方法1
	public void organizeCardsBySuit() {
	    ArrayList<Card> organizedCards = new ArrayList<>();
	    for (int suit = 1; suit <= 4; suit++) {
	        for (Card card : cards) {
	            if (card.getSuit() == suit) {
	                organizedCards.add(card);
	            }
	        }
	    }
	    cards = organizedCards;
	}
	// 整理牌方法2
	public void organizeCardsByNumber() {
		Collections.sort(cards, new Comparator<Card>() {
			public int compare(Card card1, Card card2) {
				return card1.getNumber() - card2.getNumber();
			}
		});
	}
	
	// 屏幕展示玩家手中所有的牌
	public void displayCards() {
	    System.out.println("Player " + playerId + ": " + playerName);
	    for (Card card : cards) {
	        System.out.print(card + " ");
	    }
	    System.out.println();
	}

	// 判断玩家是否具有出牌资格
	public boolean hasValidMove() {
	    // 实现判断出牌资格的逻辑
	    return false; // 这里需要根据游戏规则实现具体逻辑
	}
}


public class PlayCard {
	public static void main(String[] args) {
		Card card = new Card(10, 1);
		System.out.println(card.toString());
	}
}
