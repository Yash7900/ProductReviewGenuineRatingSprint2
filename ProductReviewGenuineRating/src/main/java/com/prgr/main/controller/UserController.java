package com.prgr.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.prgr.main.service.FeedbackService;
import com.prgr.main.service.PersonService;
import com.prgr.main.service.ProductService;

@RestController
@RequestMapping("/prgr/user")
public class UserController {
	
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	/**
	 * This method is used to register user.It accepts details of user
	 * and pass the control to the service layer addPerson() method.
	 * @param 
	 * @return ResponseEntity<Person>
	 */
	@PostMapping("/addperson")
	public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person){
		Person savePerson=personService.addPerson(person);
		if (savePerson == null) {
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(savePerson, HttpStatus.OK);
		
	}
	/**
	 *  This method accepts login credentials from user
	 * and pass that credentials to service layer loginPerson() method.
	 * @param 
	 * @return  ResponseEntity<String>
	 */
	@GetMapping("/login")
	public ResponseEntity<String> adminLogin(@RequestParam("email")String email,@RequestParam("password")String password) {
		boolean login=personService.loginPerson(email, password);
		if(login) {
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Login Failed", HttpStatus.NOT_FOUND);
		}
	}
	/**
	 * It accepts details from user and pass it to
	 * the service layer updatePerson() method.
	 * @param 
	 * @return ResponseEntity<Person> 
	 */
	@PutMapping("/updateperson")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person){
		Person updtPerson=personService.updatePerson(person);
		if (updtPerson == null) {
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Person>(updtPerson, HttpStatus.OK);
		
	}
	
	/**
	 * It will fetch product details based on id
	 * by passing control to service layer by calling getAllProduct()
	 * @param product id
	 * @return Product object
	 */
	@GetMapping(value = "/getproductbyid/{productId}")
	public Product getProductById(@PathVariable("productId") int id) {
		Product product = productService.getProductById(id);
		return product;
	}

	
	/**
	 * It will fetch all the products
	 * from database by passing control to service layer by calling getAllProductList()
	 * @param empty
	 * @return List<Product> object
	 */
	@GetMapping(value = "/getallproduct")
	public List<Product> getProductList() {
		List<Product> productList = productService.getProductList();
		return productList;
	}

	/**
	 * It will fetch all the products based on category
	 * by passing control to service layer by calling getProductByCategory()
	 * @param category
	 * @return List<Person> object
	 */

	@GetMapping(value = "/getproductbycategory")
	public List<Product> getProductByCategory(@RequestParam("category") String category) {
		List<Product> list = productService.getProductByCategory(category);
		return list;
	}

	/**
	 * This method adds feedback in the repository.
	 * @param feedback
	 * @return ResponseEntity<Feedback>
	 */
	@PostMapping("/add")
	public ResponseEntity<Feedback> addFeedback(@RequestBody Feedback feedback) {
		Feedback addFeedback = feedbackService.addFeedback(feedback);
		return new ResponseEntity<Feedback>(addFeedback,HttpStatus.OK);
	}

}
