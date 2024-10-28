package com.viendong.webbanhang.controller;

import com.viendong.webbanhang.model.Brand;
import com.viendong.webbanhang.model.Category;
import com.viendong.webbanhang.service.BrandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BrandController {
    @Autowired
    private final BrandService brandService;

    @GetMapping("/brand/add")
    public String showAddForm(Model model) {
        model.addAttribute("brand", new Brand());
        return "/brand/add-brand";
    }
    @PostMapping("/brand/add")
    public String addCategory(@Valid Brand brand, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "/brand/add-brand";
        }
        brandService.addBrand(brand);
        return "redirect:/brand";
    }

    // Hiển thị danh sách danh mục
    @GetMapping("/brand")
    public String listbrand (Model model) {
        List<Brand> brand = brandService.getAllBrand();
        model.addAttribute("brand", brand);
        return "/brand/brand-list";
    }

    // GET request to show category edit form
    @GetMapping("/brand/edit/{id}")
    public String showUpdateForm(@PathVariable("id") Long id, Model model) {
        Brand brand = brandService.getBrandById(id).orElseThrow(() -> new IllegalArgumentException("Invalid brand Id:" + id));
        model.addAttribute("brand",brand);
        return "/brand/update-brand";
    }

    // POST request to update category
    @PostMapping("/brand/update/{id}")
    public String updateBrand(@PathVariable("id") Long id, @Valid Brand brand, BindingResult result, Model model) {
        if (result.hasErrors()) {
            brand.setId(id);
            return "/brand/update-brand";
        }
       brandService.updateBrand(brand);
        model.addAttribute("brand", brandService.getAllBrand());
        return "redirect:/brand";
    }

    // GET request for deleting category
    @GetMapping("/brand/delete/{id}")
    public String deleteBrand(@PathVariable("id") Long id, Model model) {
       Brand brand = brandService.getBrandById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        brandService.deleteBrand(id);
        model.addAttribute("brand", brandService.getAllBrand());
        return "redirect:/brand";
    }
}
