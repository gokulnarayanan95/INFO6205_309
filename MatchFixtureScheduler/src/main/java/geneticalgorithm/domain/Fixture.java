package geneticalgorithm.domain;

import java.util.Date;

/**
 * Data structure for storing Fixtures
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/10/2018
 */
public class Fixture {

    /**
     * Member variable representing the details of a match fixture
     */
    private Date date;
    private Team homeTeam;
    private Team awayTeam;
    private Location location;

    /**
     * Creates an instance of a fixture with default values
     */
    public Fixture() {
        date = new Date();
        homeTeam = new Team();
        awayTeam = new Team();
        location = new Location();
    }

    /**
     * Creates an instance of a fixture with values passed
     *
     * @param date     Date of the match fixture
     * @param homeTeam The home team playing in the match
     * @param awayTeam The another team playing in the match
     * @param location The location of the match
     */
    public Fixture(Date date, Team homeTeam, Team awayTeam, Location location) {
        this.date = date;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.location = location;
    }

    /**
     * Getter method of the {@code Date date}
     *
     * @return The value of the variable date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Getter method of the {@code Team homeTeam}
     *
     * @return The value of the variable homeTeam
     */
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * Getter method of the {@code Team awayTeam}
     *
     * @return The value of the variable awayTeam
     */
    public Team getAwayTeam() {
        return awayTeam;
    }

    /**
     * Getter method of the {@code Location location}
     *
     * @return The value of the variable location
     */
    public Location getLocation() {
        return location;
    }

}
