package learn.net;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener {
	// 计算器按键名字
	private final String[] KEYS = { "7", "8", "9", "+", "%", "4", "5", "6", "-", "x^2", "1", "2", "3",
			"*", "√￣", "0", ".", "=", "/", "1/x"};
	// 计算器功能键名字
	private final String[] COMMANDS = {"Backspace"};
	// 计算器正余弦函数名字
	private final String[] FUN = { "sin", "cos", "tan", "C"};

	// 计算器按键按钮
	private JButton keys[] = new JButton[KEYS.length];
	// 计算器功能键按钮
	private JButton commands[] = new JButton[COMMANDS.length];
	// 计算器正余弦函数按钮
	private JButton fun[] = new JButton[FUN.length];
	// 计算器结果显示文本框
	private JTextField displayText = new JTextField("");
	private JTextField resultText = new JTextField("0");

	private double resultNum = 0.0;
	private boolean firstDigit = true;
	private boolean basicOperator = true;
	private boolean operateValidFlag = true;
	private boolean changeMode = false;
	private boolean sayNotice = false;
	private int index = 0;

	private double num[] = new double[110];
	private char ope[] = new char[110];
	private int count1 = 0, count2 = 0;

	/*
	 * 构造函数
	 */
	public Calculator() {
		// 调用父类方法
		super();
		// 初始化计算器
		init();
		// 设置计算器的背景颜色
		this.setBackground(Color.LIGHT_GRAY);
		// 设置标题
		this.setTitle("Calculator");
		// 设置计算器显示坐标(500, 300)
		this.setLocation(500, 300);
		// 禁止修改计算器大小
		this.setResizable(false);
		// 计算器中各组件大小自适应
		this.pack();
	}

	/*
	 * 初始化计算器
	 */
	private void init() {
		// 按键字体样式
		Font font = new Font(null, Font.BOLD, 14);
		// 文本框内容右对齐
		displayText.setHorizontalAlignment(JTextField.RIGHT);
		// 禁止修改结果文本框
		displayText.setEditable(false);
		// 设置文本框背景颜色
		displayText.setBackground(Color.WHITE);
		// 设置文本框字体
		displayText.setFont(font);
		// 结果显示
		resultText.setVisible(false);

		// 初始化计算器按键按钮，放在一个面板内
		JPanel keysPanel = new JPanel();
		// 使用网格布局器，4行6列，水平与竖直方向均相差3个像素
		keysPanel.setLayout(new GridLayout(4, 6, 3, 3));
		for (int i = 0; i < KEYS.length; ++i) {
			keys[i] = new JButton(KEYS[i]);
			keysPanel.add(keys[i]);
			keys[i].setFont(font); // 按键字体加粗
			keys[i].setForeground(Color.BLACK); // 设置按键颜色为黑色
		}
		// 设置基本运算符键为红色
		keys[3].setForeground(Color.RED);
		keys[8].setForeground(Color.RED);
		keys[13].setForeground(Color.RED);
		keys[18].setForeground(Color.RED);
		// 设置右侧函数表达式键为蓝色
		keys[4].setForeground(Color.BLUE);
		keys[9].setForeground(Color.BLUE);
		keys[14].setForeground(Color.BLUE);
		keys[19].setForeground(Color.BLUE);
		
		// 初始化功能键按钮，放在一个面板内
		JPanel commandsPanel = new JPanel();
		// 使用网格布局器，1行4列，水平与竖直方向均相差3个像素
		commandsPanel.setLayout(new GridLayout(1, 1, 3, 3));
		for (int i = 0; i < COMMANDS.length; ++i) {
			commands[i] = new JButton(COMMANDS[i]);
			commandsPanel.add(commands[i]);
			commands[i].setFont(font);
			commands[i].setForeground(Color.RED); // 设置按键颜色为红色
		}

		// 初始化正余弦函数键按钮，放在一个面板内
		JPanel funPanel = new JPanel();
		// 使用网格布局器，4行1列，水平与竖直方向均相差3个像素
		funPanel.setLayout(new GridLayout(4, 1, 3, 3));
		for (int i = 0; i < FUN.length; ++i) {
			fun[i] = new JButton(FUN[i]);
			funPanel.add(fun[i]);
			fun[i].setFont(font);
			fun[i].setForeground(Color.BLUE);
		}
		fun[3].setForeground(Color.RED);

		// 将正余弦函数与基本按键放在同一个画板
		JPanel panel = new JPanel();
		// 画板采用边界布局管理器，水平与竖直方向均相差3个像素
		panel.setLayout(new BorderLayout(3, 3));
		panel.add("Center", keysPanel);
		panel.add("West", funPanel);

		// 将文本框放在一个画板
		JPanel text = new JPanel();
		text.setLayout(new BorderLayout());
		text.add("Center", displayText);

		// 整体布局
		getContentPane().setLayout(new BorderLayout(3, 3));
		getContentPane().add("North", text);
		getContentPane().add("Center", commandsPanel);
		getContentPane().add("South", panel);

		// 给所有按键添加时间侦听器
		// 均使用同一个监听器，即本对象
		for (int i = 0; i < KEYS.length; ++i)
			keys[i].addActionListener(this);
		for (int i = 0; i < COMMANDS.length; ++i)
			commands[i].addActionListener(this);
		for (int i = 0; i < FUN.length; ++i)
			fun[i].addActionListener(this);
	}

	/*
	 * 处理事件
	 */
	public void actionPerformed(ActionEvent e) {
		// 获取事件源标签
		String label = e.getActionCommand();
		// 回退
		if (label.equals(COMMANDS[0]))
			handleBackspace();
		// 清空屏幕
		else if (label.equals(FUN[3]))
			handleC();
		// 数字键
		else if ("0123456789.".contains(label))
			handleNumber(label);
		// 运算符
		else
			handleOperator(label);
	}

	/*
	 * 处理Backspace按键事件
	 */
	private void handleBackspace() {
		String distext = displayText.getText(), restext = resultText.getText();
		int dislen = distext.length(), reslen = restext.length();
		if (reslen > 0) {
			restext = restext.substring(0, reslen - 1);
			distext = distext.substring(0, dislen - 1);
			if (restext.length() == 0) {
				displayText.setText("0");
				resultText.setText("0");
				firstDigit = true;
			} else {
				displayText.setText(distext);
				resultText.setText(restext);
			}
		}
	}

	/*
	 * 处理C按键事件
	 */
	private void handleC() {
		// 初始化
		displayText.setText("0");
		resultText.setText("0");
		resultNum = 0.0;
		firstDigit = true;
		basicOperator = true;
		operateValidFlag = true;
		changeMode = false;
		sayNotice = false;
		index = 0;
		count1 = count2 = 0;
	}

	/*
	 * 处理数字键事件
	 */
	private void handleNumber(String key) {
		// 改变显示模式
		if (changeMode || sayNotice) {
			if (firstDigit)
				displayText.setText(key);
			else
				displayText.setText(displayText.getText() + key);
			sayNotice = false;
		} else
			displayText.setText(displayText.getText() + key);
		// 处理输入数字
		if (firstDigit)
			resultText.setText(key);
		else if (key.equals(".") && (!resultText.getText().contains(".")))
			resultText.setText(resultText.getText() + ".");
		else if (!key.equals("."))
			resultText.setText(resultText.getText() + key);
		firstDigit = false;
	}

	/*
	 * 处理运算符被按下的事件
	 */
	private void handleOperator(String key) {
		// 显示基本运算符
		if ("+-*/%^".contains(key))
			displayText.setText(displayText.getText() + key);
		// 读取运算符
		if ("+-*/".contains(key))
			ope[count2++] = key.charAt(0);
		// 每按一次运算符，读取一次数字
		getNumberFromText();
		// sin函数
		if (key.equals("sin")) {
			basicOperator = false;
			resultNum = Math.sin(num[0] * Math.PI / 180);
		}
		// cos函数
		else if (key.equals("cos")) {
			basicOperator = false;
			resultNum = Math.cos(num[0] * Math.PI / 180);
		}
		// tan函数
		else if (key.equals("tan")) {
			basicOperator = false;
			resultNum = Math.sin(num[0] * Math.PI / 180);
		}
		// 开根号
		else if (key.equals("√￣")) {
			basicOperator = false;
			resultNum = Math.sqrt(num[0]);
		}
		// 倒数
		else if (key.equals("1/x")) {
			basicOperator = false;
			resultNum = num[0];
			if (resultNum == 0.0) // 除零错误
				operateValidFlag = false;
			else
				resultNum = 1.0 / resultNum;
		}
		// 取余
		else if (key.equals("%")) {
			basicOperator = false;
			index = 1;
		}
		// 幂
		else if (key.equals("^")) {
			basicOperator = false;
			index = 2;
		}
		// 平方
		else if (key.equals("x^2")) {
			basicOperator = false;
			resultNum = num[0] * num[0];
		}
		// 等于输出结果
		else if (key.equals("=")) {
			// +-*/基本运算
			if (basicOperator)
				resultNum = handlePolynomial();
			// 处理函数
			if (index > 0) {
				resultNum = handleindex(index);
				index = 0;
			}
			// 输出结果
			resultPrint(resultNum);
			basicOperator = true;
		}
		firstDigit = true;
		operateValidFlag = true;
	}

	/**
	 * 处理多项式运算，考虑运算符优先级
	 */
	private double handlePolynomial() {
		double result = 0.0;
		for (int i = 0; i < count2; ++i) {
			if (ope[i] == '-') {
				num[i + 1] *= (-1.0);
				ope[i] = '+';
			}
		}
		for (int i = 0; i < count2; ++i) {
			if (ope[i] == '*') {
				num[i + 1] *= num[i];
				num[i] = 0.0;
			} else if (ope[i] == '/') {
				if (num[i] == 0.0)
					operateValidFlag = false;
				else
					num[i + 1] = num[i] / num[i + 1];
				num[i] = 0.0;
			}
		}
		for (int i = 0; i < count1; ++i)
			result += num[i];
		return result;
	}

	/**
	 * 处理二元式
	 */
	private double handleindex(int index) {
		double result = 0.0;
		if (index == 1)
			result = num[0] % num[1];
		else if (index == 2)
			result = Math.pow(num[0], num[1]);
		else if (index == 3)
			result = num[0] + num[0] * num[1];
		else if (index == 4)
			result = num[0] * num[0] + num[1] * num[1];
		return result;
	}

	/**
	 * 输出结果，并判断是浮点数还是整数
	 */
	private void resultPrint(double result) {
		if (operateValidFlag) {
			int t1;
			double t2;
			t1 = (int) result;
			t2 = result - t1;
			if (t2 == 0) {
				displayText.setText(String.valueOf(t1));
				resultText.setText(String.valueOf(t1));
			} else {
				displayText.setText(String.valueOf(result));
				resultText.setText(String.valueOf(result));
			}
		} else
			displayText.setText("Illegal operation, please enter again.");
		count1 = count2 = 0; // 初始化
	}

	/**
	 * 从输入文本中获取数字
	 */
	private void getNumberFromText() {
		double result = Double.valueOf(resultText.getText());
		num[count1++] = result;
		System.out.println(count1 + " " + result);
	}

	/**
	 * 主函数
	 */
	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.setVisible(true);
		calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}