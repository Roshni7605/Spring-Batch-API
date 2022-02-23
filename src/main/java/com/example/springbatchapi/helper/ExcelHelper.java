package com.example.springbatchapi.helper;

import com.example.springbatchapi.entity.Product;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelHelper {

    /**
     * Check that file is of Excel type or not
     * @param file
     * @return
     */
    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        if(contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")){
            return true;
        }else return false;
    }

    /**
     * Convert Excel to list of Products
     * @param inputStream
     * @return
     */
    public static List<Product> convertExcelToListOfProduct(InputStream inputStream){
        List<Product> productList = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("product");
            int rowNumber = 0;
            Iterator<Row> iterator = sheet.iterator();
            while (iterator.hasNext()){
                Row row = iterator.next();
                if (rowNumber==0){
                    rowNumber++;
                    continue;
                }
                Iterator<Cell> cells = row.iterator();
                int cid = 0;
                Product product = new Product();
                while (cells.hasNext()){
                    Cell cell = cells.next();
                    switch(cid){
                        case 0:
                            product.setProductId((int)cell.getNumericCellValue());
                            break;
                        case 1:
                            product.setProductName(cell.getStringCellValue());
                            break;
                        case 2:
                            product.setProductDesc(cell.getStringCellValue());
                            break;
                        case 3:
                            product.setProductPrice(cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                    cid++;
                }
                productList.add(product);
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }
}
