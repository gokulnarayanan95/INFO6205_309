package geneticalgorithm;

import geneticalgorithm.domain.Fixture;
import geneticalgorithm.domain.Schedule;

import java.util.concurrent.CompletableFuture;

/**
 * This class implements Genetic Algorithm
 *
 * @author Gokul Anantha Narayanan, Krupashankar Sundararajan, Raghavan Renganathan
 * @version 1.0
 * @since 04/14/2018
 */
public class GeneticAlgorithmDriver {
    static final double MUTATION_RATE = 0.05;
    static final double CROSSOVER_RATE = 0.5;
    static final int TOURNAMENT_SELECTION_SIZE = 50;

    private static final int POPULATION_SIZE = 5000;
    private static final int COLONY_SIZE = 100;
    private static Population population = null;
    private static Data data = new Data();

    public static void main(String[] args) {
        runAlgorithm(0, POPULATION_SIZE);
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
        int generation = 0;
        Algorithm algorithm = new Algorithm(data);

        while (population.getSchedules().get(0).getFitness() != 1.0) {
            population = algorithm.evolve(population).sortByFitness();

            Schedule best = population.getSchedules().get(0);
            Schedule worst = population.getSchedules().get(population.size() - 1);

            System.out.println("Generation - " + (++generation));
            System.out.println("Best Schedule:");
            System.out.println(String.format("Fitness = %.5f", best.getFitness()));
            System.out.println("Conflicts = " + best.getConflicts());

            System.out.println("Worst Schedule:");
            System.out.println(String.format("Fitness = %.5f", worst.getFitness()));
            System.out.println("Conflicts = " + worst.getConflicts());

            System.out.println("\n\nBest Schedule:\n");

            System.out.println("Date\t\t|\t\tHome Team\t\t|\t\tAway Team\t\t|\t\tLocation");
            best.getFixures().sort((f1, f2) -> {
                if (f1.getDate().after(f2.getDate()))
                    return 1;
                else if (f1.getDate().before(f2.getDate()))
                    return -1;
                else
                    return 0;
            });
            for (Fixture f : best.getFixures()) {
                System.out.println(f);
            }
        }
    }
}
