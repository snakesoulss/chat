package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 9178);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);
            String name;
            Thread thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            System.out.println(in.readUTF());
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            thread.start();
            System.out.println("Введите свое имя");
            name = scanner.nextLine();
            System.out.println("Теперь Вы можете писать сообщения");
            while (true){
                String message = scanner.nextLine();
                out.writeUTF(name + ": " + message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
