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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Category(String name, List<Habit> habits, List<Goal> goals, User user) {
        this.name = name;
        this.habits = habits;
        this.goals = goals;
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

}
