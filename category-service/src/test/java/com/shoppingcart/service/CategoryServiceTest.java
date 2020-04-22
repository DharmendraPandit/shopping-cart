package com.shoppingcart.service;

import com.shoppingcart.model.Category;
import com.shoppingcart.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

public class CategoryServiceTest {

    @InjectMocks
    private CategoryService service;
    @Mock
    private CategoryRepository repository;
    private Category category;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        category = create();
    }

    @Test
    public void createSuccess() {
        when(repository.save(category)).thenReturn(category);
        Category actualResult = service.create(category);
        Assertions.assertEquals(actualResult.getId(), category.getId());
    }

    @Test
    public void getAllSuccess() {
        List<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        when(repository.findAll()).thenReturn(categoryList);
        List<Category> actualResult = service.getAll();
        Assertions.assertEquals(actualResult.size(), categoryList.size());
    }

    @Test
    public void getByIdSuccess() {
        Optional<Category> optionalCategory = Optional.of(category);
        when(repository.findById(category.getId())).thenReturn(optionalCategory);
        Optional<Category> acutalResult = service.getById(category.getId());
        Assertions.assertEquals(acutalResult.isPresent(), optionalCategory.isPresent());
    }

    private Category create() {
        Category category = new Category();
        category.setId(1L);
        category.setDescription("Cosmatic");
        return category;

    }
}
