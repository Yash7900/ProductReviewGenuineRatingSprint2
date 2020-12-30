package com.prgr.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prgr.main.entity.Feedback;
import com.prgr.main.entity.Person;
import com.prgr.main.entity.Product;
import com.prgr.main.exception.FeedbackNotFoundException;
import com.prgr.main.exception.ProductException;
import com.prgr.main.exception.UserNotFoundException;
import com.prgr.main.service.FeedbackService;
import com.prgr.main.service.PersonService;
import com.prgr.main.service.ProductService;

@RestController
@RequestMapping("/prgr/admin")
public class AdminController {

	@Autowired
	private PersonService personService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FeedbackService feedbackService;

	/**
	 *  This method accepts login credentials from admin
	 * and pass that credentials to service layer loginAdmin() method.
	 * @param 
	 * @return  ResponseEntity<String>
	 */
	@GetMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestParam("username") String username,
			@RequestParam("password") String password) {
		boolean login = personService.loginAdmin(username, password);
		if (login) {
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Login Failed", HttpStatus.NOT_FOUND);
		}
	}
	
	/**
	 * It will fetch all the person details
	 * from database by passing control to service by calling getAllPerson()
	 * @param empty
	 * @return List<Person> object
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/allusers")
	public ResponseEntity<List<Person>> getAllPerson() throws UserNotFoundException {
		List<Person> personList = personService.getAllPerson();
		if (personList.isEmpty()) {
			throw new UserNotFoundException("No Users Found.");
		} else {
			return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
		}
	}

	
	/**
	 * It accepts product details from admin and pass it to
	 * the service layer addProduct() method.
	 * @param product
	 * @return Product Object
	 */

	@PostMapping(value = "/addproduct")
	public  ResponseEntity<Product> addProduct(@Valid @RequestBody Product product) throws ProductException {
		
		if (product.getProductId()==0&&product.getProductName()!=null&&product.getSellerName()!=null&&product.getCategory()!=null&&product.getDescription()!=null&&product.getPrice()!=0) {
			return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
		}
		else {
			throw new ProductException("Product cannot be added");
		}
	}
	/**
	 * It takes product ID and pass it to the service layer deleteProduct() method.
	 * @param product Id
	 * @return ResponseEntity<String>
	 */
	
	@DeleteMapping(value = "/deleteproduct/{productId}")
	public  ResponseEntity<Product> deleteProduct(@PathVariable("productId") int id) throws ProductException{
		if(productService.getProductById(id)!=null) {
			return new ResponseEntity<Product>( productService.deletProduct(id), HttpStatus.OK);
		}
		else {
			throw new ProductException("Product cannot be deleted, as id "+id+" not present");
		}
}

	/**
	 * It accepts product details from admin and pass it to
	 * the service layer updateProduct() method.
	 * @param product
	 * @return Product Object
	 */
	@PutMapping(value = "/updateproduct")
	public ResponseEntity<Product> updateProduct(@RequestBody Product product) throws ProductException{
		//	Product update = productService.updateProduct(product);
			if(productService.getProductById(product.getProductId())!=null) {
				return new ResponseEntity( productService.updateProduct(product),HttpStatus.OK);
			}
			else {
				throw new ProductException("Product could not be updated,as id "+product.getProductId()+" not present");
			}
			
		}

	/**
	 * It will fetch product details based on id
	 * by passing control to service layer by calling getAllProduct()
	 * @param product id
	 * @return Product object
	 */
	@GetMapping(value = "/getproductbyid/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId") int id) throws ProductException {
		 Product product = productService.getProductById(id);
			if(productService.getProductById(id)!=null) {
			 return new ResponseEntity<Product>(product, HttpStatus.OK);
		 }
		 else {
			 throw new ProductException("No Product Found by this id");
		 }
			
	}

	
	/**
	 * It will fetch all the products
	 * from database by passing control to service layer by calling getAllProductList()
	 * @param empty
	 * @return List<Product> object
	 */
	@GetMapping(value = "/getallproduct")
	public ResponseEntity<List<Product>> getProductList() throws ProductException{
		List<Product> productList = productService.getProductList();
		if (!productList.isEmpty()) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			throw new ProductException("No Product Found");
		}		
		
	}


	/**
	 * It will fetch all the products based on category
	 * by passing control to service layer by calling getProductByCategory()
	 * @param category
	 * @return List<Person> object
	 */

	@GetMapping(value = "/getproductbycategory/{category}")
	public  ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) throws ProductException{
		List<Product> list = productService.getProductByCategory(category);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			throw new ProductException("No Product Found by this category");
		}
	}
	
	/**
	 * This method display all the feedbacks from database.
	 * @return ResponseEntity<List<Feedback>>
	 * @throws FeedbackNotFoundException 
	 */
	@GetMapping("/getAllfeedback")
	public ResponseEntity<List<Feedback>> getAllFeedback() throws FeedbackNotFoundException
	{
		List<Feedback> feedbackList=feedbackService.viewAllFeedback();
		if (!feedbackList.isEmpty()) {
			return new ResponseEntity<List<Feedback>>(feedbackList,HttpStatus.OK);
		} else {
			throw new FeedbackNotFoundException("No feedbacks Found");
		}	
		
	}
	
	/**
	 * This method delete feedback by id parameter.
	 * @param id
	 * @return ResponseEntity<Feedback>
	 */
	@DeleteMapping("/deletefeedback/{id}")
	public ResponseEntity<Feedback> deleteFeedback(@PathVariable("id") Integer feedbackId)throws FeedbackNotFoundException
	{
		boolean feedbackPresent=feedbackService.getFeedbackById(feedbackId);
		if(feedbackPresent) {
			feedbackService.deleteFeedback(feedbackId);
			return new ResponseEntity("feedback deleted",HttpStatus.OK);
		}
		else {
			throw new FeedbackNotFoundException("Feedback cannot be deleted, as id "+feedbackId+" not present");
		}
	}

}
