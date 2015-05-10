/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;
import m1.entity.Group;


/**
 *
 * @author rbary
 */
public class ChatClientController {
    private final ChatClientView _chatClientView;
    private final ChatClientModel _chatClientModel;
    private String _userId;
    
    public ChatClientController(){
        this._chatClientView = new ChatClientView(this);
        this._chatClientModel = new ChatClientModel(this);
        this._userId = new String();
    }
    
    public void launch(){
        this._chatClientView.setVisible(true);
    }
    
    public void connect(){
        if(this._chatClientModel.connection(this._chatClientView.getUsernameTextField().getText(),new String(this._chatClientView.getPasswordField().getPassword()))){
            this._userId = this._chatClientView.getUsernameTextField().getText();
            System.out.println("Je me suis connecté");
            this._chatClientModel.init(_userId);
            ArrayList<String> groups = this._chatClientModel.getGroups();
            
            
        
            /*//desactiver les champs de connection username, password et le boutton login (excepté le bouton logout)
            //activer la partie chat du jFrame          
            Iterator it = groupnames.iterator();
            while(it.hasNext()){
                String groupname = (String) it.next();
                System.out.println(groupname);
                this._chatClientView.addEntryToGroupList(groupname);
                this._chatClientView.addGroupTab(groupname);
            }
            
            //by default open a tab relating to the first group in the list
            this._chatClientView.getGroupsList().setSelectedIndex(0);
            Object o = this._chatClientView.getGroupsList().getModel().getElementAt(0);
            this._chatClientView.setVisibleGroupTab(o.toString());*/
            
        }else{
            System.out.println("Identifiants incorrects");
            JOptionPane.showMessageDialog(_chatClientView,
                    "Erreur de connexion",
                    "Les informations saisies sont erronées",
                    JOptionPane.ERROR_MESSAGE);
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
        this._chatClientView.writeOutputMessageLocal(textToSend,recipientGroup);
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
}
