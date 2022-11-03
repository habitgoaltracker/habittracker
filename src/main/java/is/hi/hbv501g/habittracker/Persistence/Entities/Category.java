package is.hi.hbv501g.habittracker.Persistence.Entities;

import java.util.List;

public class Category {
    private String name;
    private String ID;
    private List<Habit> habits;
    private List<Goal> goals;

    public Category(String catName, List<Habit> habits, List<Goal> goals){
        this.name = catName;
        this.habits = habits;
        this.goals = goals;
    }

    public String getID() {
        return ID;
    }

    public String getCatName() {
        return name;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setCatName(String catName) {
        this.name = catName;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
}
