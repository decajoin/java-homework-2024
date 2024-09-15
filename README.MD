# Java实验课作业

- [x] 第5次实验作业

- [x] 第6次实验作业

- [x] 第7次实验作业
- [x] 第8次实验作业
- [x] 第9次实验作业
- [x] 第10次实验作业
- [x] 第11次实验作业
- [x] 第12次实验作业
- [x] 第13次实验作业

## 第5次实验

### 题目

![image-20240427233519011](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427233519011.png)

### 代码执行结果

![image-20240427233929384](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427233929384.png)



## 第6次实验

### 题目

![image-20240427233607050](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427233607050.png)

### 代码执行结果

![image-20240427234047277](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427234047277.png)

## 第7次实验

### 题目

![image-20240427233703972](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427233703972.png)

![image-20240427234516701](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427234516701.png)



### 代码执行结果

![image-20240427234057929](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427234057929.png)

## 第8次实验

### 题目

![image-20240427233759668](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240427233759668.png)

### 代码执行结果

![运行结果演示](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/%E8%BF%90%E8%A1%8C%E7%BB%93%E6%9E%9C%E6%BC%94%E7%A4%BA.gif)

## 第9次实验

### 题目

![image-20240521162223504](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240521162223504.png)

### 代码执行结果

![PixPin_2024-05-21_16-31-57](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/PixPin_2024-05-21_16-31-57.gif)

## 第10次实验

### 题目

![](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240521163744389.png)

### 代码执行结果

![TE](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/TE.gif)

## 第11次实验

### 题目1

![image-20240601110710212](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240601110710212.png)

### 代码执行结果1

+ 生成的密钥

![image-20240601111219653](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240601111219653.png)

+ 原文

![image-20240601111046287](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240601111046287.png)

+ 密文

![image-20240601111127446](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240601111127446.png)

+ 密文还原的明文

![image-20240601111202838](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240601111202838.png)

​	**由实验结果可以看出并不是所有密文都可以正确还原**，这种方法虽然能够在大多数情况下解密正确，但并不能保证100%正确解密。因为在加密过程中使用了对字符进行加密的算法，可能会存在多个原始字符被映射到同一个密文字符的情况，这样在解密时就无法确定具体的原始字符。这种情况下就会导致解密出现错误。



### 题目2

![image-20240601111532476](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240601111532476.png)

### 代码执行结果2

![PixPin_2024-09-15_16-50-06](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/PixPin_2024-09-15_16-50-06.gif)



## 第12次实验

### 题目

根据下面给出的server程序写出client程序

```java
package niss.net;

import java.io.*;
import java.net.*;

public class SimpleServer {
	ServerSocket ss;
	Socket s;

	public SimpleServer() {
		try {
			ss = new ServerSocket(1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public SimpleServer(int port) { // port为端口号
		try {
			ss = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean judgeBouond() {
		return ss.isBound();
	}

	public void waitForConnect() { // 等待与客户端连接
		try {
			while (true) {
				s = ss.accept();
				return;
			}
		} catch (Exception e) {
			System.out.println("error");
		}
	}

	public void SendMessageToClient(String info) { // 向客户端发送消息
		try {
			PrintStream out = new PrintStream(s.getOutputStream());
			out.print(info + "\r\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String GetMessageFromClient() { // 从客户端接受消息
		String str = new String("");
		while (true) {
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				str = br.readLine();
				if (!str.equals(""))
					return str;
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	public void CloseSocket() {
		try {
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void CloseServer() {
		try {
			ss.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) {
		SimpleServer Ser1 = new SimpleServer(5432);
		try {
			Ser1.waitForConnect();
			System.out.println(Ser1.GetMessageFromClient());
			Ser1.SendMessageToClient("hi");
			Ser1.CloseSocket();
			// Ser1.CloseServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
```

### 代码执行结果

![PixPin_2024-06-01_11-24-31](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/PixPin_2024-06-01_11-24-31.gif)

## 第13次实验

### 题目

![image-20240616113532893](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240616113532893.png)

![image-20240616113553964](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240616113553964.png)

![image-20240616113613024](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240616113613024.png)

### 代码执行结果

> 只实现了阶段2......

![image-20240616113753012](https://yeyi0003.oss-cn-hangzhou.aliyuncs.com/image-20240616113753012.png)



## 上机考试

难度I - 面向对象

大概包括继承，手写排序，文件操作（很坑，我以为不会考）...

难度II - 多线程

没做，没仔细看

**实验课成绩：90**

**完结撒花~~**
