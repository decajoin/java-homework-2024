package niss.net;

public class SuperCard {

    public class Account {
        double account_money;
        String account_id;
        String owner_name;
        String owner_id;
		String pass_word;

        public Account() {
			super();
		}
		public Account(double account_money, String account_id, String owner_name, String owner_id, String pass_word) {
			super();
			this.account_money = account_money;
			this.account_id = account_id;
			this.owner_name = owner_name;
			this.owner_id = owner_id;
			this.pass_word = pass_word;
		}


		// 查询账户余额
		public double getAccount_money() {
			return account_money;
		}
		public void setAccount_money(double account_money) {
			this.account_money = account_money;
		}
		public String getAccount_id() {
			return account_id;
		}
		public void setAccount_id(String account_id) {
			this.account_id = account_id;
		}
		public String getOwner_name() {
			return owner_name;
		}
		public void setOwner_name(String owner_name) {
			this.owner_name = owner_name;
		}
		public String getOwner_id() {
			return owner_id;
		}
		public void setOwner_id(String owner_id) {
			this.owner_id = owner_id;
		}
		public String getPass_word() {
			return pass_word;
		}
		public void setPass_word(String pass_word) {
			this.pass_word = pass_word;
		}

		// 为账户充值
		public void depositMoney(double add_money) {
			account_money += add_money;
		}
    }

    interface Parkable {
        void enterPark();
    }

    public class Card {
        String card_num;
        String card_catgroy;

        public String getCard_num() {
            return card_num;
        }

        public void setCard_num(String card_num) {
            this.card_num = card_num;
        }

        public String getCard_catgroy() {
            return card_catgroy;
        }

        public void setCard_catgroy(String card_catgroy) {
            this.card_catgroy = card_catgroy;
        }

		public Card(String card_num, String card_catgroy) {
			super();
			this.card_num = card_num;
			this.card_catgroy = card_catgroy;
		}

		public Card() {
			super();
		}
    }

    public class BusCard extends Card {
        Account account;

		public Account getAccount() {
			return account;
		}

		public void setAccount(Account account) {
			this.account = account;
		}

		public BusCard(String card_num, String card_catgroy, Account account) {
			super(card_num, card_catgroy);
			this.account = account;
		}

		public BusCard(String card_num, String card_catgroy) {
			super(card_num, card_catgroy);
		}

		public void takeBus(int stationNum) {
			double busFee = stationNum * 0.5;
			account.account_money -= busFee;
			System.out.println("本次乘车的站数为 " + stationNum + ",本次乘车费用为 " + busFee);
		}
        
    }

    public class RealNameBusCard extends BusCard implements Parkable {
        String real_name;
        double discount_rate;
        boolean ispark_card;
		public String getReal_name() {
			return real_name;
		}
		public void setReal_name(String real_name) {
			this.real_name = real_name;
		}
		public double getDiscount_rate() {
			return discount_rate;
		}
		public void setDiscount_rate(double discount_rate) {
			this.discount_rate = discount_rate;
		}
		public boolean isPark_card() {
			return ispark_card;
		}
		public void setPark_card(boolean ispark_card) {
			this.ispark_card = ispark_card;
		}
		public RealNameBusCard(String card_num, String card_catgroy, Account account, String real_name,
				double discount_rate, boolean ispark_card) {
			super(card_num, card_catgroy, account);
			this.real_name = real_name;
			this.discount_rate = discount_rate;
			this.ispark_card = ispark_card;
		}
		public RealNameBusCard(String card_num, String card_catgroy, Account account) {
			super(card_num, card_catgroy, account);
		}

		public void enterPark() {
			if (ispark_card) {
				System.out.println("您已开通公园年卡，可以进入公园");
			}
			else {
				System.out.println("还没有开通公园年卡");
			}
		}
		@Override
		public void takeBus(int stationNum) {
			double busFee = stationNum * 0.5 * discount_rate;
			account.account_money -= busFee;
			System.out.println("本次乘坐的站数为 " + stationNum + ",本次乘车费用优惠后为 " + busFee);
		}

    }

    public static void main(String[] args) {
    	// Creating an account
        SuperCard.Account account1 = new SuperCard().new Account(1000.0, "9876", "小红", "ABCD1234", "password");

        // Creating a bus card
        SuperCard.BusCard busCard = new SuperCard().new BusCard("123", "非实名制金陵通", account1);


    	// Creating an account
        SuperCard.Account account2 = new SuperCard().new Account(50.0, "9877", "小白", "ABCD4321", "password");

        // Creating a real name bus card
        SuperCard.RealNameBusCard realNameBusCard = new SuperCard().new RealNameBusCard("12345", "实名制金陵通", account2, "小白", 0.8, true);

        // Using the bus card
        System.out.println("小红乘车前账户余额: " + account1.getAccount_money());
        busCard.takeBus(5);
        System.out.println("小红乘车后账户余额: " + account1.getAccount_money());

        System.out.println("-----------------------------------------------------------");

        System.out.println("小白乘车前账户余额: " + account2.getAccount_money());
        realNameBusCard.takeBus(5);
        System.out.println("小白乘车后账户余额: " + account2.getAccount_money());

        // Using the real name bus card for parking
        realNameBusCard.enterPark();
    }
}
