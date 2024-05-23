package niss.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileDecryption {
	public static void main(String[] args) {
        try {
            // 读取密钥
            int[] key = readKey("files/key.txt");

            // 解密文件
            decryptFile("files/secretFile.txt", "files/decryptedFile.txt", key);

            System.out.println("文件解密完成！");
        } catch (IOException e) {
            System.out.println("出现IO异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    // 读取密钥
    public static int[] readKey(String filename) throws IOException {
        BufferedReader keyReader = new BufferedReader(new FileReader(filename));
        int[] key = new int[128];
        int i = 0;
        int ch;
        while ((ch = keyReader.read()) != -1) {
            key[i++] = ch;
        }
        keyReader.close();
        return key;
    }

    // 解密文件
    public static void decryptFile(String inputFile, String outputFile, int[] key) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        PrintWriter writer = new PrintWriter(outputFile);

        int i = 0;
        int ch;
        while ((ch = reader.read()) != -1) {
            int decryptedChar = ch - key[i % 128];
            for (int j = 1; j <= 4; j++) {
                if (isValidDecryption(decryptedChar, j)) {
                    writer.print((char) (decryptedChar - j));
                    break;
                }
            }
            i++;
        }

        reader.close();
        writer.close();
    }

    // 判断是否是有效的解密
    private static boolean isValidDecryption(int ch, int j) {
        switch (j) {
            case 1:
                return Character.isDigit(ch);
            case 2:
                return Character.isLowerCase(ch);
            case 3:
                return Character.isUpperCase(ch);
            case 4:
                return !Character.isDigit(ch) && !Character.isLowerCase(ch) && !Character.isUpperCase(ch);
            default:
                return false;
        }
    }
}
