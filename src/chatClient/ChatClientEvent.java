/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            this._chatClientController.connect();
        }
        
        if(e.getActionCommand().equals("send")) {
            this._chatClientController.sendMessage();
        }
        
        if(e.getActionCommand().equals("input")) {
            this._chatClientController.sendMessage();
        }
        
        if(e.getActionCommand().equals("help")){
            this._chatClientController.openHelp();
        }
    }
    
}
