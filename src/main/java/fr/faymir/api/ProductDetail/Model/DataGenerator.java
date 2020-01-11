package fr.faymir.api.ProductDetail.Model;

import com.github.javafaker.Faker;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DataGenerator {
    private static DataGenerator instance = null;
    private List<ProductInfo> products = null;

    private DataGenerator(){
        products = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            Faker faker = new Faker(new Locale("fr"));
            products.add(new ProductInfo(
                    i,
                    faker.commerce().productName(),
                    faker.commerce().material(),
                    faker.commerce().department(),
                    Float.parseFloat(faker.commerce().price().replace(",", ".")),
                    faker.internet().image()
            ));
        }
    }

    public static DataGenerator getInstance(){
        if(instance == null){
            instance = new DataGenerator();
        }
        return instance;
    }

    public List<ProductInfo> products(){
        return products;
    }

    public void addProductInfo(ProductInfo productInfo){
        products.add(productInfo);
    }

    public boolean deleteProductInfo(Integer id){
        return products.removeIf(p -> p.getId() == id);
    }
}
