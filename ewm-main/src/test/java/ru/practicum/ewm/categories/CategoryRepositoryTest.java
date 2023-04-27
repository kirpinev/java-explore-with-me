package ru.practicum.ewm.categories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.DirtiesContext;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.categories.repository.CategoryRepository;

import java.util.List;

@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CategoryRepository categoryRepository;

    private final Category category = new Category(null, "Концерты");

    @Test
    void createCategory() {
        Category created = categoryRepository.save(category);

        Assertions.assertNotNull(created);
        Assertions.assertEquals(1L, created.getId());
        Assertions.assertEquals(category.getName(), created.getName());
    }

    @Test
    void updateCategory() {
        entityManager.persist(category);
        entityManager.flush();

        Category updated = categoryRepository.findById(1L).orElse(null);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(1L, updated.getId());
        Assertions.assertEquals(category.getName(), updated.getName());
    }

    @Test
    void getCategories() {
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categories = categoryRepository
                .findAll(PageRequest.of(0, 1)).getContent();

        Assertions.assertNotNull(categories);
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals(1L, categories.get(0).getId());
        Assertions.assertEquals(category.getName(), categories.get(0).getName());
    }

    @Test
    void getCategoryById() {
        entityManager.persist(category);
        entityManager.flush();

        List<Category> categories = categoryRepository
                .findById(1L, PageRequest.of(0, 1));

        Assertions.assertNotNull(categories);
        Assertions.assertEquals(1, categories.size());
        Assertions.assertEquals(1L, categories.get(0).getId());
        Assertions.assertEquals(category.getName(), categories.get(0).getName());
    }

    @Test
    void deleteCategoryById() {
        entityManager.persist(category);
        entityManager.flush();

        categoryRepository.deleteCategoryById(1L);

        List<Category> categories = categoryRepository
                .findById(1L, PageRequest.of(0, 1));

        Assertions.assertNotNull(categories);
        Assertions.assertEquals(0, categories.size());
    }
}
