package niss.net;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class KeyGeneration {
	public static void main(String[] args) {
		// 生成随机密钥并保存到key.txt
        try {
			generateAndSaveKey("files/key.txt");
			
			System.out.println("密钥生成完毕！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// 生成随机128位密钥并保存到文件
    public static void generateAndSaveKey(String filename) throws IOException {
        PrintWriter keyWriter = new PrintWriter(filename);
        Random random = new Random();
        for (int i = 0; i < 128; i++) {
            int randomChar = random.nextInt(4);
            char ch;
            switch (randomChar) {
                case 0:
                    ch = (char) ('0' + random.nextInt(10)); // 数字
                    break;
                case 1:
                    ch = (char) ('A' + random.nextInt(26)); // 大写字母
                    break;
                case 2:
                    ch = (char) ('a' + random.nextInt(26)); // 小写字母
                    break;
                default:
                    ch = (char) (random.nextInt(94) + 33); // 其他字符
            }
            keyWriter.print(ch);
        }
        keyWriter.close();
    }
}
