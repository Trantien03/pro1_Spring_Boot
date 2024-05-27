package com.example.pro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class ProductController {
    //call model step 2
    @Autowired
    private ProductRepository productRepository;

    //step 1
    @GetMapping("/")
    public String productList(Model model){
        List<Product> products= productRepository.findAll();
        //step 3
        model.addAttribute("products",products);
        return "product-list";
    }
    @GetMapping("/add")
    public String showAddForm(Model model){
        model.addAttribute("product",new Product());
        return "add-product";
    }
    @PostMapping("add")
    public String addProduct(@ModelAttribute Product product){
        productRepository.save(product);
        return "redirect:/";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model){
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            model.addAttribute("product", product.get());
            return "edit-product";
        }else{
            return "redirect:/";
        }
    }
    @PostMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") Long id, @ModelAttribute Product product){
        product.setId(id);
        productRepository.save(product);
        return "redirect:/";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productRepository.deleteById(id);
        return "redirect:/";
    }
}
