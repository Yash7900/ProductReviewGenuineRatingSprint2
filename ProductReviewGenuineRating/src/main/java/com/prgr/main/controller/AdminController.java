package com.prgr.main.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

	/*******************************************************************************************
	 * - Function Name : adminLogin - Input Parameters :username,passsword(request from
	 * admin) - Return Type : ResponseEntity<String> - Description : get boolean from the service 
	 * layer (T/F).Based on correct credentials.
	 *******************************************************************************************/

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
	/*******************************************************************************************
	 * - Function Name : getAllPerson - Input Parameters : empty - Return Type :
	 * List<Person> object - Description : it will fetch all the person details
	 * from database by passing control to service by calling getAllProduct()
	 * 
	 *******************************************************************************************/

	
	@GetMapping("/allusers")
	public ResponseEntity<List<Person>> getAllPerson() {
		List<Person> personList = personService.getAllPerson();
		if (personList.isEmpty()) {
			return new ResponseEntity("No Person Found.", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Person>>(personList, HttpStatus.OK);
		}
	}

	/*******************************************************************************************
	 * - Function Name : addProduct - Input Parameters : product object(request from
	 * client) - Return Type : product object - Description : get the request from
	 * client as an object (use of @RequestBody annotation) and controller controls
	 * flow to service by calling addProduct()
	 * 
	 *******************************************************************************************/

	@RequestMapping(value = "/addproduct", method = RequestMethod.POST)
	public Product addProduct(@RequestBody Product product) {
		Product add = productService.addProduct(product);
		return add;
	}

	/*******************************************************************************************
	 * - Function Name : deleteProduct - Input Parameters : id object(request from
	 * client) - Return Type : product object - Description : delete the product
	 * from the database by calling deleteProduct()
	 * 
	 *******************************************************************************************/

	@RequestMapping(value = "/deleteproduct/{id}", method = RequestMethod.DELETE)
	public Product deleteProduct(@PathVariable int id) {
		Product delete = productService.deletProduct(id);
		return delete;
	}

	/*******************************************************************************************
	 * - Function Name : updateProduct - Input Parameters : product object(request
	 * from client) - Return Type : product object - Description : update product
	 * database when departmentId the matches in database by calling updateProduct()
	 * 
	 *******************************************************************************************/

	@RequestMapping(value = "/updateproduct", method = RequestMethod.PUT)
	public Product updateProduct(@RequestBody Product product) {
		Product update = productService.updateProduct(product);
		return update;
	}

	/*******************************************************************************************
	 * - Function Name : getProductById - Input Parameters : id object(request from
	 * client) - Return Type : product object - Description : it will fetch all the
	 * product details (use of @PathVariable annotation) based on id from database
	 * by passing control to service by calling getProductById()
	 * 
	 * 
	 *******************************************************************************************/

	@RequestMapping(value = "/getproductbyid/{id}")
	public Product getProductById(@PathVariable int id) {
		Product product = productService.getProductById(id);
		return product;
	}

	/*******************************************************************************************
	 * - Function Name : getProductList - Input Parameters : empty - Return Type :
	 * List<product> object - Description : it will fetch all the product details
	 * from database by passing control to service by calling getProductList()
	 * 
	 *******************************************************************************************/

	@RequestMapping(value = "/getallproduct")
	public List<Product> getProductList() {
		List<Product> productList = productService.getProductList();
		return productList;
	}

	/*******************************************************************************************
	 * - Function Name : getProductCategory - Input Parameters : category
	 * object(request from client) - Return Type : List<Product> object -
	 * Description : it will fetch all the product details (use of @PathVariable
	 * annotation) based on category from database by passing control to service by
	 * calling getProductByCategory()
	 * 
	 *******************************************************************************************/

	@RequestMapping(value = "/getproductbycategory/{category}")
	public List<Product> getProductByCategory(@PathVariable String category) {
		List<Product> list = productService.getProductByCategory(category);
		return list;
	}

}
