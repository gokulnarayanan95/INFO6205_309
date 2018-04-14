package geneticalgorithm.domain;


import geneticalgorithm.domain.Location;
import geneticalgorithm.domain.Team;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Gokul Anantha Narayanan
 */
public class Fixture {

    private Date date;
    private Team homeTeam;
    private Team awayTeam;
    private Location location;
   
    public Fixture() {
        date = new Date();
        homeTeam = new Team();
        awayTeam = new Team();
        location = new Location();  
    }

    public Fixture(Date date, Team homeTeam, Team awayTeam, Location location) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
    }
    
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Team getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

}
