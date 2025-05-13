package codeid.oe.service;

import com.codeid.oe.model.dto.CategoryDto;
import com.codeid.oe.model.entity.Category;
import com.codeid.oe.repository.CategoryRepository;
import com.codeid.oe.service.implementation.CategoryServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CategoryServiceTest {

    @Mock
    private CategoryRepository categoryRepository;

    @InjectMocks
    private CategoryServiceImpl categoryService;

    private static final Short CATEGORY_ID = 100;
    private static final String CATEGORY_NAME = "Category";
    private static final String DESCRIPTION = "Description";
    private static final byte[] PICTURE = new byte[]{1, 2, 3};

    @Test
    void findAll_whenCategoriesExists_shouldReturnPageOfCategoryResponse() {
        // Arrange
        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);
        category.setCategoryName(CATEGORY_NAME);
        category.setDescription(DESCRIPTION);
        category.setPicture(PICTURE);

        List<Category> categories = List.of(category);

        when(categoryRepository.findAll())
                .thenReturn(categories);

        // Act
        List<CategoryDto> responses = categoryService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(1, responses.size());
        assertEquals(CATEGORY_ID, responses.get(0).getCategoryId());
        assertEquals(CATEGORY_NAME, responses.get(0).getCategoryName());
        assertEquals(DESCRIPTION, responses.get(0).getDescription());
        assertArrayEquals(PICTURE, responses.get(0).getPicture());

        verify(categoryRepository, times(1))
                .findAll();
    }

    @Test
    void findAll_whenCategoriesNotExists_shouldReturnEmptyOfCategoryResponse() {
        // Arrange
        List<Category> categories = List.of();

        when(categoryRepository.findAll())
                .thenReturn(categories);

        // Act
        List<CategoryDto> responses = categoryService.findAll();

        // Assert
        assertNotNull(responses);
        assertEquals(0, responses.size());

        verify(categoryRepository, times(1))
                .findAll();
    }

    @Test
    void findOne_whenCategoryExists_shouldReturnCategoryResponse() {
        // Arrange
        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);
        category.setCategoryName(CATEGORY_NAME);
        category.setDescription(DESCRIPTION);
        category.setPicture(PICTURE);

        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(category));

        // Act
        CategoryDto response = categoryService.findById(CATEGORY_ID);

        // Assert
        assertNotNull(response);
        assertEquals(CATEGORY_ID, response.getCategoryId());
        assertEquals(CATEGORY_NAME, response.getCategoryName());
        assertEquals(DESCRIPTION, response.getDescription());
        assertArrayEquals(PICTURE, response.getPicture());

        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
    }

    @Test
    void findOne_whenCategoryNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            categoryService.findById(CATEGORY_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
    }

    @Test
    void create_whenRequestIsValid_shouldSaveAndReturnCategoryResponse() {
        // Arrange
        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);
        category.setCategoryName(CATEGORY_NAME);
        category.setDescription(DESCRIPTION);
        category.setPicture(PICTURE);

        CategoryDto request = new CategoryDto();
        request.setCategoryId(CATEGORY_ID);
        request.setCategoryName(CATEGORY_NAME);
        request.setDescription(DESCRIPTION);
        request.setPicture(PICTURE);

        when(categoryRepository.save(any(Category.class)))
                .thenReturn(category);

        // Act
        CategoryDto response = categoryService.save(request);

        // Assert
        assertNotNull(response);
        assertEquals(CATEGORY_ID, response.getCategoryId());
        assertEquals(CATEGORY_NAME, response.getCategoryName());
        assertEquals(DESCRIPTION, response.getDescription());
        assertArrayEquals(PICTURE, response.getPicture());

        verify(categoryRepository, times(1))
                .save(any(Category.class));
    }

    @Test
    void update_whenCategoryExists_shouldUpdateAndReturnCategoryResponse() {
        // Arrange
        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);
        category.setCategoryName(CATEGORY_NAME);
        category.setDescription(DESCRIPTION);
        category.setPicture(PICTURE);

        CategoryDto request = new CategoryDto();
        request.setCategoryId(CATEGORY_ID);
        request.setCategoryName("New Company");
        request.setDescription("New Description");
        request.setPicture(new byte[]{3, 2, 1});

        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(category));
        when(categoryRepository.save(any(Category.class)))
                .thenReturn(category);

        // Act
        CategoryDto response = categoryService.update(CATEGORY_ID, request);

        // Assert
        assertNotNull(response);
        assertEquals(CATEGORY_ID, response.getCategoryId());
        assertEquals("New Company", response.getCategoryName());
        assertEquals("New Description", response.getDescription());
        assertArrayEquals(new byte[]{3, 2, 1}, response.getPicture());

        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(categoryRepository, times(1))
                .save(any(Category.class));
    }

    @Test
    void update_whenCategoryNotExists_shouldThrowResponseStatusException() {
        // Arrange
        CategoryDto request = new CategoryDto();
        request.setCategoryId(CATEGORY_ID);
        request.setCategoryName("New Company");
        request.setDescription("New Description");
        request.setPicture(new byte[]{3, 2, 1});

        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            categoryService.update(CATEGORY_ID, request);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(categoryRepository, never())
                .save(any(Category.class));
    }

    @Test
    void remove_whenCategoryExists_shouldDeleteCategory() {
        // Arrange
        Category category = new Category();
        category.setCategoryId(CATEGORY_ID);
        category.setCategoryName(CATEGORY_NAME);
        category.setDescription(DESCRIPTION);
        category.setPicture(PICTURE);

        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.of(category));
        doNothing().when(categoryRepository)
                .delete(category);

        // Act
        categoryService.delete(CATEGORY_ID);

        // Assert
        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(categoryRepository, times(1))
                .delete(category);
    }

    @Test
    void remove_whenCategoryNotExists_shouldThrowResponseStatusException() {
        // Arrange
        when(categoryRepository.findById(CATEGORY_ID))
                .thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            categoryService.delete(CATEGORY_ID);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());

        verify(categoryRepository, times(1))
                .findById(CATEGORY_ID);
        verify(categoryRepository, never())
                .delete(any(Category.class));
    }
}