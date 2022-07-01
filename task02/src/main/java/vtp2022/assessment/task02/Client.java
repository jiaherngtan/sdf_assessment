package vtp2022.assessment.task02;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) {
        System.out.println("Client Application");
        try {
            // exchange a series of messages with a server
            // running at task02.chuklee.com on port 80 or
            // running at 68.183.239.26 on port 80

            // Socket socket = new Socket("task02.chuklee.com", 80);
            Socket socket = new Socket("68.183.239.26", 80);

            // get the OutputStream first
            OutputStream os = socket.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(os);

            // followed by InputStream
            InputStream is = socket.getInputStream();
            ObjectInputStream ois = new ObjectInputStream(is);

            // accept the request from server
            String reqFromServer = ois.readUTF();
            System.out.printf("Request from server: %s\n", reqFromServer);

            // split the request from server by request ID and contents
            String[] reqFromServerArr = reqFromServer.split(" ");
            // request ID
            String reqID = reqFromServerArr[0];
            // contents (list of integers) in String array
            String[] numList = reqFromServerArr[1].split(",");

            // calculate the average for the list of integers
            float total = 0;
            float average = 0;
            for (int i = 0; i < numList.length; i++) {
                total = total + Float.parseFloat(numList[i]);
            }
            average = total / numList.length;
            System.out.printf("%.2f\n", average);

            // write the answers back to the server
            oos.writeUTF(reqID);
            oos.writeUTF("Tan Jia Herng");
            oos.writeUTF("jherngt@gmail.com");
            oos.writeFloat(average);
            oos.flush();

            // get the response status
            boolean res = ois.readBoolean();
            try {
                if (res)
                    System.out.println("SUCCESS");
            } catch (Exception e) {
                System.out.println("FAILED");
                System.err.println(e);
            }

            is.close();
            os.close();
            socket.close();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
