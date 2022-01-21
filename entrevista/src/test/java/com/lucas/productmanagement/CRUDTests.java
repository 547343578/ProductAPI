package com.lucas.productmanagement;

import com.lucas.productmanagement.exception.ProductNotFoundException;
import com.lucas.productmanagement.model.Product;
import com.lucas.productmanagement.repo.ProductRepo;
import com.lucas.productmanagement.service.ProductService;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import org.testng.annotations.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import javax.transaction.Transactional;

@SpringBootTest(classes = ProductManagementApplication.class)
@RunWith(SpringRunner.class)
public class CRUDTests extends AbstractTransactionalTestNGSpringContextTests{
    private static final String TEST_PRODUCT1_NAME = "Manzana";
    private static final String TEST_PRODUCT1_SECTOR = "Fruta";
    private static final Double TEST_PRODUCT1_PRICE = 10.02;
    private static final String TEST_PRODUCT2_NAME = "iPhone X";
    private static final String TEST_PRODUCT2_SECTOR = "Electronicos";
    private static final Double TEST_PRODUCT2_PRICE = 989.99;

    @Autowired
    ProductService productService;
    
    @Test(dependsOnMethods = {"addProd"})
    @Rollback(value = false)
    @Transactional(value = Transactional.TxType.NEVER)
    public void getAll(){
        Assert.assertEquals(2, productService.findAllProducts().size());
    }

    @Test(dependsOnMethods = {"addProd"})
    @Rollback(value = false)
    @Transactional(value = Transactional.TxType.NEVER)
    public void getProdById(){
        Assert.assertNotNull(productService.findProductById((long)1));
    }

    @Test
    @Rollback(value = false)
    @Transactional(value = Transactional.TxType.NEVER)
    public void addProd(){
        Product p1 = new Product(TEST_PRODUCT1_NAME, TEST_PRODUCT1_SECTOR, TEST_PRODUCT1_PRICE);
        Product p2 = new Product(TEST_PRODUCT2_NAME, TEST_PRODUCT2_SECTOR, TEST_PRODUCT2_PRICE);
        productService.addProduct(p1);
        Assert.assertEquals(1, productService.count());
        productService.addProduct(p2);
        Assert.assertEquals(2, productService.count());
    }

    @Test(dependsOnMethods = {"getProdById", "addProd"})
    @Rollback(value = false)
    @Transactional(value = Transactional.TxType.NEVER)
    public void updateProd(){
        Product p = productService.findProductById((long)1);
        p.setPrice(20.02);
        productService.updateProduct(p);
        Product p1 = productService.findProductById((long)1);
        double price = p1.getPrice();
        Assert.assertEquals((long)20.02, (long)price);
    }

    @Test(dependsOnMethods = {"addProd", "updateProd", "getAll", "getProdById"})
    @Rollback(value = false)
    @Transactional(value = Transactional.TxType.NEVER)
    public void deleteProd(){
        productService.deleteProduct((long)2);
        Assert.assertEquals(1, productService.count());
    }

    @Test(dependsOnMethods = {"addProd", "updateProd", "deleteProd", "getAll", "getProdById"})
    @Rollback(value = false)
    @Transactional(value = Transactional.TxType.NEVER)
    public void removeElements(){
        deleteFromTables(new String[]{"product"});

        Assert.assertEquals(0, productService.count());
    }
}
