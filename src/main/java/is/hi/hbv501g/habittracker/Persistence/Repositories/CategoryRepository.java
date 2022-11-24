package is.hi.hbv501g.habittracker.Persistence.Repositories;

import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category save(Category category);
    void delete(Category category);
    List<Category> findAll();
    Category findByID(long id);
}
