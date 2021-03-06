package m1.entity;
// Generated 10 mai 2015 22:43:48 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Location generated by hbm2java
 */
public class Location  implements java.io.Serializable {


     private Integer id;
     private String label;
     private Set users = new HashSet(0);

    public Location() {
    }

	
    public Location(String label) {
        this.label = label;
    }
    public Location(String label, Set users) {
       this.label = label;
       this.users = users;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getLabel() {
        return this.label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }




}


