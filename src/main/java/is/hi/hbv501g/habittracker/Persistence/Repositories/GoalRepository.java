package is.hi.hbv501g.habittracker.Persistence.Repositories;

import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GoalRepository extends JpaRepository<Goal, Long> {
    Goal save(Goal goal);
    void deleteById(long id);
    List<Goal> findAll();
    Goal findByID(long id);
}
