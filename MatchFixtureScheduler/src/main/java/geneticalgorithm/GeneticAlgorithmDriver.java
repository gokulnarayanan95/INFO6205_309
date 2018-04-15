package geneticalgorithm;

import java.util.concurrent.CompletableFuture;

/**
 * This class implements Genetic Algorithm
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/14/2018
 */
public class GeneticAlgorithmDriver {
    static final double MUTATION_RATE = 0.015;
    static final double CROSSOVER_RATE = 0.5;
    static final int TOURNAMENT_SELECTION_SIZE = 50;
    static final int NUMB_OF_ELITE_SCHEDULES = 1;

    private static final int POPULATION_SIZE = 5000;
    private static final int COLONY_SIZE = 100;
    private static Population population = null;
    private static int scheduleNumber = 0;
    private static int fixtureNumber = 1;
    private static Data data = new Data();

    public static void main(String[] args) {
        runAlgorithm(0, POPULATION_SIZE);
//        population = new Population(POPULATION_SIZE, data, true);
//        reproduce();
    }

    private static void runAlgorithm(int from, int to) {
        int size = to - from;

        if (size < COLONY_SIZE) {
            population = new Population(size, data, true);
        } else {
            int mid = (from + to) / 2;

            CompletableFuture<Population> colonies1 = generatePopulation(from, mid);
            CompletableFuture<Population> colonies2 = generatePopulation(mid, to);

            CompletableFuture<Population> groupColonies = colonies1
                    .thenCombine(colonies2, (xs1, xs2) -> new Population(xs1.getSchedules(), xs2.getSchedules()));

            groupColonies.whenComplete((population, throwable) -> {
                if (throwable != null) {
                    System.out.println("Exception thrown in thread: " + throwable.getMessage());
                    return;
                }

                GeneticAlgorithmDriver.population = population;
            });

            CompletableFuture.allOf(groupColonies).join();

            groupColonies.thenRun(GeneticAlgorithmDriver::reproduce);
        }
    }

    private static CompletableFuture<Population> generatePopulation(int from, int to) {
        return CompletableFuture.supplyAsync(() -> {
            runAlgorithm(from, to);
            return population;
        });
    }

    private static void reproduce() {
        int generationNumber = 0;
        Algorithm algorithm = new Algorithm(data);

        fixtureNumber = 1;

        while (population.getSchedules().get(0).getFitness() != 1.0) {
            population = algorithm.evolve(population).sortByFitness();
            scheduleNumber = 0;
            population.getSchedules().forEach(schedule -> System.out.println("       " + scheduleNumber++ +
                    "     | " + schedule + " | " +
                    String.format("%.5f", schedule.getFitness()) +
                    " | " + schedule.getConflicts()));
        }
    }
}
