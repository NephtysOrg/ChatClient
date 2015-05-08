/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import communication.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import m1.entity.Group;
import service.IChat;


/**
 *
 * @author rbary
 */
public class ChatClientModel extends Thread implements IChat{
    private final ChatClientController _chatClientController;
    private Chat _chat;
    private String _identifier;
    private final String[] _groups;
    private HashMap<String, LinkedList<Message> > _waitingMessage;
    
    public ChatClientModel(ChatClientController chatClientController){
        this._chatClientController = chatClientController;
        _groups = new String[3];
        _groups[0]="M1TI pau";
        _groups[1]="FreeNode";
        _groups[2]="NephtysOrg";
    }
    
    public void init(String identifier){
        this._identifier = identifier;
        this.start();
    }
    
    @Override
    public void run(){
        this._chat = new Chat();
        this._chat.init(); 
        /*this._groupes = getGroupes();*/
        this._waitingMessage = new HashMap<>();
        for (String unGroupe : this._groups) {
            this._waitingMessage.put(unGroupe, new LinkedList<Message>());
        }
        
        while (true) {
            Message msg = this._chat.broadcastService.synchDeliver();
            if( this._waitingMessage.containsKey(msg.getRecepientGroup()) ){
                this._waitingMessage.get(msg.getRecepientGroup()).add(msg);
            }
            
            for (String groupe : this._groups) {                                                
                String nextMsg = getNextMessageIncomming(groupe);
                if (!nextMsg.equals("")) {
                    this._chatClientController.writeOutput(nextMsg, groupe);
                }
            }          
        }
    }

    public String getIdentifier() {
        return this._identifier;
    }
    
    @Override
    public boolean connection(String login, String password){
        return true;
    }
    
    @Override
    public void disconnection(){
        for(String aGroup : this._groups){
            sendMessage("A Bientôt !", aGroup);
        }
        this._chat.services.disconnect();
    }

    /*public String[] getGroups() {
        ArrayList<String> groups = new ArrayList<> ();
        //Retourner la liste des groupes auquel l'identifiant courant appartient dans la bd
    }*/
   
    @Override
    public String getNextMessageIncomming(String group){
        Message msg = (Message) this._waitingMessage.get(group).poll();
        if (msg == null) {
            return "";
        } else {
            return "["+(String)msg.getSender()+"] "+msg.getData();
        }
    }
    
    @Override
    public void sendMessage(String text, String group) {
        this._chat.sendMessage(text,this._identifier,group);
    }
}
