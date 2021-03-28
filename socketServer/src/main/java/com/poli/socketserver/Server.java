    
/*
    * To change this license header, choose License Headers in Project Properties.
    * To change this template file, choose Tools | Templates
    * and open the template in the editor.
 */

/**
 *
 * @author Diego Rios P
 */
package com.poli.socketserver;
import java.io.*;
import java.net.*;


public class Server {
    public static ServerSocket socketPort;

    public static void constructor() {
        // init server
        System.out.println("Creating server ....");
        // createServer();
    }

    public static void createServer() {
        try {
            socketPort = new ServerSocket(8080);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    public static void closeServer() {        
        try {
            socketPort.close();
        } catch (Exception e) {
            System.out.println("Error closing socket");
        }        
    }
    
    public static String readSocketServer(){
        String str = "";
        try {
            Socket s = socketPort.accept();//establishes connection   
            DataInputStream data = new DataInputStream(s.getInputStream());
            str = (String) data.readUTF();
            System.out.println("message= " + str);
        } catch (Exception e) {
            System.out.println("Error reading socket");
        }
         
        return str;
    }
    
    public static void writeSocketServer(String str){                
        try {
            Socket s = socketPort.accept();//establishes connection   
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(str);
            System.out.println("Write socket: "+str);
        } catch (Exception e) {
            System.out.println("Error writing socket");
        }
    }

}
