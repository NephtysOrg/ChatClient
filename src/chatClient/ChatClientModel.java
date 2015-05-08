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
    private String[] _groups;
    private HashMap<String, LinkedList<Message> > _waitingMessages;
    
    public ChatClientModel(ChatClientController chatClientController){
        this._chatClientController = chatClientController;
    }
    
    public void init(String identifier){
        this._identifier = identifier;
        this.start();
    }
    
    @Override
    public void run(){
        this._chat = new Chat();
        this._chat.init();
        this._chat.start();         
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
        //Retourner la liste des groupes auquel l'identifiant courant appartient
    }*/
   
    @Override
    public String getNextMessageIncomming(String group){
        Message msg = (Message) this._waitingMessages.get(group).poll();
        if (msg == null) {
            return "";
        } else {
            return (String)msg.getSender()+": "+msg.getData();
        }
    }
    
    @Override
    public void sendMessage(String message, String group) {
        this._chat.sendMessage(message,this._identifier,group);
    }
    
    
}
