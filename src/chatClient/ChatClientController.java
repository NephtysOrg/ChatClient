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
        add("M1TI pau");
        add("FreeNode");
        add("NephtysOrg");
    }};
    
    private final String userId;
    
    
    public ChatClientController(){
        this._chatClientView = new ChatClientView(this);
        this._chatClientModel = new ChatClientModel(this);
        userId = "ftoulouz"; 
    }
    
    public void launch(){
        this._chatClientView.setVisible(true);
    }
    
    public void connect(){
        if (true){ //Supposed to be connected
            System.out.println("Login reussi");
            this._chatClientView.setUserId(userId);
            //recuperer la liste des groupes au quel l'user appartient
            //descactiver les champs de connection username, password et le boutton login (excepté le bouton logout)
            //activer la partie chat du jFrame
            
            this._chatClientModel.init(this._chatClientView.getUserId());
            Iterator it = grouplist.iterator();
            while(it.hasNext()){         
                this._chatClientView.addEntryToGroupList((String)it.next());
            }
            
            //by default open a tab relating to the first group in the list
            this._chatClientView.getGroupsList().setSelectedIndex(0);
            Object o = this._chatClientView.getGroupsList().getModel().getElementAt(0);
            this._chatClientView.addGroupTab(o.toString());
            
        } else {  //Supposed to be not connected
            System.out.println("you have been disconnect");
        }
    }
    
    public void disconnect(){
        //this._chatClientModel.discconnect();
        System.exit(0);
    }
    
    public void sendMessage(){
        int selectedIndex = this._chatClientView.getChatTabbedPane().getSelectedIndex();
        String recipientGroup = this._chatClientView.getChatTabbedPane().getTitleAt(selectedIndex);
        String textToSend = this._chatClientView.getInputTextField().getText().trim();
        this._chatClientModel.sendMessage(textToSend,recipientGroup);
        this._chatClientView.getInputTextField().setText("");
    }
    
    public void writeOutput(String text,String group){
        this._chatClientView.writeOutputMessage(text,group);
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
