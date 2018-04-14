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
public class Location {
    String locationName;
    

      public Location(String locationname) {
        this.locationName=locationname;
    }

    public Location() {
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
    
    
}
