/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author rbary
 */
public class ChatClientController {
    private final ChatClientView _chatClientView;
    private final ChatClientModel _chatClientModel;
    
    /*en dur */
    ArrayList<String> grouplist = new ArrayList<String>() {{
        add("G1");
        add("G2");
        add("G3");
    }};
    
    private final String identifier;
    
    
    public ChatClientController(){
        this._chatClientView = new ChatClientView(this);
        this._chatClientModel = new ChatClientModel(this);
        identifier = "cfollet"; 
    }
    
    public void launch(){
        this._chatClientView.setVisible(true);
    }
    
    public void connect(){
        if (true){ //Supposed to be connected
            System.out.println("Login reussi");
            this._chatClientView.setUserId(identifier);
            //recuperer la liste des groupes au quel l'user appartient
            //descactiver les champs de connection username, password et le boutton login (excepté le bouton logout)
            //activer la partie chat du jFrame
            
            this._chatClientModel.init(this._chatClientView.getUserId());
            Iterator it = grouplist.iterator();
            while(it.hasNext()){
                //this._chatClientView.addGroupTab((String)it.next());
                this._chatClientView.addEntryToGroupList((String)it.next());
            }
            
        } else {  //Supposed to be not connected
            System.out.println("you have been disconnect");
        }
    }
    
    public void disconnect(){
        //this._chatClientModel.discconnect();
        System.exit(0);
    }
    
    public void sendMessage(){
        //this._chatClientModel.sendMessage(this._chatClientView.getInputTextField().getText(),);
    }
    
    public void openHelp(){
        System.out.println("Open the help pane"); 
    }
    
    public String retrieveIdentifier(){
        return this._chatClientModel.getIdentifier();
    }
    
    public void newTabChat(){
        String selectedItem = (String)(this._chatClientView.getGroupsList().getSelectedValue());
        this._chatClientView.addGroupTab(selectedItem);
    }
}
