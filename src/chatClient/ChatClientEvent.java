/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author rbary
 */
public class ChatClientEvent implements ActionListener{
    private final ChatClientController _chatClientController;
    
    public ChatClientEvent(ChatClientController chatClientController){
        this._chatClientController = chatClientController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("login")){
            try {
                this._chatClientController.connect();
            } catch (InterruptedException ex) {
                Logger.getLogger(ChatClientEvent.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        if(e.getActionCommand().equals("send")) {
            this._chatClientController.sendMessage();
        }
        
        if(e.getActionCommand().equals("logout")){
            this._chatClientController.disconnect();
        }
        
        if(e.getActionCommand().equals("input")) {
            this._chatClientController.sendMessage();
        }
        
        if(e.getActionCommand().equals("help")){
            this._chatClientController.openHelp();
        }
    }
    
}
