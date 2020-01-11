package fr.faymir.api.ProductDetail.Controller;

import fr.faymir.api.ProductDetail.Model.DataGenerator;
import fr.faymir.api.ProductDetail.Model.ProductInfo;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/productDetail")
public class ProductResource {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductInfo addProductInfo(@RequestBody ProductInfo productInfo){
        DataGenerator.getInstance().addProductInfo(productInfo);
        return productInfo;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProductInfo(@PathVariable("id") Integer id){
        DataGenerator.getInstance().deleteProductInfo(id);
    }

    @GetMapping("/{id}")
    public ProductInfo getProductInfo(@PathVariable("id") Integer id){
        for(ProductInfo pI: DataGenerator.getInstance().products()){
            if (pI.getId() == id)
                return pI;
        }
        return null;
    }
//
    @GetMapping("/all/{category}")
    public List<ProductInfo> getByCategory(@PathVariable("category") String category){
        List<ProductInfo> result = new ArrayList<>();
        DataGenerator.getInstance().products().forEach( p -> {
            if (p.getCategory().equalsIgnoreCase(category))
                result.add(p);
        });
        return result;
    }

    @GetMapping("/categories")
    public List<String> getCategories(){
        Set<String> categories = new HashSet<>();

        DataGenerator.getInstance().products().forEach( p -> categories.add(p.getCategory()));
        return new ArrayList<>(categories);
    }

    @GetMapping
    public List<ProductInfo> getProducts(){
        return DataGenerator.getInstance().products();
    }
}
