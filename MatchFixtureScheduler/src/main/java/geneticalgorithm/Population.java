/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package geneticalgorithm;

import geneticalgorithm.domain.Schedule;
import geneticalgorithm.domain.Fixture;
import java.util.ArrayList;

/**
 *
 * @author naray
 */
public class Population {
    
    private ArrayList<Schedule> schedules;
    
    public Population(int size, Data data, boolean initialize) {
        
        schedules = new ArrayList<>(size);
        if(initialize){
        for (int i = 0; i < size; i++)
            schedules.add(new Schedule(data));
        }
    }
    
    public Population(ArrayList<Schedule> s1, ArrayList<Schedule> s2) {
        schedules = new ArrayList<>();
        schedules.addAll(s1);
        schedules.addAll(s2);
    }
    public int size(){
        return schedules.size();
    }
    public Schedule getFittestSchedule(){
        return null; // TODO
    }
    
    public void saveSchedule(Schedule s){
        schedules.add(s);
    }

    public ArrayList<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(ArrayList<Schedule> schedules) {
        this.schedules = schedules;
    }
    
    public Schedule getFittest(){
        return null; // TODO
    }
    
      public Population sortByFitness() {
        schedules.sort((schedule1, schedule2) -> {
            int returnValue = 0;
            if (schedule1.getFitness() > schedule2.getFitness()) {
                returnValue = -1;
            } else if (schedule1.getFitness() < schedule2.getFitness()) {
                returnValue = 1;
            }
            return returnValue;
        });
        return this;
    }
    
}
