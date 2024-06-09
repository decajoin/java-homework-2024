package niss.net;

import java.io.IOException;
import java.io.ObjectInputStream;

public class ServerGetThread extends Thread {
    private ObjectInputStream in;
    private ServerFrame frame;

    public ServerGetThread(ObjectInputStream in, ServerFrame frame) {
        this.in = in;
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
        	// 相当于一个监听程序，while(true)一直监听有没有收到信息
            while (true) {
                Information info = (Information) in.readObject();
                frame.displayMessage(info);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
