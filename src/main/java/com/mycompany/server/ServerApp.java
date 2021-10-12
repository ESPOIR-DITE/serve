/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.server;


import com.mycompany.server.domain.ServerToken;
import com.mycompany.server.controller.Controller;
import com.mycompany.server.factory.ServerTokenFactory;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 *
 * @author Marilyn
 */
//@SpringBootApplication
public class ServerApp {
     // Server socket
    private ServerSocket listener;
    
    // Client connection
    private Socket client;
    
    /** Creates a new instance of ServerApp */
    public ServerApp()
    {
        // Create server socket
        try {
            listener = new ServerSocket(12345, 10);
        }
        catch (IOException ioe)
        {
          System.out.println("IO Exception: " + ioe.getMessage());
        }
    }
    
    public void listen()
    {
        // Start listening for client connections
        try {
          System.out.println("Server is listening");
          client = listener.accept();  
          System.out.println("Now moving onto processClient");
          processClient();
        }
        catch(IOException ioe)
        {
            System.out.println("IO Exception: " + ioe.getMessage());
        }
    }
    
    public void processClient() {
        // Communicate with the client
        String result = null;
        // First step: initiate channels
       try {
            ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
            out.flush();
            ObjectInputStream in = new ObjectInputStream(client.getInputStream());
            String msg= null;
           Controller controller = new Controller();
            
            do{
            // Step 2: communicate
                try{
                    msg = (String)in.readObject();
                    ServerToken response = ServerTokenFactory.getServerToken(msg);
                    result =  controller.getServerToken(response);
                    System.out.println(response.getDate());
                    out.writeObject(result);
                    out.flush();
                }catch (IOException ioe) {
//                    System.out.println("IO Exception: " + ioe.getMessage());
                }
            }while(!msg.equals("TERMINATE"));
            // Step 3:close down
            out.close();
            in.close();
            client.close();        
        }
        catch (IOException ioe)
        {
            System.out.println("IO Exception: " + ioe.getMessage());
        }
        catch (ClassNotFoundException cnfe)
        {
            System.out.println("Class not found: " + cnfe.getMessage());
        }
    }
  
    public static void main(String[] args)
    {
        // Create application
        ServerApp server = new ServerApp();
        
        // Start waiting for connections
        server.listen();
    }    
}
