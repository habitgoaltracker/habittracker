package is.hi.hbv501g.habittracker.Persistence.Entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "habits")
public class Habit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long ID;

    private String name;
    @ManyToOne
    private Category category;
    private int streak;
    private int highestStreak;
    private LocalDate lastDate;

    public Habit() {

    }

    public Habit(String name, Category category, int streak, int highestStreak, LocalDate lastDate) {
        this.name = name;
        this.category = category;
        this.streak = streak;
        this.highestStreak = highestStreak;
        this.lastDate = lastDate;
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
/*
    @Override
    public String toString() {
        return "Habit{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", streak=" + streak +
                ", highestStreak=" + highestStreak +
                ", lastDate=" + lastDate +
                '}';
    }

 */
}
