/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

public interface IChat {

    /**
     * Connecte un utilisateur au chat. Tant que la connexion n'est pas validee,
     * aucun des autres services de l'interface ne doit etre utilisable.
     * @param pseudo le pseudo de l'utilisateur
     * @param mdp le mot de passe de l'utilisateur
     * @return true s'il existe un pseudo avec ce mot de passe
     * (la connexion est alors effective), <code>false</code> sinon
     */
    public boolean connection(String pseudo, String mdp);

    /**
     * Deconnecte l'utilisateur courant du chat.
     */
    public void disconnection();

    /**
     * Retourne l'ensemble des groupes auxquels appartient l'utilisateur courant.
     //* @return l'ensemble des groupes de l'utilisateur courant
     */
    //public String[] getGroups();

    /**
     * Retourne le prochain message non lu de la discussion d'un groupe. 
     * Reste bloque tant qu'un message n'est pas disponible.
     * @param groupe le groupe dont on veut recuperer le prochain message
     * @return le prochain message
     */
    public String getNextMessageIncomming(String groupe);

    /**
     * Envoie un message a un groupe de la part de l'utilisateur courant.
     * @param le message a envoyer
     * @param groupe le groupe a qui envoyer le message
     */
    public void sendMessage(String message, String groupe); 
}
