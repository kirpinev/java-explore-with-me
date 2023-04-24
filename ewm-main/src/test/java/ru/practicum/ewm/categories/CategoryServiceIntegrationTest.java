package ru.practicum.ewm.categories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.dto.NewCategoryDto;
import ru.practicum.ewm.categories.service.CategoryService;
import ru.practicum.ewm.exception.NotFoundException;

import java.util.List;

@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CategoryServiceIntegrationTest {

    @Autowired
    private CategoryService categoryService;

    private final NewCategoryDto newCategoryDto = new NewCategoryDto("Концерты");

    @Test
    void create() {
        CategoryDto categoryDto = categoryService.create(newCategoryDto);

        Assertions.assertEquals(1L, categoryDto.getId());
        Assertions.assertEquals(newCategoryDto.getName(), categoryDto.getName());
    }

    @Test
    void getCategories() {
        categoryService.create(newCategoryDto);

        List<CategoryDto> categoryDtos = categoryService.getCategories(0, 1);

        Assertions.assertNotNull(categoryDtos);
        Assertions.assertEquals(1, categoryDtos.size());
        Assertions.assertEquals(1L, categoryDtos.get(0).getId());
        Assertions.assertEquals(newCategoryDto.getName(), categoryDtos.get(0).getName());
    }

    @Test
    void getCategoriesById() {
        categoryService.create(newCategoryDto);

        CategoryDto findCategory = categoryService.getCategoryById(1L, 0, 1);

        Assertions.assertNotNull(findCategory);
        Assertions.assertEquals(1L, findCategory.getId());
        Assertions.assertEquals(newCategoryDto.getName(), findCategory.getName());
    }

    @Test
    void deleteCategoryById() {
        categoryService.create(newCategoryDto);

        CategoryDto findCategory = categoryService.getCategoryById(1L, 0, 1);

        Assertions.assertNotNull(findCategory);
        Assertions.assertEquals(1L, findCategory.getId());
        Assertions.assertEquals(newCategoryDto.getName(), findCategory.getName());

        categoryService.deleteCategoryById(1L);

        List<CategoryDto> deletedCategories = categoryService.getCategories(0, 1);

        Assertions.assertNotNull(deletedCategories);
        Assertions.assertEquals(0, deletedCategories.size());
    }

    @Test
    void getCategoryByWrongId() {
        Assertions.assertThrows(NotFoundException.class,
                () -> categoryService.getCategoryById(10L, 0, 1));
    }

    @Test
    void updateCategoryByWrongId() {
        NewCategoryDto newCategoryDto = new NewCategoryDto("Концерты");

        Assertions.assertThrows(NotFoundException.class,
                () -> categoryService.updateCategoryById(10L, newCategoryDto));
    }

    @Test
    void deleteCategoryByWrongId() {
        Assertions.assertThrows(NotFoundException.class,
                () -> categoryService.deleteCategoryById(10L));
    }
}
