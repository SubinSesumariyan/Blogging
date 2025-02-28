package blog.bloggingApp.controller;

import blog.bloggingApp.payloads.ApiResponse;
import blog.bloggingApp.payloads.CategoryDto;
import blog.bloggingApp.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    ResponseEntity<CategoryDto> createCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto categoryDto1=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(categoryDto1, HttpStatus.CREATED);
    }
    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId){
        CategoryDto categoryDto1=this.categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(categoryDto1,HttpStatus.OK);
    }
    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryDto> geCategoriestById(@PathVariable Integer categoryId){
        CategoryDto categoryDto=this.categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto,HttpStatus.OK);
    }
    @GetMapping("/")
    ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDtos=this.categoryService.getAllCategories();
        return new ResponseEntity<List<CategoryDto>>(categoryDtos,HttpStatus.OK);
    }
    @DeleteMapping("/{categoryId}")
    ResponseEntity<ApiResponse> deleteCategory(@PathVariable Integer categoryId){
        this.categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("User Deleted successfully", true),HttpStatus.OK);
    }
}
