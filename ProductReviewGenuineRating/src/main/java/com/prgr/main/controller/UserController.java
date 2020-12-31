package com.prgr.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.prgr.main.entity.Review;
import com.prgr.main.exception.ProductException;
import com.prgr.main.exception.UserNotFoundException;
import com.prgr.main.service.FeedbackService;
import com.prgr.main.service.PersonService;
import com.prgr.main.service.ProductService;
import com.prgr.main.service.ReviewService;

@RestController
@RequestMapping("/prgr/user")
/**
 * User Controller class
 * @author YASH
 *
 */
public class UserController {
	private static final Logger logger=LoggerFactory.getLogger(UserController.class);
	@Autowired
	private PersonService personService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private FeedbackService feedbackService;
	
	@Autowired
	private ReviewService reviewService;
	
	/**
	 * This method is used to register user.It accepts details of user
	 * and pass the control to the service layer addPerson() method.
	 * @param 
	 * @return ResponseEntity<Person>
	 */
	@PostMapping("/addperson")
	public ResponseEntity<Person> addPerson(@Valid @RequestBody Person person){
		logger.info("user registration");
		Person savePerson=personService.addPerson(person);
		if (savePerson == null) {
			logger.info("user registration Failed");
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		logger.info("user Successfully registered");
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
		logger.info("user Login");
		if(login) {
			logger.info("user Login Successful");
			return new ResponseEntity<String>("Login Successful", HttpStatus.OK);
			
		}
		else {
			logger.info("user Login Failed");
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
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody Person person)throws UserNotFoundException{
		logger.info("User updates details");
		boolean savePerson=personService.getPerson(person.getPersonId());
		
		if (savePerson) {
			Person updtPerson=personService.updatePerson(person);
			return new ResponseEntity<Person>(updtPerson, HttpStatus.OK);
		}
		else {
			logger.error("Person cannot be updated, as id "+person.getPersonId()+" not present");
			throw new UserNotFoundException("Person cannot be updated, as id "+person.getPersonId()+" not present");
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
		logger.info("User views product");
		 Product product = productService.getProductById(id);
			if(productService.getProductById(id)!=null) {
			 return new ResponseEntity<Product>(product, HttpStatus.OK);
		 }
		 else {
			 logger.error("No Product Found by this id");
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
		logger.info("User views all products");
		List<Product> productList = productService.getProductList();
		if (!productList.isEmpty()) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			logger.error("No Product Found");
			throw new ProductException("No Product Found");
		}		
		
	}

	/**
	 * It will fetch all the products based on category
	 * by passing control to service layer by calling getProductByCategory()
	 * @param category
	 * @return List<Person> object
	 */

	@GetMapping(value = "/getproductbycategory")
	public  ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category") String category) throws ProductException{
		logger.info("User views products by category");
		List<Product> list = productService.getProductByCategory(category);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			logger.error("No Product Found by this category");
			throw new ProductException("No Product Found by this category");
		}
	}

	/**
	 * This method adds feedback in the repository.
	 * @param feedback
	 * @return ResponseEntity<Feedback>
	 */
	@PostMapping("/addfeedback")
	public ResponseEntity<Feedback> addFeedback(@Valid @RequestBody Feedback feedback) {
		logger.info("User adds Feedback");
		Feedback addFeedback = feedbackService.addFeedback(feedback);
		if (addFeedback == null) {
			logger.info("Failed to add Feedback");

			return new ResponseEntity("Failed to add Feedback", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Feedback>(addFeedback,HttpStatus.OK);
	}
	
	/**
	 * This method add review to a product by giving userId,productId & review
	 * to service layer addReviewForProduct() method.
	 * @param userId
	 * @param productId
	 * @param review
	 * @return ResponseEntity<Review> 
	 */
	@PostMapping("/addreviewforproduct/{userid}/product/{productid}")
	public ResponseEntity<Review> addReviewForProduct(@PathVariable("userid")int userId,@PathVariable("productid")int productId,@Valid @RequestBody Review review) {
		logger.info("User adds Review For product");
		boolean checkReview=reviewService.findByUserIdAndProdId(userId, productId);
		if(checkReview) {
		Review addReview = reviewService.addReviewForProduct(review,productId,userId);
		return new ResponseEntity<Review>(addReview,HttpStatus.OK);
		}
		else {
			logger.info("You have already given review for this product");
			return new ResponseEntity("You have already given review for this product",HttpStatus.OK);
		}
	}

}
