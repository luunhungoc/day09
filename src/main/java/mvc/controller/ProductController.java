package mvc.controller;

import mvc.entity.ProductEntity;
import mvc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;


@Controller
@RequestMapping(value = "/")
public class ProductController {
    @Autowired
    ProductRepository productRepository;


    @RequestMapping(method = GET)
    public String showProducts(Model model) {
        List<ProductEntity> productList = (List<ProductEntity>) productRepository.findAll();
        model.addAttribute("productList", productList);
        return "cart/productList";
    }

//    @RequestMapping(value = "/search", method = GET)
//    public String search(@RequestParam("searchInput") String searchInput, Model model) {
//        List<ProductEntity> resultList;
//        if (searchInput.isEmpty()) {
//            resultList = (List<ProductEntity>) productRepository.findAll();
//        } else {
//            resultList = productRepository.findByNameContainingOrIdContaining(searchInput, Integer.valueOf(searchInput));
//        }
//        model.addAttribute("productList", resultList);
//        return "book/productList";
//    }

}
