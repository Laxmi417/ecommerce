package com.sheryians.major.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sheryians.major.model.Category;
import com.sheryians.major.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
CategoryRepository categorycepository ;
	public List<Category> getAllCategery(){
		return categorycepository.findAll();
		
	}
	public void addCategory(Category category) {
		categorycepository.save(category);
	}
	public void removeCategoryById(int id) {
		categorycepository.deleteById(id);
	}
	public Optional<Category>getCategoryById(int id) {
		return categorycepository.findById(id);
	}
}
