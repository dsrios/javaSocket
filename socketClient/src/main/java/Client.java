/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego Rios P
 */
import java.io.*;
import java.net.*;
import javax.swing.JOptionPane;

public class Client {

    /**
     * @param args the command line arguments
     */
    static Socket server;
    static DataOutputStream dataOut;
    static String[] choices = {"Input","Find","Close" };

    public static void main(String[] args) {
        // loop to read data if is close close the connection
        String answer = "Close";
        do {
            //type,account,balance
            //type,account
            openConnection();
            String response = (String) JOptionPane.showInputDialog(null, null, "Choose an Option", JOptionPane.INFORMATION_MESSAGE, null, choices, choices[0]);
            
            answer = response;
            switch (response) {
                case "Close":
                    // close socket server and Client when menu was completed
                    writeSocket(response);
                    closeConnection();
                    break;
                case "Input":
                    String account = JOptionPane.showInputDialog(null, "Write your account");
                    String balance = JOptionPane.showInputDialog(null, "Write your balance");
                    String info = response.concat(",").concat(account).concat(",").concat(balance);
                    writeSocket(info);
                    break;
                case "Find":
                    String accountF = JOptionPane.showInputDialog(null, "Write your account");
                    writeSocket(response.concat(",").concat(accountF));
                    break;
            }

        } while (!answer.equals("Close"));

    }

    public static void writeSocket(String text) {

        try {            
            // dataOut.writeUTF("diego2,1234567893,12COP");
            dataOut.writeUTF(text);
            dataOut.flush();
            dataOut.close();
        } catch (Exception e) {
        }

    }

    public static void openConnection() {
        try {
            server = new Socket("localhost", 8080);
            dataOut = new DataOutputStream(server.getOutputStream());

//            Socket s = new Socket("localhost", 8080);
//            DataOutputStream dataOut = new DataOutputStream(s.getOutputStream());
//            dataOut.writeUTF("diego2,1234567893,12COP");
//            dataOut.flush();
//            dataOut.close();
//            s.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void closeConnection() {
        try {
            server.close();
        } catch (Exception e) {
        }

    }
}
