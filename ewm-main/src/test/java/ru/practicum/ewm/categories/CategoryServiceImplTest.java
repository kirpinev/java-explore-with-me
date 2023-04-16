package ru.practicum.ewm.categories;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import ru.practicum.ewm.categories.dto.CategoryDto;
import ru.practicum.ewm.categories.dto.NewCategoryDto;
import ru.practicum.ewm.categories.model.Category;
import ru.practicum.ewm.categories.repository.CategoryRepository;
import ru.practicum.ewm.categories.service.CategoryServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)

public class CategoryServiceImplTest {

    @InjectMocks
    private CategoryServiceImpl categoryService;

    @Mock
    private CategoryRepository categoryRepository;

    private final NewCategoryDto newCategoryDto = new NewCategoryDto("Концерты");

    private final CategoryDto categoryDto = new CategoryDto(1L, "Концерты");

    private final Category category = new Category(1L, "Концерты");

    private final Category updatedCategory = new Category(1L, "Выставки");

    private final NewCategoryDto updatedCategoryDto = new NewCategoryDto("Выставки");

    @Test
    void createCategory() {
        when(categoryRepository.save(any(Category.class))).thenReturn(category);

        CategoryDto created = categoryService.create(newCategoryDto);

        Assertions.assertNotNull(created);
        Assertions.assertEquals(categoryDto.getId(), created.getId());
        Assertions.assertEquals(categoryDto.getName(), created.getName());

        verify(categoryRepository, times(1)).save(any(Category.class));
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    void getCategories() {
        when(categoryRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(List.of(category)));

        List<CategoryDto> categoryDtos = categoryService.getCategories(0, 1);

        Assertions.assertNotNull(categoryDtos);
        Assertions.assertEquals(1, categoryDtos.size());
        Assertions.assertEquals(categoryDto.getId(), categoryDtos.get(0).getId());
        Assertions.assertEquals(categoryDto.getName(), categoryDtos.get(0).getName());

        verify(categoryRepository, times(1)).findAll(any(PageRequest.class));
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    void getCategoryById() {
        when(categoryRepository.findCategoryById(any(Long.class), any(PageRequest.class)))
                .thenReturn(List.of(category));

        CategoryDto findCategoryDto = categoryService.getCategoryById(1L, 0, 1);

        Assertions.assertNotNull(findCategoryDto);
        Assertions.assertEquals(categoryDto.getId(), findCategoryDto.getId());
        Assertions.assertEquals(categoryDto.getName(), findCategoryDto.getName());

        verify(categoryRepository, times(1))
                .findCategoryById(anyLong(), any(PageRequest.class));
        verifyNoMoreInteractions(categoryRepository);
    }

    @Test
    void updateCategoryById() {
        when(categoryRepository.findById(anyLong())).thenReturn(Optional.of(category));

        CategoryDto updated = categoryService.updateCategoryById(1L, updatedCategoryDto);

        Assertions.assertNotNull(updated);
        Assertions.assertEquals(updatedCategory.getId(), updated.getId());
        Assertions.assertEquals(updatedCategory.getName(), updated.getName());

        verify(categoryRepository, times(1)).findById(anyLong());
        verifyNoMoreInteractions(categoryRepository);
    }
}
