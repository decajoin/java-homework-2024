package niss.net;

import java.nio.file.attribute.UserPrincipalLookupService;
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
	private int maxCardNumber = 13;
	private int totalScore;
	private int num1, num2, num3, num4;
	// 两种构造函数
	public Player(int playerId, String playerName) {
		super();
		this.playerId = playerId;
		this.playerName = playerName;
	}
	public Player() {
		super();
	}
	
	public int getTotalScore() {
		return totalScore;
	}
	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}
	

	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	// 获得牌
	public void takeACard(Card card) {
		if (cards.size() <= maxCardNumber) {
			cards.add(card);
		}
		
	}
	// 统计玩家手中每种花色牌的数量
	public void countTheNumberOfCardsInEachSuit() {
		for (Card card : cards) {
			if (card.getSuit() == 1) num1++;
			else if (card.getSuit() == 2) num2++;
			else if (card.getSuit() == 3) num3++;
			else num4++;
		}
	}

	// 统计玩家手中所有牌的数量
	public int countCards() {
		return cards.size();
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
	    System.out.print("Player " + playerId + ": " + playerName + " 剩下的牌有:");
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
	
	// 不考虑上家出牌方式 - 开局第一个出牌
	public Card playingCard1() {
        // 统计玩家手中每种花色的牌的数量
        countTheNumberOfCardsInEachSuit();

        // 找到剩余牌最多的花色
        int maxSuit = 1; // 假设第一种花色是剩余牌最多的
        int maxCount = num1; // 假设第一种花色牌的数量最多
        if (num2 > maxCount) {
            maxCount = num2;
            maxSuit = 2;
        }
        if (num3 > maxCount) {
            maxCount = num3;
            maxSuit = 3;
        }
        if (num4 > maxCount) {
            maxCount = num4;
            maxSuit = 4;
        }

        // 找到剩余牌最多花色中数字最小的牌
        Card minCard = null;
        for (Card card : cards) {
            if (card.getSuit() == maxSuit) {
                if (minCard == null || card.getNumber() < minCard.getNumber()) {
                    minCard = card;
                }
            }
        }

        // 如果找到了要出的牌
        if (minCard != null) {
            // 打印出牌信息
            System.out.println("Player " + playerId + " plays: " + minCard);
            
            // 将这张牌从手牌中移除
            cards.remove(minCard);
            
            // 更新相应花色的牌数量统计
            if (maxSuit == 1) num1--;
            else if (maxSuit == 2) num2--;
            else if (maxSuit == 3) num3--;
            else num4--;
            
            // 返回打出的牌
            return minCard;
        }
        return null;
    }

	public Card playingCard2(Card lastCard) {

		// 不是第一次出牌，根据上家出的牌选择合适的牌进行出牌
        int lastCardSuit = lastCard.getSuit(); // 上家出的牌的花色
        int lastCardNumber = lastCard.getNumber(); // 上家出的牌的数字

        // 寻找手中同花色的牌，并选择最小的牌出
        Card minCard = null;
        for (Card card : cards) {
            if (card.getSuit() == lastCardSuit && card.getNumber() > lastCardNumber) {
                if (minCard == null || card.getNumber() < minCard.getNumber()) {
                    minCard = card;
                }
            }
        }

        // 如果找到了要出的牌
        if (minCard != null) {
            // 打印出牌信息
            System.out.println("Player " + playerId + " plays: " + minCard);
            cards.remove(minCard); // 将选出的牌从手牌中移除
            	
            // 更新相应花色的牌数量统计
            if (lastCardSuit == 1) num1--;
            else if (lastCardSuit == 2) num2--;
            else if (lastCardSuit == 3) num3--;
            else num4--;
            
            return minCard;
        } else {
            // 如果没有找到符合规则的牌可出，则选择 PASS，表示放弃出牌
            System.out.println("Player " + playerId + " Pass!");
            return null;
        }
	}
}


class PlayPork {
    public ArrayList<Card> nowCards;
    public Player[] players;
    public boolean[] propriety;

    public PlayPork() {
        // 初始化 players 数组
        players = new Player[4];
        players[0] = new Player(1, "小红");
        players[1] = new Player(2, "小白");
        players[2] = new Player(3, "小绿");
        players[3] = new Player(4, "小青");

        // 初始化 propriety 数组
        propriety = new boolean[4];
        int firstPlayerIndex = new Random().nextInt(4);
        propriety[firstPlayerIndex] = true;

        // 初始化牌
        nowCards = new ArrayList<>();
        for (int suit = 1; suit <= 4; suit++) {
            for (int number = 1; number <= 13; number++) {
                nowCards.add(new Card(number, suit));
            }
        }

        // 洗牌
        shuffle();

        // 发牌给每个玩家
        dealCards();
    }

    // 打印玩家出牌优先级顺序
    public void printPropriety() {
    	System.out.println("随机的出牌优先级是：");
    	for (int i = 0; i < 4; i++) {
    		if (propriety[i]) System.out.println("true " + (i + 1));
    		else System.out.println("false " + (i + 1));
    	}
    }

    // 更新出牌优先权
    public void updatePropriety(int currentPlayerIndex) {
        // 将上一轮出牌的玩家的出牌优先权设为 false
        propriety[currentPlayerIndex] = false;
        
        // 找到下一轮应该出牌的玩家的索引
        int nextPlayerIndex = (currentPlayerIndex + 1) % 4;

        // 更新下一轮出牌的玩家的出牌优先权
        propriety[nextPlayerIndex] = true;
    }
    
    // 洗牌
    public void shuffle() {
        Collections.shuffle(nowCards);
    }

    // 发牌给每个玩家
    private void dealCards() {
        int cardIndex = 0;
        for (int i = 0; i < 4; i++) {
            ArrayList<Card> playerCards = new ArrayList<>();
            for (int j = 0; j < 13; j++) {
                playerCards.add(nowCards.get(cardIndex));
                cardIndex++;
            }
            players[i].setCards(playerCards);
        }
    }
    
    // 判断这一局是否结束，只要有一个玩家手牌为0就结束
    public boolean isOver() {
    	for (int i = 0; i < 4; i++) {
    		if (players[i].isTheCardCleared()) return true;
    	}
    	
    	return false;
    }
    
    // 计算每个玩家这回合的得分
    public void calculate() {
    	ArrayList<Integer> countPlayerCards = new ArrayList<Integer>();
    	for (int i = 0; i < 4; i++) {
    		countPlayerCards.add(players[i].countCards());
    	}
    	Collections.sort(countPlayerCards);
    	for (int i = 0; i < 4; i++) {
    		for (int j = 0; j < 4; j++) {
    			if (players[j].countCards() == countPlayerCards.get(i)) {
    				if (i == 0) {
    					players[j].setTotalScore(5);
    				}
    				else if (i == 1) {
    					players[j].setTotalScore(3);
    				}
    				else if (i == 2) {
    					players[j].setTotalScore(2);
    				}
    				else players[j].setTotalScore(1);
    			}
    		}
    	}
    	
    }
    
    // 由优先级数组得到现在该出牌的玩家号码
    public int getPlayerIndex(boolean []propriety) {
    	for (int i = 0; i < 4; i++) {
    		if (propriety[i]) return i;
    	}
		return 0;
    }
    
    // 开始玩一局
    public void playAGame() {
    	printPropriety();
    	Card lastcCard = new Card();
    	
    	int currentPlayerIndex = getPlayerIndex(propriety);
    	
    	// 记录上一个出牌的人的编号
    	int lastMaxPlayerIndex = currentPlayerIndex;
    	
    	Player currentPlayer = players[currentPlayerIndex];
    	lastcCard = currentPlayer.playingCard1();
    	currentPlayer.displayCards();
    	updatePropriety(currentPlayerIndex);
    	
    	while (!isOver()) {
    		currentPlayerIndex = getPlayerIndex(propriety);
    		currentPlayer = players[currentPlayerIndex];
    		
    		Card tempCard = currentPlayer.playingCard2(lastcCard);
    		if (tempCard != null) {
    			lastcCard = tempCard;
    			lastMaxPlayerIndex = currentPlayerIndex;
    		}
    		else {
    			if (currentPlayerIndex == lastMaxPlayerIndex) {
    				lastcCard = currentPlayer.playingCard1();
    				currentPlayer.displayCards();
    				updatePropriety(currentPlayerIndex);
    			}
    		}
    		
    		currentPlayer.displayCards();
    		updatePropriety(currentPlayerIndex);
    	}
    	
    	System.out.println("本轮游戏结束");
    	
    	calculate();
    	
    }
}


public class PlayCard {
	public static void main(String[] args) {
	
		PlayPork playPork = new PlayPork();
		
		playPork.playAGame();
	}
}
