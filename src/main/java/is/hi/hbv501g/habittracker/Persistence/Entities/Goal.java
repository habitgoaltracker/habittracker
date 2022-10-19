package is.hi.hbv501g.habittracker.Persistence.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;
    private String category;
    private boolean goalCompleted;
    private double goalProgress;
    @OneToMany
    private List<Task> tasks;

    public Goal() {

    }

    public Goal(String name, String category, boolean goalCompleted, double goalProgress, List<Task> tasks) {
        this.name = name;
        this.category = category;
        this.goalCompleted = goalCompleted;
        this.goalProgress = goalProgress;
        this.tasks = tasks;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isGoalCompleted() {
        return goalCompleted;
    }

    public void setGoalCompleted(boolean goalCompleted) {
        this.goalCompleted = goalCompleted;
    }

    public double getGoalProgress() {
        return goalProgress;
    }

    public void setGoalProgress(double goalProgress) {
        this.goalProgress = goalProgress;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
