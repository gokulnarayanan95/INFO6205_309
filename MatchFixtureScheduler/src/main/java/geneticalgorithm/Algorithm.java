/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.domain.Fixture;

/**
 *
 * @author naray
 */
public class Algorithm {
     private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private Data data;
    public Algorithm(Data data){
        this.data=data;
        this.data.initializeData();
    }
    public Population evolve(Population pop){
         Population newPopulation = new Population(pop.size(), data, false);
            for (int i = 0;i < pop.size(); i++) {
            Schedule s1 = tournamentSelection(pop);
            Schedule s2 = tournamentSelection(pop);
            Schedule newSchedule = crossover(s1, s2);
            newPopulation.saveSchedule(newSchedule);
        }
            for (Schedule s: newPopulation.getSchedules()) {
            mutation(s);
        }
         return newPopulation;
    }
    
    public Schedule crossover(Schedule s1, Schedule s2){
           Schedule newSol = new Schedule(data);
        // Loop through genes
        for (int i = 0; i < s1.size(); i++) {
            // Crossover
            if (Math.random() <= uniformRate) {
                newSol.getFixures().set(i, s1.getFixures().get(i));
            } else {
                newSol.getFixures().set(i, s2.getFixures().get(i));
            }
        }
        return newSol;
    }
    
    public void mutation(Schedule s1){
        Schedule tempSchedule= new Schedule(data);
        for (int i = 0; i < s1.size(); i++) {
            if (Math.random() <= mutationRate) {
                // Create random gene
                Fixture fix = (Fixture) tempSchedule.getFixures().get(i);
                s1.getFixures().set(i, fix);
            }
        }
    }
    
    public Schedule tournamentSelection(Population pop){
        Population tournament = new Population(tournamentSize,data, false);
        // For each place in the tournament get a random individual
        for (int i = 0; i < tournamentSize; i++) {
            int randomId = (int) (Math.random() * pop.size());
            tournament.getSchedules().set(i, pop.getSchedules().get(randomId));
        }
        // Get the fittest
        Schedule fittest = tournament.getFittest();
        return fittest;
    }
    
}
