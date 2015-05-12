/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chatClient;


import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


/**
 *
 * @author rbary
 */
public class ChatClientView extends javax.swing.JFrame {
    public ChatClientView(ChatClientController chatClientController) throws UnsupportedLookAndFeelException {
        this._chatClientController = chatClientController;
        this.groupsListModel = new javax.swing.DefaultListModel();
        this.tabs = new ArrayList<>();
        this.helpText = "1. If you do not have an account please create a user \n" +
                        "accountfrom the web service Chat ++\n" +
                        "2. You can create , join chat groups for this service\n" +
                        "3. Inquire credentials in the respective fields and then\n"+
                        "click Login or press the enter key\n" +
                        "4. If you belong to the focus groups, a list of these groups will be displayed\n" +
                        "5. Interact with this list to access the different chat room";
        try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (ClassNotFoundException ex) {
                } catch (InstantiationException ex) {
                } catch (IllegalAccessException ex) {
                } catch (UnsupportedLookAndFeelException ex) {
        }

        initComponents();
        this.GUIConnectionState();
    }

     public JTextField getInputTextField() {
        return inputTextField;
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }

    public JTextField getUsernameTextField() {
        return usernameTextField;
    }

    public void addEntryToGroupList(String groupname){
        ((javax.swing.DefaultListModel)this.groupsList.getModel()).addElement(groupname);
    }
    
    public void addGroupTab(String groupname){
        this.tabs.add(new ChatTab(groupname));
    }
    
    public void setVisibleGroupTab(String groupname){
        this.tabs.stream().filter((tab) -> (tab.getGroupName().equals(groupname))).forEach((tab) -> {
            this.chatTabbedPane.addTab(groupname, tab);
        });
    }
    
    public boolean checkTabComponent(String groupname){
        boolean result = false;
        if (this.chatTabbedPane.indexOfTab(groupname)!=-1) {
            result = true;
        } 
        return result;
    }
  
    public JList getGroupsList() {
        return groupsList;
    }

    public JTabbedPane getChatTabbedPane() {
        return chatTabbedPane;
    }

    public ArrayList<ChatTab> getTabs() {
        return tabs;
    }

    public JButton getLoginButton() {
        return loginButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
    
    public void showNotification(String title,String notification,String notificationType){
        if(notificationType.equals("error")){
            JOptionPane.showMessageDialog(this,notification,title,JOptionPane.ERROR_MESSAGE);
        }
        
        if(notificationType.equals("notify")){
            JOptionPane.showMessageDialog(this,notification,title,JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    public void openHelp(){
        JOptionPane.showMessageDialog(this, this.helpText, "Help", JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void clearChatPanel(){
        this.chatTabbedPane.removeAll();
        this.tabs.clear();
        ((javax.swing.DefaultListModel)this.groupsList.getModel()).removeAllElements();
    }

    public void writeOutputMessage(String text,String group){
        this.tabs.stream().filter((tab) -> (tab.getGroupName().equals(group))).forEach((tab) -> {
            tab.writeOutput(text);
        });   
    }
    
    public void writeOutputMessageLocal(String text,String group){
        int nbTab= this.chatTabbedPane.getTabCount();
        for(int i=0;i<nbTab;i++){
            if(this.chatTabbedPane.getTitleAt(i).equals(group)){
                ((ChatTab)this.chatTabbedPane.getComponentAt(i)).writeOutputLocal(text); 
            }
        }
    }
    
    public void GUIConnectionState(){
        this.inputTextField.setEnabled(false);
        this.sendButton.setEnabled(false);
        this.usernameTextField.setEnabled(true);
        this.passwordField.setEnabled(true);
        this.logoutButton.setEnabled(false);
    }
    
    public void GUITalkingState(){
        this.inputTextField.setEnabled(true);
        this.sendButton.setEnabled(true);
        this.usernameTextField.setEnabled(false);
        this.passwordField.setEnabled(false);
        this.logoutButton.setEnabled(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        connexionPanel = new javax.swing.JPanel();
        usernameLabel = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        passwordLabel = new javax.swing.JLabel();
        passwordField = new javax.swing.JPasswordField(8);
        loginButton = new javax.swing.JButton();
        logoutButton = new javax.swing.JButton();
        chatPanel = new javax.swing.JPanel();
        inputTextField = new javax.swing.JTextField();
        messageLabel = new javax.swing.JLabel();
        sendButton = new javax.swing.JButton();
        chatTabbedPane = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        groupsList = new javax.swing.JList();
        jLabel1 = new javax.swing.JLabel();
        logoLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        
        //**********************************************************************
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Chat++ Client");
        setResizable(false);
        setSize(new java.awt.Dimension(745, 505));
        //**********************************************************************
        connexionPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Identification"));
        
        //**********************************************************************
        usernameLabel.setText("Username:");
        //**********************************************************************
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });
        //**********************************************************************
        passwordLabel.setText("Password:");
        //**********************************************************************
        passwordField.setText("jPasswordField1");
        passwordField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                passwordFieldActionPerformed(evt);
            }
        });
        //**********************************************************************
        loginButton.setText("Login");
        loginButton.setActionCommand("login");
        loginButton.addActionListener(new ChatClientEvent(_chatClientController));
        
        //**********************************************************************
        logoutButton.setText("Logout");
        logoutButton.setActionCommand("logout");
        logoutButton.addActionListener(new ChatClientEvent(_chatClientController));
        
        //**********************************************************************
        javax.swing.GroupLayout connexionPanelLayout = new javax.swing.GroupLayout(connexionPanel);
        connexionPanel.setLayout(connexionPanelLayout);
        connexionPanelLayout.setHorizontalGroup(
            connexionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, connexionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(usernameLabel)
                .addGap(1, 1, 1)
                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(passwordLabel)
                .addGap(2, 2, 2)
                .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(loginButton, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(logoutButton, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        connexionPanelLayout.setVerticalGroup(
            connexionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, connexionPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(connexionPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(usernameLabel)
                    .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel)
                    .addComponent(passwordField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loginButton)
                    .addComponent(logoutButton))
                .addContainerGap())
        );
        //**********************************************************************

        chatPanel.setBorder(javax.swing.BorderFactory.createTitledBorder("Talking"));
        //**********************************************************************
        inputTextField.setActionCommand("input");
        inputTextField.addActionListener(new ChatClientEvent(_chatClientController));
        //**********************************************************************
        messageLabel.setText("Message:");
        //**********************************************************************
        sendButton.setText("Send");
        sendButton.setActionCommand("send");
        sendButton.addActionListener(new ChatClientEvent(_chatClientController));
        
        //**********************************************************************
        //jList1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        groupsList.setModel(groupsListModel);
        jScrollPane1.setViewportView(groupsList);
        MouseListener mouseListener;
        mouseListener = new ChatClientMouseAdapter(this);
        groupsList.addMouseListener(mouseListener);

        //**********************************************************************
        jLabel1.setText("User Groups");
        
        //**********************************************************************
        javax.swing.GroupLayout chatPanelLayout = new javax.swing.GroupLayout(chatPanel);
        chatPanel.setLayout(chatPanelLayout);
        chatPanelLayout.setHorizontalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addComponent(messageLabel)
                .addGap(2, 2, 2)
                .addComponent(inputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(sendButton, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addComponent(chatTabbedPane, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 57, Short.MAX_VALUE))))
        );
        chatPanelLayout.setVerticalGroup(
            chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(chatPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chatTabbedPane)
                    .addGroup(chatPanelLayout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(chatPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(messageLabel)
                    .addComponent(sendButton))
                .addGap(6, 6, 6))
        );
        
        //**********************************************************************
        logoLabel.setFont(new java.awt.Font("Ubuntu", 3, 16)); // NOI18N
        logoLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/logo.png"))); // NOI18N
        logoLabel.setText("Chat ++");
        
        //**********************************************************************
        helpButton.setText("Help");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });
        
        //**********************************************************************
        jButton1.setText("Close");
        
        //**********************************************************************
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(connexionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 562, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(helpButton, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(connexionPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chatPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(helpButton)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pack();
    }// </editor-fold>                        

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        // TODO add your handling code here:
    }                                                 

    private void loginButtonActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void logoutButtonActionPerformed(java.awt.event.ActionEvent evt) {                                             
        // TODO add your handling code here:
    }                                            

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          

    private void passwordFieldActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
    }                                             

    private void inputTextFieldActionPerformed(java.awt.event.ActionEvent evt) {                                               
        // TODO add your handling code here:
    }                                              

    private void sendButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
    }                                          
    
    public static void main(String args[]) throws UnsupportedLookAndFeelException {
       ChatClientGUI myView = new ChatClientGUI();
       myView.setVisible(true);
    } 
    
    
    //other variables declaration
    // Variables declaration - do not modify
    private ChatClientController _chatClientController;                    
    private javax.swing.JPanel chatPanel;
    private javax.swing.JTabbedPane chatTabbedPane;
    private javax.swing.JPanel connexionPanel;
    private javax.swing.JButton helpButton;
    private javax.swing.JTextField inputTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList groupsList;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton loginButton;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JButton logoutButton;
    private javax.swing.JLabel messageLabel;
    private javax.swing.JPasswordField passwordField;
    private javax.swing.JLabel passwordLabel;
    private javax.swing.JButton sendButton;
    private javax.swing.JLabel usernameLabel;
    private javax.swing.JTextField usernameTextField;
    
    private javax.swing.AbstractListModel groupsListModel;
    private ArrayList<ChatTab> tabs;
    private String helpText;
    // End of variables declaration                   
}
