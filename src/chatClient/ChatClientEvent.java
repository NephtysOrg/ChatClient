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
    ChatClientController _chatClientController;
    
    public ChatClientEvent(ChatClientController chatClientController){
        this._chatClientController = chatClientController;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
