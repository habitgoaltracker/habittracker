package is.hi.hbv501g.habittracker.Persistence.Entities;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "goals")
public class Goal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;
    @ManyToOne
    private Category category;

    private boolean goalCompleted;
    private double goalProgress;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate goalDueDate;
    @OneToMany(orphanRemoval = true)
    private List<Task> tasks;

    public Goal() {

    }

    public Goal(String name, Category category, boolean goalCompleted, double goalProgress, LocalDate goalDueDate, List<Task> tasks) {
        this.name = name;
        this.category = category;
        this.goalCompleted = goalCompleted;
        this.goalProgress = goalProgress;
        this.goalDueDate = goalDueDate;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
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

    public LocalDate getGoalDueDate() {
        return goalDueDate;
    }

    public void setGoalDueDate(LocalDate goalDueDate) {
        this.goalDueDate = goalDueDate;
    }
}
