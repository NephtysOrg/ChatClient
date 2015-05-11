package m1.entity;
// Generated 10 mai 2015 22:43:48 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * UserGroup generated by hbm2java
 */
public class UserGroup  implements java.io.Serializable {


     private UserGroupId id;
     private Group group;
     private User user;
     private byte invited;
     private byte subscribed;
     private Date memberSince;

    public UserGroup() {
    }

    public UserGroup(UserGroupId id, Group group, User user, byte invited, byte subscribed, Date memberSince) {
       this.id = id;
       this.group = group;
       this.user = user;
       this.invited = invited;
       this.subscribed = subscribed;
       this.memberSince = memberSince;
    }
   
    public UserGroupId getId() {
        return this.id;
    }
    
    public void setId(UserGroupId id) {
        this.id = id;
    }
    public Group getGroup() {
        return this.group;
    }
    
    public void setGroup(Group group) {
        this.group = group;
    }
    public User getUser() {
        return this.user;
    }
    
    public void setUser(User user) {
        this.user = user;
    }
    public byte getInvited() {
        return this.invited;
    }
    
    public void setInvited(byte invited) {
        this.invited = invited;
    }
    public byte getSubscribed() {
        return this.subscribed;
    }
    
    public void setSubscribed(byte subscribed) {
        this.subscribed = subscribed;
    }
    public Date getMemberSince() {
        return this.memberSince;
    }
    
    public void setMemberSince(Date memberSince) {
        this.memberSince = memberSince;
    }




}


