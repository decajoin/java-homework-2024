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

	public boolean isTheCardCleared() {
		return cards.isEmpty();
	}
	// 判断玩家是否具有出牌资格
	public boolean hasValidMove(boolean[] propriety) {
	    // 如果玩家手中没有牌了，不能出牌
	    if (cards.isEmpty()) {
	        return false;
	    }
	    
	    // 如果是首次出牌，可以随意出牌
	    // 在游戏开始或者上一轮出牌的玩家出完牌后，都算是首次出牌
	    // 这里假设 propriety 数组记录了每个玩家的出牌优先权，可以根据情况修改条件
	    if (propriety[playerId - 1]) {
	        return true;
	    }
	    
	    // 其他情况根据游戏规则判断是否满足出牌规则，比如是否能出同花色的牌
	    // 这里假设游戏规则是只能出同花色的牌，关于手上的牌能否出，应该交给出牌函数处理
	    
	    return false;
	}
}

class PlayPork {
    private ArrayList<Card> cards;
    private Player[] players;
    private boolean[] propriety;

    public PlayPork() {
        // 初始化牌、玩家等
        
        // 初始化 propriety 数组
        propriety = new boolean[players.length];
        int firstPlayerIndex = new Random().nextInt(players.length);
        propriety[firstPlayerIndex] = true;
    }

    // 其他方法...

    // 更新出牌优先权
    public void updatePropriety(int currentPlayerIndex) {
        // 将上一轮出牌的玩家的出牌优先权设为 false
        propriety[currentPlayerIndex] = false;
        
        // 找到下一轮应该出牌的玩家的索引
        int nextPlayerIndex = (currentPlayerIndex + 1) % players.length;
        while (!players[nextPlayerIndex].hasValidMove(propriety)) {
            nextPlayerIndex = (nextPlayerIndex + 1) % players.length;
        }
        
        // 更新下一轮出牌的玩家的出牌优先权
        propriety[nextPlayerIndex] = true;
    }
}


public class PlayCard {
	public static void main(String[] args) {
		Card card = new Card(10, 1);
		System.out.println(card.toString());
	}
}
