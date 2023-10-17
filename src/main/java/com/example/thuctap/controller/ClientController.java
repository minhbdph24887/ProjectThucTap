package com.example.thuctap.controller;

import com.example.thuctap.bean.Category;
import com.example.thuctap.bean.Product;
import com.example.thuctap.bean.ProductItems;
import com.example.thuctap.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientController {
    List<Category> listCategory = new ArrayList<>();
    @Autowired
    DetailService service;

    @RequestMapping(value = "/product", method = RequestMethod.GET)
    public String viewAllViewer(Model model, @RequestParam(defaultValue = "0") int page, @RequestParam("cid") Optional<Long> cid) {
        listCategory = service.viewCategory();
        if (cid.isPresent()) {
            Page<ProductItems> listProductItems = service.getProductsByCategory(PageRequest.of(page, 6), cid.get());
            model.addAttribute("listCategory", listCategory);
            model.addAttribute("listProduct", listProductItems);
        } else {
            Page<ProductItems> listProductItems = service.getAllProductItems(PageRequest.of(page, 6));
            model.addAttribute("listCategory", listCategory);
            model.addAttribute("listProduct", listProductItems);
        }
        return "viewer/indexviewer";
    }

    @RequestMapping(value = "/product/detail/{idProductItems}", method = RequestMethod.GET)
    public String detailProductClient(Model model, @PathVariable("idProductItems") Long idProductItems) {
        ProductItems productItems = service.detailProductItems(idProductItems);
        if (productItems != null) {
            Category category = productItems.getCategory(); // Lấy danh mục của sản phẩm
            List<ProductItems> sameCategoryProducts = service.findProductItemsByCategory(category.getIdCategory()); // Lấy danh sách sản phẩm cùng loại
            model.addAttribute("detailProductItems", productItems);
            model.addAttribute("sameCategoryProducts", sameCategoryProducts); // Thêm danh sách sản phẩm cùng loại vào Model
        }
        return "viewer/detailproduct";
    }
}
