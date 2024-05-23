package niss.net;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class FileEncryption {
	public static void main(String[] args) {
        try {
            // 加密文件并保存到secretFile.txt
            encryptFile("files/testFile.txt", "files/secretFile.txt", "files/key.txt");

            System.out.println("文件加密完成！");
        } catch (IOException e) {
            System.out.println("出现IO异常：" + e.getMessage());
            e.printStackTrace();
        }
    }

    

    // 加密文件
    public static void encryptFile(String inputFile, String outputFile, String keyFile) throws IOException {
        BufferedReader keyReader = new BufferedReader(new FileReader(keyFile));
        int[] key = new int[128];
        int i = 0;
        int ch;
        while ((ch = keyReader.read()) != -1) {
            key[i++] = ch;
        }
        keyReader.close();

        BufferedReader reader = new BufferedReader(new FileReader(inputFile));
        PrintWriter writer = new PrintWriter(outputFile);

        i = 0;
        while ((ch = reader.read()) != -1) {
            int encryptedChar;
            if (Character.isDigit(ch)) {
                encryptedChar = ch + 1 + key[i % 128];
            } else if (Character.isLowerCase(ch)) {
                encryptedChar = ch + 2 + key[i % 128];
            } else if (Character.isUpperCase(ch)) {
                encryptedChar = ch + 3 + key[i % 128];
            } else {
                encryptedChar = ch + 4 + key[i % 128];
            }
            writer.print((char)encryptedChar);
            i++;
        }

        reader.close();
        writer.close();
    }
}
