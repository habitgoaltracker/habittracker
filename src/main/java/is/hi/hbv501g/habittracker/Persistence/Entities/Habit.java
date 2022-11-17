package is.hi.hbv501g.habittracker.Persistence.Entities;

import com.sun.istack.NotNull;
import org.hibernate.criterion.NotEmptyExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.NonNull;
import org.springframework.boot.validation.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "habits")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;


    private String name;
    @ManyToOne
    private Category category;
    private int streak = 0;
    private int highestStreak = 0;
    private LocalDate lastDate;
    private LocalDate createdDate = LocalDate.now().minusMonths(9);
    private int totalCompletions = 0;
    private boolean habitCompleted;
    private long totalHabitChances;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    //private boolean onStreak;



    public Habit() {

    }


    public Habit(String name, Category category, int streak, int highestStreak, LocalDate lastDate, LocalDate createdDate, int totalCompletions, User user, boolean habitCompleted, long totalHabitChances) {
        this.name = name;
        this.category = category;
        this.streak = streak;
        this.highestStreak = highestStreak;
        this.lastDate = lastDate;
        this.createdDate = createdDate;
        this.totalCompletions = totalCompletions;
        this.user = user;
        this.habitCompleted = habitCompleted;
        this.totalHabitChances = totalHabitChances;
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

    public int getStreak() {
        return streak;
    }

    public void setStreak(int streak) {
        this.streak = streak;
    }

    public int getHighestStreak() {
        return highestStreak;
    }

    public void setHighestStreak(int highestStreak) {
        this.highestStreak = highestStreak;
    }

    public LocalDate getLastDate() {
        return lastDate;
    }

    public void setLastDate(LocalDate lastDate) {
        this.lastDate = lastDate;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public int getTotalCompletions() {
        return totalCompletions;
    }

    public void setTotalCompletions(int totalCompletions) {
        this.totalCompletions = totalCompletions;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isHabitCompleted() {
        return habitCompleted;
    }

    public void setHabitCompleted(boolean habitCompleted) {
        this.habitCompleted = habitCompleted;
    }

    @Override
    public String toString() {
        return "Habit{" + ", \n" +
                "ID=" + ID + ", \n" +
                " name='" + name + '\'' + ", \n" +
                " category=" + category.getName() + ", \n" +
                " streak=" + streak + ", \n" +
                " highestStreak=" + highestStreak + ", \n" +
                " lastDate=" + lastDate + ", \n" +
                " createdDate=" + createdDate + ", \n" +
                " totalCompletions=" + totalCompletions + ", \n" +
                " habitCompleted=" + habitCompleted + ", \n" +
                " user=" + user + ", \n" +
                " totalHabitChances=" + totalHabitChances + ", \n" +
                '}';
    }

    public long getTotalHabitChances() {
        return totalHabitChances;
    }

    public void setTotalHabitChances(long totalHabitChances) {
        this.totalHabitChances = totalHabitChances;
    }
}
