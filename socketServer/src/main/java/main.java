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
        crearFile();
        // writeFile("1234567891", "9");
        // writeFile("1234567892", "8");
        // writeFile("1234567893", "7");
        // writeFile("1234567892", "6");
        Server s = new Server();
        s.createServer();
        System.out.println("This is the balance for account "+readFile("1234567890"));
        
        // int counter = 0;
        while (flag) {
            // solo permitir 10 lecturas maximo
            /*if (counter == 9){
                flag = false;
            }*/
            String res = s.readSocketServer();
            String[] split = res.split(",");
            try {
               if(res.equals("Close") || flag == false) {
                   System.out.println("Closing server ...");
                   // System.out.println("Counter "+counter);
                   sleep(1000);
                   flag = false;
                   s.closeServer();  
               } else if (split[0].equals("Input")) {
                    writeFile(split[1], split[2]);              
               } else if (split[0].equals("Find")) {
                   System.out.println("\nFinding ...\n"+readFile(split[1]));
               }                              
            } catch (Exception e) {
            }
        }
        System.out.println("Ended");
        /*
        try {
           sleep(10000); 
           System.out.println("Closing server ...");
           flag = false;
           s.closeServer();
        } catch (Exception e) {
            s.closeServer();
        } 
*/
        
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
