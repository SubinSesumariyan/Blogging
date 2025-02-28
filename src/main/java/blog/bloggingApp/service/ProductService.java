package blog.bloggingApp.service;

import blog.bloggingApp.payloads.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;



    public Product getProductById() {
        String url = "https://fakestoreapi.com/products/1";
        return restTemplate.getForObject(url, Product.class);

    }
}
