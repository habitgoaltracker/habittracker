package is.hi.hbv501g.habittracker.Persistence.Entities;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate taskDueDate;
    private String taskDescription;
    private boolean taskCompleted;

    public Task(){

    }

    public Task(String name, LocalDate taskDueDate, String taskDescription, boolean taskCompleted) {
        this.name = name;
        this.taskDueDate = taskDueDate;
        this.taskDescription = taskDescription;
        this.taskCompleted = taskCompleted;
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

    public LocalDate getTaskDueDate() {
        return taskDueDate;
    }

    public void setTaskDueDate(LocalDate taskDueDate) {
        this.taskDueDate = taskDueDate;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }
}
