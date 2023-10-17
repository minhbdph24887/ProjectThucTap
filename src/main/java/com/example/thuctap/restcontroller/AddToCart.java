package com.example.thuctap.restcontroller;

import com.example.thuctap.bean.ProductItems;
import com.example.thuctap.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
public class AddToCart {
    @Autowired
    DetailService service;

    @RequestMapping(value = "/product/{idProductItems}", method = RequestMethod.GET)
    public ProductItems getOneCart(@PathVariable("idProductItems") Long idProductItems) {
        return service.detailProductItems(idProductItems);
    }
}
