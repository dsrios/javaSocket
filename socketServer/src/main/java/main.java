/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Diego Rios P
 */
import com.poli.socketserver.Server;
import javax.swing.JOptionPane;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import static java.lang.Thread.sleep;
import java.util.ArrayList;

public class main {
    public static String path = "./datos.txt";
    public static Boolean flag = true;
    public static void main(String[] args) {        
        JOptionPane.showMessageDialog(null, "Socket Initiallized");
        crearFile(); // created file from 0 and insert a testing value
        Server s = new Server();
        s.createServer();
        System.out.println("This is the balance for account "+readFile("1234567890")); // testing value inserted
        
       
        while (flag) {
            String res = s.readSocketServer();
            String[] split = res.split(",");
            try {
               if(res.equals("Close") || flag == false) {
                   System.out.println("Closing server ...");
                   s.writeSocketServer("Server closed ...");
                   sleep(1000);
                   flag = false;
                   s.closeServer();  
               } else if (split[0].equals("Input")) {                   
                    s.writeSocketServer( writeFile(split[1], split[2]));
               } else if (split[0].equals("Find")) {
                   s.writeSocketServer("\nFinding ...\n"+readFile(split[1]));
               }                              
            } catch (Exception e) {
            }
        }
        System.out.println("Ended");
        
    }

    public static void crearFile() {
    // Crear txt file
        try {
            
            String contenido = "1234567890,100000";
            File file = new File(path);
            // Si el archivo no existe es creado
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, false);
            // write inside the file
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(contenido);
            bw.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Error", 0);
            // e.printStackTrace();
        }
    }
    
    public static String writeFile(String accountNumber, String totalValue) {
        String valuesToInsert = "\n".concat(accountNumber.concat(",").concat(totalValue));
        try {
            FileWriter fw = new FileWriter(path, true);
            // write inside the file
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(valuesToInsert);
            bw.close();
            return "OK";
        } catch (Exception e) {
            return "NO-OK";
        }       
    }
    
    public static String readFile ( String nroCuenta ){
         BufferedReader reader = null;
         // ArrayList<String> lines = new ArrayList<String>();
         String valueFound = "Not found";
        try {
           String currentLine;
           reader = new BufferedReader(new FileReader(path));
           
            while( (currentLine = reader.readLine()) != null ) {
                // lines.add(currentLine);
                String[] split = currentLine.split(",");
                System.out.println(currentLine);               
                if( split[0].equals(nroCuenta) ) {
                    // if the line is equal to nroCuenta wil be return the value = 1
                    valueFound = split[1];
                }              
            }
        return valueFound;   
        } catch (Exception e) {
            return "Not found";
        }
    }
    
}
