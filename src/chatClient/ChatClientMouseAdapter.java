/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JList;

/**
 *
 * @author rbary
 */
public class ChatClientMouseAdapter extends MouseAdapter {
    private final ChatClientView _chatClientView;
    
    public ChatClientMouseAdapter(ChatClientView chatClientView){
        this._chatClientView = chatClientView;
    }
    
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        JList theList = (JList) mouseEvent.getSource();
        if (mouseEvent.getClickCount() == 2) {
            int index = theList.locationToIndex(mouseEvent.getPoint());
            if (index >= 0) {
                Object o = theList.getModel().getElementAt(index);
                //il faudra verifier si l'onglet n'est pas deja ajoute
                this._chatClientView.addGroupTab(o.toString());
                //System.out.println("Double-clicked on: " + o.toString());
                        
            }
        }   
    }
}
