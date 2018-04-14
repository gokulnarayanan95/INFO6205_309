package geneticalgorithm.domain;

/**
 * Data structure for storing Teams
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/10/2018
 */
public class Team {
    /**
     * Member variable representing the Team. Their name and their home-ground
     */
    String name;
    Location homeGround;

    /**
     * Creates an instance of a Team with default values
     */
    public Team() {
        name = "";
        homeGround = new Location();
    }

    /**
     * Creates an instance of a team with the passed parameters
     *
     * @param name       Name of the team
     * @param homeGround Home ground of the team
     */
    public Team(String name, Location homeGround) {
        this.homeGround = homeGround;
        this.name = name;
    }

    /**
     * Getter method for {@code String name}
     *
     * @return The name of the team
     */
    public String getName() {
        return name;
    }

    /**
     * Getter method for {@code Location homeGround}
     *
     * @return The home ground of the team
     */
    public Location getHomeGround() {
        return homeGround;
    }
}
