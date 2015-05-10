/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;

import communication.Message;
import dao.UserDAO;
import dao.UserDAOImpl;
import dao.UserGroupDAO;
import dao.UserGroupDAOImpl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import m1.entity.Group;
import m1.entity.User;
import m1.entity.UserGroup;
import service.IChat;



/**
 *
 * @author rbary
 */
public class ChatClientModel extends Thread implements IChat{
    private final ChatClientController _chatClientController;
    private Chat _chat;
    private String _identifier;
    private ArrayList<String> _groups;
    private final UserDAO _userDAO;
    private final UserGroupDAO _userGroupDAO;
    private HashMap<String, LinkedList<Message> > _waitingMessage;
    
    public ChatClientModel(ChatClientController chatClientController){
        this._chatClientController = chatClientController;
        this._userDAO = new UserDAOImpl();
        this._userGroupDAO = new UserGroupDAOImpl();
        _groups = new ArrayList<>();
    }
    
    public void init(String identifier){
        this._identifier = identifier;
        this.start();
    }
    
    @Override
    public void run(){
        this._chat = new Chat();
        this._chat.init(); 
        this._groups = getGroupNames();
        this._waitingMessage = new HashMap<>();
        for (String aGroup : this._groups) {
            this._waitingMessage.put(aGroup, new LinkedList<Message>());
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
        return _userDAO.connection(login, password);
    }
    
    @Override
    public void disconnection(){
        for(String aGroup : this._groups){
            sendMessage("A Bientôt !", aGroup);
        }
        this._chat.services.disconnect();
    }

    public ArrayList<String> getGroupNames() { //Means the name of groups and not all the entity group
        ArrayList<String> groupNames = new ArrayList<>();
        try {
            //Iterator it = (Iterator) this.getMemberGroups(getUserFromUserDaoImpl());
            Iterator it = (Iterator) this.getUserFromUserDaoImpl().getGroups();
            while (it.hasNext()){
                groupNames.add(((Group)it.next()).getName());
            }
        } catch (Exception e) {
            System.out.println(e);
        }    
        return groupNames;
    }
    
   
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
    
    public User getUserFromUserDaoImpl(){
        return this._userDAO.getUser();
    }
    
    public List<Group> getMemberGroups(User user) {
        List <UserGroup> listUserGroup = this._userGroupDAO.listUserGroups();
        List<Group> groups = new ArrayList<>();
        for (UserGroup ug : listUserGroup) {
            if(!ug.getGroup().getUser().getId().equals(user.getId()) && ug.getUser().getId().equals(user.getId()) && ug.getSubscribed()== 0 && ug.getInvited()== 0){
                groups.add(ug.getGroup());
            }
        }
        return groups;
    }

    public ArrayList<String> getGroups() {
        return _groups;
    }

    public void setGroups(ArrayList<String> _groups) {
        this._groups = _groups;
    }
}
