package com.fullStack.expenseTracker.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fullStack.expenseTracker.dtos.ApiResponseDto;
import com.fullStack.expenseTracker.dtos.CategoryRequestDto;
import com.fullStack.expenseTracker.entities.Category;
import com.fullStack.expenseTracker.exceptions.CategoryServiceLogicException;
import com.fullStack.expenseTracker.services.CategoryService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CategoryController.class)
public class CategoryControllerTest {

    private MockMvc mockMvc;
    
    @MockBean
    private CategoryService categoryService;

    @InjectMocks
    private CategoryController categoryController;

    @Mock
    private WebApplicationContext webApplicationContext;

    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testAddCategorySuccess() throws Exception {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setCategoryName("New Category");
        requestDto.setTransactionTypeId(1);

        Category category = new Category();
        category.setCategoryName(requestDto.getCategoryName());
        category.setTransactionTypeId(requestDto.getTransactionTypeId());

        when(categoryService.addCategory(ArgumentMatchers.any(Category.class))).thenReturn(Optional.of(category));

        ApiResponseDto<String> responseDto = new ApiResponseDto<>(ApiResponseDto.Status.SUCCESS, HttpStatus.CREATED, "Category added successfully!");

        mockMvc.perform(post("/mywallet/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isCreated())
                .andExpect(result -> assertEquals(objectMapper.writeValueAsString(responseDto), result.getResponse().getContentAsString()));
    }

    @Test
    public void testAddCategoryFailure() throws Exception {
        CategoryRequestDto requestDto = new CategoryRequestDto();
        requestDto.setCategoryName("New Category");
        requestDto.setTransactionTypeId(1);

        when(categoryService.addCategory(ArgumentMatchers.any(Category.class))).thenThrow(new CategoryServiceLogicException("Category cannot be added"));

        ApiResponseDto<String> responseDto = new ApiResponseDto<>(ApiResponseDto.Status.FAILED, HttpStatus.BAD_REQUEST, "Category cannot be added");

        mockMvc.perform(post("/mywallet/category/add")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertEquals(objectMapper.writeValueAsString(responseDto), result.getResponse().getContentAsString()));
    }
}
