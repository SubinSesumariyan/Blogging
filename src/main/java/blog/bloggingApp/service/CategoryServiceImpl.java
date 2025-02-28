package blog.bloggingApp.service;

import blog.bloggingApp.entity.Category;
import blog.bloggingApp.exception.ResourceNotFoundException;
import blog.bloggingApp.payloads.CategoryDto;
import blog.bloggingApp.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepo categoryRepo;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category=new Category();
        category.setCategoryId(categoryDto.getCategoryId());
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory=this.categoryRepo.save(category);

        CategoryDto categoryDto1=new CategoryDto();
        categoryDto1.setCategoryId(savedCategory.getCategoryId());
        categoryDto1.setCategoryTitle(savedCategory.getCategoryTitle());
        categoryDto1.setCategoryDescription(savedCategory.getCategoryDescription());
        return categoryDto1;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Id", categoryId));
        //category.setCategoryId(categoryDto.getCategoryId()); // its mistake. because primary keys are immuted.
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category savedCategory=this.categoryRepo.save(category);

        CategoryDto categoryDto1=new CategoryDto();
        categoryDto1.setCategoryId(savedCategory.getCategoryId());
        categoryDto1.setCategoryTitle(savedCategory.getCategoryTitle());
        categoryDto1.setCategoryDescription(savedCategory.getCategoryDescription());
        return categoryDto1;
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Id", categoryId));
        CategoryDto categoryDto1=new CategoryDto();
        categoryDto1.setCategoryId(category.getCategoryId());
        categoryDto1.setCategoryTitle(category.getCategoryTitle());
        categoryDto1.setCategoryDescription(category.getCategoryDescription());
        return categoryDto1;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories =this.categoryRepo.findAll();
        List<CategoryDto> categoryDtos=new ArrayList<>();
        for(int i=0; i<categories.size(); i++){
            CategoryDto categoryDto=new CategoryDto();
            categoryDto.setCategoryId(categories.get(i).getCategoryId());
            categoryDto.setCategoryTitle(categories.get(i).getCategoryTitle());
            categoryDto.setCategoryDescription(categories.get(i).getCategoryDescription());
            categoryDtos.add(categoryDto);
        }
        return categoryDtos;
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("category","Id",categoryId));
        this.categoryRepo.delete(category);

    }
}
