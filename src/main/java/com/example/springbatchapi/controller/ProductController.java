package com.example.springbatchapi.controller;

import com.example.springbatchapi.entity.Product;
import com.example.springbatchapi.helper.ExcelHelper;
import com.example.springbatchapi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("/product/upload")
    public ResponseEntity<?> upload(@RequestParam("file")MultipartFile file) throws IOException {
        if (ExcelHelper.checkExcelFormat(file)){
            // True
            this.productService.save(file);
            return ResponseEntity.ok(Map.of("message", "File is uploaded and data saved to database."));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Please Upload Excel file only");
    }

    @GetMapping("/product")
    public List<Product> getAllProducts(){
        return this.productService.getAllProducts();
    }

}
