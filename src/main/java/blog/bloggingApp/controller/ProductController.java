package blog.bloggingApp.controller;



import blog.bloggingApp.payloads.Product;
import blog.bloggingApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;



    @GetMapping("/product")
    public Product getProduct() {
        return productService.getProductById();
    }
}
