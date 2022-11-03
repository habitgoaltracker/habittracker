package is.hi.hbv501g.habittracker.Services.Implementation;

import is.hi.hbv501g.habittracker.Persistence.Entities.Category;
import is.hi.hbv501g.habittracker.Persistence.Repositories.CategoryRepository;
import is.hi.hbv501g.habittracker.Services.CategoryService;

import java.util.List;

public class CategoryServiceImplementation implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImplementation(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Category findByName(String name) {
        return categoryRepository.findByName(name).get(0);
    }

    @Override
    public Category findByID(long ID) {
        return categoryRepository.findByID(ID);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void deleteByID(long id) {
        Category category = findByID(id);
        this.categoryRepository.delete(category);
    }
}
