package com.sheryians.major.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sheryians.major.dto.productsDTO;
import com.sheryians.major.model.Category;
import com.sheryians.major.model.Product;
import com.sheryians.major.service.CategoryService;
import com.sheryians.major.service.ProductService;

@Controller
 class AdminController {
	public static String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static.productImages";

	@Autowired
	CategoryService categoryservice;
	@Autowired
	ProductService productservice;
  @GetMapping("/admin")
  public String adminHome() {
   return "adminHome";
  }
  @GetMapping("/admin/categories")
  public String getCat(Model model) {
	  model.addAttribute("categories",categoryservice.getAllCategery());
	return "categories";
	  
  }
  @GetMapping("/admin/categories/add")
  public String getCatAdd(Model model) {
	  model.addAttribute("category",new Category());
	return "categoriesAdd";
	  
  }
  @PostMapping("/admin/categories/add")
  public String postCatAdd(@ModelAttribute("category")Category category) {
	 categoryservice.addCategory(category);
	return "redirect:/admin/categories";
	  
  }
  @GetMapping("/admin/categories/delete/{id}")
  public String deleteCat(@PathVariable int id) {
	  categoryservice.removeCategoryById(id);
	 return "redirect:/admin/categories";
  }
  @GetMapping("/admin/categories/update/{id}")
  public String updateCat(@PathVariable int id ,Model model) {
	  Optional<Category> category =categoryservice.getCategoryById(id);
	  if(category.isPresent()) {
		  model.addAttribute("category",category.get());
		  return"categoriesAdd";
	  }else
		  return"404";
	  }
  
  //product section
  @GetMapping("/admin/products")
  public String products(Model model) {
	  List<Product> products = productservice.getAllProduct();
	
	    model.addAttribute("products", products); // Ensure the attribute name matches the template
	    return "products"; // Ensure this matches your Thymeleaf template name
	}
  @GetMapping("/admin/products/add")
  public String productAddGet(Model model) {
	  model.addAttribute("productsDTO",new productsDTO());
	  model.addAttribute("categories",categoryservice.getAllCategery());
	 return "productsAdd";
  }
 @PostMapping("/admin/products/add")
	 public String productAddPost(@ModelAttribute("productsDTO") productsDTO productsDTO,
			                                        @RequestParam("productImage")MultipartFile file ,
	                                                @RequestParam("imageName")String imgName )throws IOException{
	 Product   product= new Product() ;  
	 product.setProduct_id(productsDTO.getProduct_id());
	 product.setProduct_name(productsDTO.getProduct_name());
	 product.setCategory(categoryservice.getCategoryById(productsDTO.getCategory_id()).get());
	 product.setProduct_price(productsDTO.getProduct_price());
	 product.setProduct_weight(productsDTO.getProduct_weight());
	 product.setDescription(productsDTO.getDescription());
	 String imageUUID;
	 if (!file.isEmpty()) {
		    imageUUID = file.getOriginalFilename();
		    Path fileNamePath = Paths.get(uploadDir, imageUUID);
		 //   Files.copy(file.getInputStream(), fileNamePath, StandardCopyOption.REPLACE_EXISTING);
		    Files.write(fileNamePath, file.getBytes());
		} else {
		    imageUUID = imgName; 
		}

	 product.setImageName(imageUUID);
	 productservice.addproduct(product);
	
		return"redirect:/admin/products"; 
	 }
 @GetMapping("/admin/product/delete/{id}")
 public String deleteproduct(@PathVariable int id) {
	 productservice.removeProductById(id);
	 return "redirect:/admin/products";
 }
 @GetMapping("/admin/product/update/{id}")
	 public String updateProduct(@PathVariable long id, Model model){
	 
	 Product product=productservice.getProductById(id).get();
	 productsDTO productDTO= new productsDTO();
	 
	 productDTO.setProduct_id(product.getProduct_id());
	 productDTO.setProduct_name(product.getProduct_name());
	 productDTO.setCategory_id(product.getCategory().getId());
	 productDTO.setProduct_price(product.getProduct_price());
	 productDTO.setProduct_weight(product.getProduct_weight());
	 productDTO.setDescription(product.getDescription());
	 productDTO.setImageName(product.getImageName());
	 model.addAttribute("categories",categoryservice.getAllCategery());
	 model.addAttribute("productsDTO", productDTO);
	 return"productsAdd";
	 
	 
 }
 
 {
 }
  }
  
  

