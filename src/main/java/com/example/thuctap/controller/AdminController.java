package com.example.thuctap.controller;

import com.example.thuctap.bean.*;
import com.example.thuctap.service.DetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("idPromotions")
public class AdminController {
    List<Accounts> listAccount = new ArrayList<>();
    List<Role> listRole = new ArrayList<>();
    List<Category> listCategory = new ArrayList<>();
    List<Product> listProduct = new ArrayList<>();
    List<Color> listColor = new ArrayList<>();
    List<Size> listSize = new ArrayList<>();
    List<ProductItems> listProductItems = new ArrayList<>();

    @Autowired
    DetailService service;

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String viewAdmin(Model model) {
        return "admin/viewadmindetail";
    }

    // CONTROLLER QUYEN
    @RequestMapping(value = "/admin/role", method = RequestMethod.GET)
    public String viewRole(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Role> itemRole = service.getAllRolePage(PageRequest.of(page, 5));
        model.addAttribute("listRole", itemRole);
        model.addAttribute("currentPage", page);
        return "admin/account/role/viewrole";
    }

    @RequestMapping(value = "/admin/role/view-add", method = RequestMethod.GET)
    public String viewAddRole(Model model) {
        Role role = new Role();
        model.addAttribute("role", role);
        return "admin/account/role/view-add";
    }

    @RequestMapping(value = "/admin/role/add", method = RequestMethod.POST)
    public String addRole(Role role) {
        service.addRole(role);
        return "redirect:/admin/role";
    }

    @RequestMapping(value = "/admin/role/detail/{idRole}", method = RequestMethod.GET)
    public String detailRole(Model model, @PathVariable("idRole") Long idRole) {
        Role detailRole = service.detailRole(idRole);
        model.addAttribute("detailRole", detailRole);
        return "admin/account/role/view-update";
    }

    @RequestMapping(value = "/admin/role/update", method = RequestMethod.POST)
    public String updateRole(Role role) {
        service.updateRole(role);
        return "redirect:/admin/role";
    }

    @RequestMapping(value = "/admin/role/delete/{idRole}", method = RequestMethod.GET)
    public String deleteRole(@PathVariable("idRole") Long idRole) {
        service.deleteRole(idRole);
        return "redirect:/admin/role";
    }

    // CONTROLLER ACCOUNT
    @RequestMapping(value = "/admin/account", method = RequestMethod.GET)
    public String viewAccount(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Accounts> itemAccount = service.getAllAccounts(PageRequest.of(page, 5));
        model.addAttribute("listAccount", itemAccount);
        model.addAttribute("currentPage", page);
        return "admin/account/user/viewAccount";
    }

    @RequestMapping(value = "/admin/account/view-add", method = RequestMethod.GET)
    public String viewAddAccount(Model model) {
        Accounts accounts = new Accounts();
        model.addAttribute("accounts", accounts);
        return "admin/account/user/view-add";
    }

    @RequestMapping(value = "/admin/account/add", method = RequestMethod.POST)
    public String addAccount(Accounts accounts) {
        service.addAccount(accounts);
        return "redirect:/admin/account";
    }

    @RequestMapping(value = "/admin/account/detail/{userName}", method = RequestMethod.GET)
    public String detailAccount(Model model, @PathVariable("userName") String userName) {
        Accounts detailAccount = service.detailAccount(userName);
        model.addAttribute("detailAccount", detailAccount);
        return "admin/account/user/view-update";
    }

    @RequestMapping(value = "/admin/account/update", method = RequestMethod.POST)
    public String updateAccount(Accounts accounts) {
        service.updateAccount(accounts);
        return "redirect:/admin/account";
    }

    @RequestMapping(value = "/admin/account/delete/{userName}", method = RequestMethod.GET)
    public String deleteAccount(@PathVariable("userName") String userName) {
        service.deleteAccount(userName);
        return "redirect:/admin/account";
    }

    // CONTROLLER AUTHORITY
    @RequestMapping(value = "/admin/authority", method = RequestMethod.GET)
    public String viewAuthority(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Authorities> itemAuthorities = service.getAllAuthorities(PageRequest.of(page, 5));
        model.addAttribute("listAuthority", itemAuthorities);
        model.addAttribute("currentPage", page);
        return "admin/account/authorities/viewAuthority";
    }

    @RequestMapping(value = "/admin/authority/view-add", method = RequestMethod.GET)
    public String viewAddAuthority(Model model) {
        Authorities authorities = new Authorities();
        listAccount = service.getAllAccount();
        listRole = service.getAllRoles();
        model.addAttribute("authorities", authorities);
        model.addAttribute("listAccount", listAccount);
        model.addAttribute("listRole", listRole);
        return "admin/account/authorities/view-add";
    }

    @RequestMapping(value = "/admin/authority/add", method = RequestMethod.POST)
    public String addAuthority(Authorities authorities) {
        service.addAuthorities(authorities);
        return "redirect:/admin/authority";
    }

    @RequestMapping(value = "/admin/authority/detail/{idAuthorities}", method = RequestMethod.GET)
    public String detailAuthority(Model model, @PathVariable("idAuthorities") Long idAuthorities) {
        Authorities detailAuthorities = service.detailAuthorities(idAuthorities);
        listAccount = service.getAllAccount();
        listRole = service.getAllRoles();
        model.addAttribute("detailAuthority", detailAuthorities);
        model.addAttribute("listAccount", listAccount);
        model.addAttribute("listRole", listRole);
        return "admin/account/authorities/view-update";
    }

    @RequestMapping(value = "/admin/authority/update", method = RequestMethod.POST)
    public String updateAuthority(Authorities authorities) {
        service.updateAuthorities(authorities);
        return "redirect:/admin/authority";
    }

    // CONTROLLER SAN PHAM
    @RequestMapping(value = "/admin/product", method = RequestMethod.GET)
    public String viewProduct(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Product> itemProduct = service.getAllProduct(PageRequest.of(page, 5));
        model.addAttribute("listProduct", itemProduct);
        model.addAttribute("currentPage", page);
        return "admin/detail/product/viewproduct";
    }

    @RequestMapping(value = "/admin/product/view-add", method = RequestMethod.GET)
    public String viewAddProduct(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "admin/detail/product/view-add";
    }

    @RequestMapping(value = "/admin/product/add", method = RequestMethod.POST)
    public String addProduct(Product product) {
        service.addProduct(product);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/admin/product/detail/{idProduct}", method = RequestMethod.GET)
    public String detailProduct(Model model, @PathVariable("idProduct") Long idProduct) {
        Product detailProduct = service.detailProduct(idProduct);
        model.addAttribute("detailProduct", detailProduct);
        return "admin/detail/product/view-update";
    }

    @RequestMapping(value = "/admin/product/update", method = RequestMethod.POST)
    public String updateProduct(Product product) {
        service.updateProduct(product);
        return "redirect:/admin/product";
    }

    @RequestMapping(value = "/admin/product/delete/{idProduct}", method = RequestMethod.GET)
    public String deleteProduct(@PathVariable("idProduct") Long idProduct) {
        service.deleteProduct(idProduct);
        return "redirect:/admin/product";
    }

    // CONTROLLER MAU SAC
    @RequestMapping(value = "/admin/color", method = RequestMethod.GET)
    public String viewColor(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Color> itemColor = service.getAllColor(PageRequest.of(page, 5));
        model.addAttribute("listColor", itemColor);
        model.addAttribute("currentPage", page);
        return "admin/detail/color/viewcolor";
    }

    @RequestMapping(value = "/admin/color/view-add", method = RequestMethod.GET)
    public String viewAddColor(Model model) {
        Color color = new Color();
        model.addAttribute("color", color);
        return "admin/detail/color/view-add";
    }

    @RequestMapping(value = "/admin/color/add", method = RequestMethod.POST)
    public String addColor(Color color) {
        service.addColor(color);
        return "redirect:/admin/color";
    }

    @RequestMapping(value = "/admin/color/detail/{idColor}", method = RequestMethod.GET)
    public String detailColor(Model model, @PathVariable("idColor") Long idColor) {
        Color detailColor = service.detailColor(idColor);
        model.addAttribute("detailColor", detailColor);
        return "admin/detail/color/view-update";
    }

    @RequestMapping(value = "/admin/color/update", method = RequestMethod.POST)
    public String updateColor(Color color) {
        service.updateColor(color);
        return "redirect:/admin/color";
    }

    @RequestMapping(value = "/admin/color/delete/{idColor}", method = RequestMethod.GET)
    public String deleteColor(@PathVariable("idColor") Long idColor) {
        service.deleteColor(idColor);
        return "redirect:/admin/color";
    }

    // CONTROLLER SIZE
    @RequestMapping(value = "/admin/size", method = RequestMethod.GET)
    public String viewSize(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Size> itemSize = service.getAllSize(PageRequest.of(page, 5));
        model.addAttribute("listSize", itemSize);
        model.addAttribute("currentPage", page);
        return "admin/detail/size/viewsize";
    }

    @RequestMapping(value = "/admin/size/view-add", method = RequestMethod.GET)
    public String viewAddSize(Model model) {
        Size size = new Size();
        model.addAttribute("size", size);
        return "admin/detail/size/view-add";
    }

    @RequestMapping(value = "/admin/size/add", method = RequestMethod.POST)
    public String addSize(Size size) {
        service.addSize(size);
        return "redirect:/admin/size";
    }

    @RequestMapping(value = "/admin/size/detail/{idSize}", method = RequestMethod.GET)
    public String detailSize(Model model, @PathVariable("idSize") Long idSize) {
        Size detailSize = service.detailSize(idSize);
        model.addAttribute("detailSize", detailSize);
        return "admin/detail/size/view-update";
    }

    @RequestMapping(value = "/admin/size/update", method = RequestMethod.POST)
    public String updateSize(Size size) {
        service.updateSize(size);
        return "redirect:/admin/size";
    }

    @RequestMapping(value = "/admin/size/delete/{idSize}", method = RequestMethod.GET)
    public String deleteSize(@PathVariable("idSize") Long idSize) {
        service.deleteSize(idSize);
        return "redirect:/admin/size";
    }

    // CONTROLLER CHI TIET SAN PHAM
    @RequestMapping(value = "/admin/productitems", method = RequestMethod.GET)
    public String viewProductItems(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<ProductItems> itemProductItems = service.getAllProductItems(PageRequest.of(page, 5));
        model.addAttribute("listProductItems", itemProductItems);
        model.addAttribute("currentPage", page);
        return "admin/detail/productItems/viewproductitems";
    }

    @RequestMapping(value = "/admin/productitems/view-add", method = RequestMethod.GET)
    public String viewAddProductItems(Model model) {
        ProductItems productItems = new ProductItems();
        listProduct = service.cbbProduct();
        listColor = service.cbbColor();
        listSize = service.cbbSize();
        listCategory = service.viewCategory();
        model.addAttribute("productItems", productItems);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listCategory", listCategory);
        return "admin/detail/productItems/view-add";
    }

    @RequestMapping(value = "/admin/productitems/add", method = RequestMethod.POST)
    public String addProductItems(ProductItems productItems) {
        service.addProductItems(productItems);
        return "redirect:/admin/productitems";
    }

    @RequestMapping(value = "/admin/productitems/detail/{idProductItems}", method = RequestMethod.GET)
    public String detailProductItems(Model model, @PathVariable("idProductItems") Long idProductItems) {
        ProductItems detailProductItems = service.detailProductItems(idProductItems);
        listProduct = service.cbbProduct();
        listColor = service.cbbColor();
        listSize = service.cbbSize();
        listCategory = service.viewCategory();
        model.addAttribute("detailProductItems", detailProductItems);
        model.addAttribute("listProduct", listProduct);
        model.addAttribute("listColor", listColor);
        model.addAttribute("listSize", listSize);
        model.addAttribute("listCategory", listCategory);
        return "admin/detail/productItems/view-update";
    }

    @RequestMapping(value = "/admin/productitems/update", method = RequestMethod.POST)
    public String updateProductItems(@RequestParam("file") MultipartFile file, ProductItems productItems) {
        try {
            service.updateProductItems(productItems, file);
            return "redirect:/admin/productitems";
        } catch (IOException e) {
            // Xử lý ngoại lệ ở đây
            return "error";
        }
    }

    @RequestMapping(value = "/admin/productitems/delete/{idProductItems}", method = RequestMethod.GET)
    public String deleteProductItems(@PathVariable("idProductItems") Long idProductItems) {
        service.deleteProductItems(idProductItems);
        return "redirect:/admin/productitems";
    }

    // CONTROLLER DANH MUC
    @RequestMapping(value = "/admin/category", method = RequestMethod.GET)
    public String viewCategory(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Category> itemCategory = service.getAllCategory(PageRequest.of(page, 5));
        model.addAttribute("listCategory", itemCategory);
        model.addAttribute("currentPage", page);
        return "admin/detail/category/viewcategory";
    }

    @RequestMapping(value = "/admin/category/view-add", method = RequestMethod.GET)
    public String viewAddCategory(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "admin/detail/category/view-add";
    }

    @RequestMapping(value = "/admin/category/add", method = RequestMethod.POST)
    public String addCategory(Category category) {
        service.addCategory(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/admin/category/detail/{idCategory}", method = RequestMethod.GET)
    public String detailCategory(Model model, @PathVariable("idCategory") Long idCategory) {
        Category detailCategory = service.detailCategory(idCategory);
        model.addAttribute("detailCategory", detailCategory);
        return "admin/detail/category/view-update";
    }

    @RequestMapping(value = "/admin/category/update", method = RequestMethod.POST)
    public String updateCategory(Category category) {
        service.updateCategory(category);
        return "redirect:/admin/category";
    }

    @RequestMapping(value = "/admin/category/delete/{idCategory}", method = RequestMethod.GET)
    public String deleteCategory(@PathVariable("idCategory") Long idCategory) {
        service.deleteCategory(idCategory);
        return "redirect:/admin/category";
    }

    // CONTROLLER VOURCHER
    @RequestMapping(value = "/admin/voucher", method = RequestMethod.GET)
    public String getAllVoucher(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Promotions> itemsPromotions = service.getAllPromotions(PageRequest.of(page, 5));
        model.addAttribute("listPromotions", itemsPromotions);
        model.addAttribute("currentPage", page);
        return "admin/voucher/promotions/viewPromotions";
    }


    @RequestMapping(value = "/admin/voucher/view-add", method = RequestMethod.GET)
    public String viewAddVoucher(Model model) {
        Promotions promotions = new Promotions();
        listProductItems = service.cbbProductItems();
        model.addAttribute("promotions", promotions);
        model.addAttribute("listProductItems", listProductItems);
        return "admin/voucher/promotions/view-add";
    }

    @RequestMapping(value = "/admin/voucher/add", method = RequestMethod.POST)
    public String addVoucher(Promotions promotions) {
        service.addPromotions(promotions);
        return "redirect:/admin/voucher";
    }

    @RequestMapping(value = "/admin/voucher/detail/{idPromotions}", method = RequestMethod.GET)
    public String detailPromotionsProduct(Model model, @PathVariable("idPromotions") Long idPromotions, @RequestParam(defaultValue = "0") int page) {
        Promotions promotions = service.detailPromotions(idPromotions);
        Page<PromotionsProduct> itemsPromotionsProducts = service.getAllPromotionsProducts(PageRequest.of(page, 5), promotions.getIdPromotions());
        model.addAttribute("detailPromotions", promotions);
        model.addAttribute("listPromotionsProducts", itemsPromotionsProducts);
        model.addAttribute("currentPage", page);
        model.addAttribute("idPromotions", idPromotions);
        return "admin/voucher/promotionsProduct/viewPromotionsProduct";
    }

    @RequestMapping(value = "/admin/voucher/update", method = RequestMethod.POST)
    public String updatePromotionsProduct(Promotions promotions) {
        service.updatePromotions(promotions);
        return "redirect:/admin/voucher";
    }

    @RequestMapping(value = "/admin/voucherdetail/view-add", method = RequestMethod.GET)
    public String addProductVoucher(Model model, @ModelAttribute("idPromotions") Long idPromotions) {
        PromotionsProduct promotionsProduct = new PromotionsProduct();
        Promotions promotions = service.detailPromotions(idPromotions);
        listProductItems = service.cbbProductItems();
        model.addAttribute("detailPromotions", promotions);
        model.addAttribute("promotionsProduct", promotionsProduct);
        model.addAttribute("listProductItems", listProductItems);
        return "admin/voucher/promotionsProduct/view-add";
    }

    @RequestMapping(value = "/admin/voucherdetail/add", method = RequestMethod.POST)
    public String addPromotionsProduct(PromotionsProduct promotionsProduct, RedirectAttributes redirectAttributes) {
        service.addPromotionsProduct(promotionsProduct);
        redirectAttributes.addAttribute("idPromotions", promotionsProduct.getPromotions().getIdPromotions());
        return "redirect:/admin/voucher/detail/{idPromotions}";
    }

    @RequestMapping(value = "/admin/voucherdetail/remove/{idPromotionsProduct}", method = RequestMethod.GET)
    public String deletePromotionsProduct(PromotionsProduct promotionsProduct, @PathVariable("idPromotionsProduct") Long idPromotionsProduct, RedirectAttributes redirectAttributes) {
        PromotionsProduct removedPromotionProduct = service.deletePromotionsProduct(idPromotionsProduct);
        if (removedPromotionProduct != null && removedPromotionProduct.getPromotions() != null && removedPromotionProduct.getPromotions().getIdPromotions() != null) {
            redirectAttributes.addAttribute("idPromotions", removedPromotionProduct.getPromotions().getIdPromotions());
        }
        return "redirect:/admin/voucher/detail/{idPromotions}";
    }

    // VIEW CUA THONG KE
}
