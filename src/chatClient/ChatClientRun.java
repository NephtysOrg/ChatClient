/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author rbary
 */
public class ChatClientRun {
    public static void main(String args[]) throws UnsupportedLookAndFeelException {  
        ChatClientController chatClientController1 = new ChatClientController();  
        chatClientController1.launch();
    }
}
