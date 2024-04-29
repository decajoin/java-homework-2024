package niss.net;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Player {
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
	

	// 一次性获得全部手牌
	public void setCards(ArrayList<Card> cards) {
		this.cards = cards;
	}
	
	// 获得牌
//	public void takeACard(Card card) {
//		if (cards.size() <= maxCardNumber) {
//			cards.add(card);
//		}
//		
//	}
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

	public Card playingCard2(Card lastCard, int lastPlayerIndex) {

		// 不是第一次出牌，根据上家出的牌选择合适的牌进行出牌
        int lastCardSuit = lastCard.getSuit(); // 上家出的牌的花色
        int lastCardNumber = lastCard.getNumber(); // 上家出的牌的数字
        
        // 如果上一次出牌的不是本人，id是1，2，3，4所以要减一
        if (lastPlayerIndex != playerId - 1) {
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
        else {
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
                return playingCard1();
            }
        }
        
	}
	@Override
	public String toString() {
		return "Player [playerId=" + playerId + ", playerName=" + playerName + ", totalScore=" + totalScore + "]";
	}
	
	
}
