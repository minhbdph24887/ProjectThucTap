package com.example.thuctap.service;

import com.example.thuctap.bean.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface DetailService {
    // SERVICE ACCOUNT
    Page<Accounts> getAllAccounts(Pageable pageable);

    List<Accounts> getAllAccount();

    Accounts detailAccount(String Username);

    Accounts addAccount(Accounts accounts);

    Accounts updateAccount(Accounts accounts);

    void deleteAccount(String username);

    // SERVICE QUYEN
    Page<Role> getAllRolePage(Pageable pageable);

    List<Role> getAllRoles();

    Role detailRole(Long idRole);

    Role addRole(Role role);

    Role updateRole(Role role);

    void deleteRole(Long idRole);

    // SERVICE PHAN QUYEN
    Page<Authorities> getAllAuthorities(Pageable pageable);

    Authorities detailAuthorities(Long idAuthorities);

    Authorities addAuthorities(Authorities authorities);

    Authorities updateAuthorities(Authorities authorities);

    // SERVICE ADMIN
    Page<Accounts> getAllAdmin(Pageable pageable);

    // VIEW CLIENT
    Page<Accounts> getAllClient(Pageable pageable);

    // SERVICE SAN PHAM
    Page<Product> getAllProduct(Pageable pageable);

    List<Product> cbbProduct();

    Product detailProduct(Long idProduct);

    Product addProduct(Product product);

    Product updateProduct(Product product);

    void deleteProduct(Long idProduct);

    // SERVICE MAU SAC
    Page<Color> getAllColor(Pageable pageable);

    List<Color> cbbColor();

    Color detailColor(Long idColor);

    Color addColor(Color color);

    Color updateColor(Color color);

    void deleteColor(Long idColor);

    // SERVICE SIZE
    Page<Size> getAllSize(Pageable pageable);

    List<Size> cbbSize();

    Size detailSize(Long idSize);

    Size addSize(Size size);

    Size updateSize(Size size);

    void deleteSize(Long idSize);

    // SERVICE CHI TIET SAN PHAM
    Page<ProductItems> getAllProductItems(Pageable pageable);

    List<ProductItems> cbbProductItems();

    List<ProductItems> findProductItemsByCategory(Long idCategory);

    Page<ProductItems> getProductsByCategory(Pageable pageable, Long categoryId);

    ProductItems detailProductItems(Long idProductItems);

    ProductItems addProductItems(ProductItems productItems);

    ProductItems updateProductItems(ProductItems productItems, MultipartFile file) throws IOException;

    void deleteProductItems(Long idProductItems);

    // SERVICE CATEGORY
    Page<Category> getAllCategory(Pageable pageable);

    List<Category> viewCategory();

    Category detailCategory(Long idCategory);

    Category addCategory(Category category);

    Category updateCategory(Category category);

    void deleteCategory(Long idCategory);

    // SERVICE VOURCHER
    Page<Promotions> getAllPromotions(Pageable pageable);

    Promotions detailPromotions(Long idPromotions);

    Promotions addPromotions(Promotions promotions);

    Promotions updatePromotions(Promotions promotions);

    // SERVICE VOURCHER DETAIL
    Page<PromotionsProduct> getAllPromotionsProducts(Pageable pageable, Long idPromotions);

    PromotionsProduct addPromotionsProduct(PromotionsProduct promotionsProduct);

    PromotionsProduct deletePromotionsProduct(Long idPromotionsProduct);
}
