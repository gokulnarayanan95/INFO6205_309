package geneticalgorithm;

import geneticalgorithm.domain.Fixture;
import geneticalgorithm.domain.Schedule;

/**
 * This class contains all the implementation of the Genetic Algorithm
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/10/2018
 */
public class Algorithm {

    /**
     * A constant variable that defines the rate at which the crossover happens
     */
    private static final double UNIFORM_RATE = 0.5;

    /**
     * A constant variable that defines the probability of Mutation
     */
    private static final double MUTATION_RATE = 0.015;

    /**
     * A constant variable that defines the size of the Tournament
     */
    private static final int TOURNAMENT_SIZE = 5;

    /**
     * A variable to hold the data set
     */
    private Data data;

    /**
     * Parameterized constructor for creating an instance for the Algorithm class
     *
     * @param data The data set over which the algorithm is going to be run
     */
    public Algorithm(Data data) {
        this.data = data;
        this.data.initializeData();
    }

    /**
     * This function performs the evolution on a selected population
     *
     * @param pop The population over which evolution has to be done
     * @return The evolved population
     */
    private Population evolve(Population pop) {
        Population newPopulation = new Population(pop.size(), data, false);
        for (int i = 0; i < pop.size(); i++) {
            Schedule s1 = tournamentSelection(pop);
            Schedule s2 = tournamentSelection(pop);
            Schedule newSchedule = crossover(s1, s2);
            newPopulation.saveSchedule(newSchedule);
        }
        for (Schedule s : newPopulation.getSchedules()) {
            mutation(s);
        }
        return newPopulation;
    }

    /**
     * This function performs crossover operation. In this case, we have implemented "Sexual Reproduction".
     *
     * @param s1 The first parent solution set
     * @param s2 The second parent solution set
     * @return The next generation solution set
     */
    private Schedule crossover(Schedule s1, Schedule s2) {
        Schedule newSol = new Schedule(data);
        // Loop through genes
        for (int i = 0; i < s1.size(); i++) {
            // Crossover
            if (Math.random() <= UNIFORM_RATE) {
                newSol.getFixures().set(i, s1.getFixures().get(i));
            } else {
                newSol.getFixures().set(i, s2.getFixures().get(i));
            }
        }
        return newSol;
    }

    /**
     * This function mutates the chromosome (A single solution set)
     *
     * @param s1 The solution set that has to be mutated
     */
    private void mutation(Schedule s1) {
        Schedule tempSchedule = new Schedule(data);
        for (int i = 0; i < s1.size(); i++) {
            if (Math.random() <= MUTATION_RATE) {
                // Create random gene
                Fixture fix = tempSchedule.getFixures().get(i);
                s1.getFixures().set(i, fix);
            }
        }
    }

    /**
     * This function selects the children which are fit for the next iteration
     *
     * @param pop The population in which the good fits has to be found
     * @return The fittest solution
     */
    private Schedule tournamentSelection(Population pop) {
        Population tournament = new Population(TOURNAMENT_SIZE, data, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < TOURNAMENT_SIZE; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.getSchedules().set(i, pop.getSchedules().get(randomId));
        }
        // Get the fittest
        return tournament.getFittest();
    }
}
