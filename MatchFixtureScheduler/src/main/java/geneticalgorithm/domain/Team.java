/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm.domain;

/**
 *
 * @author naray
 */
public class Team {
    String name;
    Location homeGround;

    public Team() {
    }

    public Team(String name, Location homeground) {
       this.homeGround=homeground;
       this.name=name;
    }

    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getHomeGround() {
        return homeGround;
    }

    public void setHomeGround(Location homeGround) {
        this.homeGround = homeGround;
    }
    
    
}
