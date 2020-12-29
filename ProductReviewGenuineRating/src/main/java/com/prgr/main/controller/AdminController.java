package com.prgr.main.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prgr.main.entity.Person;
import com.prgr.main.entity.Product;
import com.prgr.main.service.PersonService;
import com.prgr.main.service.ProductService;

@RestController
@RequestMapping("/prgr/admin")
public class AdminController {

	@Autowired
	private PersonService personService;
	@Autowired
	private ProductService productService;

	/**
	 *  This method accpets login credentials from admin
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
	 */
	@GetMapping("/allusers")
	public ResponseEntity<List<Person>> getAllPerson() {
		List<Person> personList = personService.getAllPerson();
		if (personList.isEmpty()) {
			return new ResponseEntity("No Person Found.", HttpStatus.NOT_FOUND);
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
	public Product addProduct(@RequestBody Product product) {
		Product add = productService.addProduct(product);
		return add;
	}

	/**
	 * It takes product ID and pass it to the service layer deleteProduct() method.
	 * @param product Id
	 * @return ResponseEntity<String>
	 */
	
	@DeleteMapping(value = "/deleteproduct")
	public Product deleteProduct(@RequestParam("productId") int id) {
		Product delete = productService.deletProduct(id);
		return delete;
	}

	/**
	 * It accepts product details from admin and pass it to
	 * the service layer updateProduct() method.
	 * @param product
	 * @return Product Object
	 */
	@PutMapping(value = "/updateproduct")
	public Product updateProduct(@RequestBody Product product) {
		Product update = productService.updateProduct(product);
		return update;
	}

	/**
	 * It will fetch product details based on id
	 * by passing control to service layer by calling getAllProduct()
	 * @param product id
	 * @return Product object
	 */
	@GetMapping(value = "/getproductbyid")
	public Product getProductById(@RequestParam("productId") int id) {
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

}
