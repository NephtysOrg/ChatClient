/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import m1.entity.Group;
import m1.entity.User;

/**
 *
 * @author rbary
 */

public interface UserDAO {
    public boolean connection(String login, String password);
    public User getUser();
    public List<Group> getMemberGroups();
    public List<String> getMemberGroupsName();
}  
