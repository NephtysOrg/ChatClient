/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import java.util.ArrayList;
import java.util.List;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author rbary
 */
public class ChatClientController {
    private final ChatClientView _chatClientView;
    private ChatClientModel _chatClientModel;
    private String _userId;
    List<String> _groupnames;
    
    public ChatClientController() throws UnsupportedLookAndFeelException{
        this._groupnames = new ArrayList<>();
        this._chatClientView = new ChatClientView(this);
        this._chatClientModel = new ChatClientModel(this);
        this._userId = new String();
    }
    
    public void launch(){
        this._chatClientView.setVisible(true);
    }
    
    public void connect() throws InterruptedException{
        if(this._chatClientModel == null){
            this._chatClientModel = new ChatClientModel(this);
        }
        
        if(this._chatClientModel.connection(this._chatClientView.getUsernameTextField().getText(),new String(this._chatClientView.getPasswordField().getPassword()))){          
            this._chatClientView.GUITalkingState();
            this._chatClientView.showNotification("Login Success","Great ! You have been logged","notify");
            this._userId = this._chatClientView.getUsernameTextField().getText();
            this._chatClientModel.init(_userId);
            this._groupnames = this._chatClientModel.getUserDAO().getMemberGroupsName();

            for (String groupname : _groupnames) {
                System.out.println("Controller : "+groupname);
                this._chatClientView.addEntryToGroupList(groupname);
                this._chatClientView.addGroupTab(groupname);
            }
            
            //by default open a tab relating to the first group in the list
            this._chatClientView.getGroupsList().setSelectedIndex(0);
            Object o = this._chatClientView.getGroupsList().getModel().getElementAt(0);
            this._chatClientView.setVisibleGroupTab(o.toString());
            
        }else{       
            this._chatClientView.showNotification("Connection Error","Sorry ! the credentials are incorrect.","error");
        }
    }
    
    public void disconnect(){
        this._chatClientModel.disconnection();
        this._chatClientModel = null;
        this._chatClientView.clearChatPanel();
        this._chatClientView.GUIConnectionState();
        this._chatClientView.showNotification("Logout","You have been disconnected.\n You can open an other session","notify");
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
        this._chatClientView.openHelp();
    }
    
    public String retrieveIdentifier(){
        return this._chatClientModel.getIdentifier();
    }
}
