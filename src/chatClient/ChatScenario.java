/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import communication.CommunicationException;
import service.id.IdentificationServer;

/**
 *
 * @author rbary
 */
public class ChatScenario {
     public static void main(String args[]) {
        //On lance un serveur
          try {
            IdentificationServer server = new IdentificationServer();
        } catch (CommunicationException ex) {
            System.err.println("[ERROR] while launching identification server: " + ex);
        }
        
        //Lancement d'un premier client
        ChatClientController chatClientController1 = new ChatClientController();  
        chatClientController1.launch();
        
        //Lancement d'un deuxieme client
        ChatClientController chatClientController2 = new ChatClientController();
        chatClientController2.launch();
    }   
}
