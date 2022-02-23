package com.example.springbatchapi.service;

import com.example.springbatchapi.entity.Product;
import com.example.springbatchapi.helper.ExcelHelper;
import com.example.springbatchapi.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public void save(MultipartFile file) throws IOException {
        List<Product> productList = ExcelHelper.convertExcelToListOfProduct(file.getInputStream());
        this.productRepo.saveAll(productList);
    }

    public List<Product> getAllProducts(){
        return this.productRepo.findAll();
    }
}
