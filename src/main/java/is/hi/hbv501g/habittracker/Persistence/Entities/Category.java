package is.hi.hbv501g.habittracker.Persistence.Entities;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
@Entity
@Table(name = "categories")
public class Category {
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;
    @OneToMany(orphanRemoval = true)
    private List<Habit> habits;
    @OneToMany(orphanRemoval = true)
    private List<Goal> goals;

    public Category(String name, List<Habit> habits, List<Goal> goals) {
        this.name = name;
        this.habits = habits;
        this.goals = goals;
    }

    public Category(){}

    public long getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public List<Goal> getGoals() {
        return goals;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setHabits(List<Habit> habits) {
        this.habits = habits;
    }

    public void setGoals(List<Goal> goals) {
        this.goals = goals;
    }
    /*
    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", ID=" + ID +
                ", habits=" + habits +
                ", goals=" + goals +
                '}';
    }

     */
}

// veb3 ég gat haft toString í Category no problem, en ef það var í hinum þá fór allt í loft....eða allavegana keyrði toString Cat einu sinni....kannski fyrir vefsíðu gerðina??
// veb3 todo Hvernig get ég haft toString?
// veb3 todo Hvernig get ég haft null/default value í Category?
