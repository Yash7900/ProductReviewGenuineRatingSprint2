package com.prgr.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.prgr.main.toc.CompareProduct;
import com.prgr.main.toc.Message;

@CrossOrigin("*")
@RestController
@RequestMapping("api/prgr/user")
/**
 * User Controller class
 * @author YASH
 *
 */
public class UserController {
	private static final Logger LOGGER=LoggerFactory.getLogger(UserController.class);
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
	public ResponseEntity<Person> addPerson(@Valid @RequestBody final Person person){
		LOGGER.info("user registration");
		Person savePerson=personService.addPerson(person);
		if (savePerson == null) {
			LOGGER.info("user registration Failed");
			return new ResponseEntity("Registration Failed", HttpStatus.NOT_FOUND);
		}
		LOGGER.info("user Successfully registered");
		return new ResponseEntity<Person>(savePerson, HttpStatus.OK);
		
	}
	

	
	
	/**
	 * It accepts details from user and pass it to
	 * the service layer updatePerson() method.
	 * @param 
	 * @return ResponseEntity<Person> 
	 */
	@PutMapping("/updateperson")
	public ResponseEntity<Person> updatePerson(@Valid @RequestBody final  Person person)throws UserNotFoundException{
		LOGGER.info("User updates details");
		boolean savePerson=personService.getPerson(person.getPersonId());
		
		if (savePerson) {
			Person updtPerson=personService.updatePerson(person);
			return new ResponseEntity<Person>(updtPerson, HttpStatus.OK);
		}
		else {
			LOGGER.error("Person cannot be updated, as id "+person.getPersonId()+" not present");
			throw new UserNotFoundException("Person cannot be updated, as id "+person.getPersonId()+" not present");
		}
	}
	
	/**
	 * It will fetch product details based on id
	 * by passing control to service layer by calling getAllProduct()
	 * @param product id
	 * @return Product object
	 */
	@GetMapping("/getproductbyid/{productId}")
	public ResponseEntity<Product> getProductById(@PathVariable("productId")final int productId) throws ProductException {
		LOGGER.info("User views product");
		 if(productService.getProductById(productId)!=null) {
			Product product = productService.getProductById(productId);
			 return new ResponseEntity<Product>(product, HttpStatus.OK);
		 }
		 else {
			 LOGGER.error("No Product Found by this id");
			 throw new ProductException("No Product Found by this id");
		 }
			
	}


	
	/**
	 * It will fetch all the products
	 * from database by passing control to service layer 
	 * by calling getAllProductList()
	 * @param empty
	 * @return List<Product> object
	 */
	@GetMapping("/getallproduct")
	public ResponseEntity<List<Product>> getProductList() throws ProductException{
		LOGGER.info("User views all products");
		List<Product> productList = productService.getProductList();
		if (!productList.isEmpty()) {
			return new ResponseEntity<List<Product>>(productList, HttpStatus.OK);
		} else {
			LOGGER.error("No Product Found");
			throw new ProductException("No Product Found");
		}		
		
	}

	/**
	 * It will fetch all the products based on category
	 * by passing control to service layer by calling getProductByCategory()
	 * @param category
	 * @return List<Person> object
	 */

	@GetMapping("/getproductbycategory/{category}")
	public  ResponseEntity<Message> getProductByCategory(@PathVariable("category")final String category) throws ProductException{
		LOGGER.info("admin views products by category");
		Message msg=new Message();
		msg.setResMessage("Successfully returning of list");
		msg.setProductList(productService.getProductByCategory(category));
		List<Product> productsList = productService.getProductByCategory(category);
		if (!productsList.isEmpty()) {
			return new ResponseEntity<Message>(msg,HttpStatus.OK);
		} else {
			LOGGER.error("No Product Found");
			throw new ProductException("No Product Found");
		}	
	}

	/**
	 * This method adds feedback in the repository.
	 * @param feedback
	 * @return ResponseEntity<Feedback>
	 */
	@PostMapping("/addfeedback")
	public ResponseEntity<Feedback> addFeedback(@Valid @RequestBody final Feedback feedback) {
		LOGGER.info("User adds Feedback");
		Feedback addFeedback = feedbackService.addFeedback(feedback);
		if (addFeedback == null) {
			LOGGER.info("Failed to add Feedback");

			return new ResponseEntity("Failed to add Feedback", HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Feedback>(addFeedback,HttpStatus.OK);
	}
	
	/**
	 * This method add review to a product by giving userId,productId & review
	 * to service layer addReviewForProduct() method.
	 * @return ResponseEntity<Review>
	 * @throws ProductException 
	 */
	@PostMapping("/addreviewforproduct/{userid}/product/{productid}")
	public ResponseEntity<Review> addReviewForProduct(@PathVariable("userid")final int userId,@PathVariable("productid")final int productId,@Valid @RequestBody final Review review) throws ProductException {
		LOGGER.info("User adds Review For product");
		if(productService.getProductById(productId)!=null) {
		boolean checkReview=reviewService.findByUserIdAndProdId(userId, productId);
		if(checkReview) {
		Review addReview = reviewService.addReviewForProduct(review,productId,userId);
		return new ResponseEntity<Review>(addReview,HttpStatus.OK);
		}
		else {
			LOGGER.info("You have already given review for this product");
			return new ResponseEntity("You have already given review for this product",HttpStatus.OK);
		}
		}
		else {
			LOGGER.error("No Product Found");
			throw new ProductException("No Product Found");
		}
	
	}
	@GetMapping("/compareproduct/product1/product2/category")
	public ResponseEntity<CompareProduct> compareTwoProductBasedOnCategory(@RequestParam final int productId1,@RequestParam final int productId2,@RequestParam final String category) throws ProductException {
		if(productService.getProductById(productId1)!=null && productService.getProductById(productId2)!=null) {
		CompareProduct compareProducts=productService.compareTwoProductBasedOnCategory(category, productId1, productId2);
		if(compareProducts.getProduct1()!=null && compareProducts.getProduct2()!=null) {
			return new ResponseEntity<CompareProduct>(compareProducts,HttpStatus.OK);
		}
		else {
			LOGGER.error("No Product Found by this category");
			throw new ProductException("No Product Found by this category");
		}
	}
		else {
			LOGGER.error("No Product Found");
			throw new ProductException("No Product Found by the given Id.");
		}
	}
	
	@GetMapping("category")
	public ResponseEntity<List<String>> getCategory(){
		return new ResponseEntity<List<String>>(productService.getCategory(),HttpStatus.OK);
	}

}
