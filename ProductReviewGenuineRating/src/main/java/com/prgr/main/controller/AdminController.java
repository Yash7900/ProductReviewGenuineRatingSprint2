package com.prgr.main.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import com.prgr.main.entity.Product;
import com.prgr.main.exception.FeedbackNotFoundException;
import com.prgr.main.exception.ProductException;
import com.prgr.main.exception.ReviewNotFoundException;
import com.prgr.main.exception.UserNotFoundException;
import com.prgr.main.service.FeedbackService;
import com.prgr.main.service.PersonService;
import com.prgr.main.service.ProductService;
import com.prgr.main.service.ReviewService;
import com.prgr.main.toc.Message;

@CrossOrigin("*")
@RestController
@RequestMapping("/prgr/admin")
/**
 * Admin Controller Class
 * @author YASH
 *
 */
public class AdminController {
	private static final Logger LOGGER=LoggerFactory.getLogger(AdminController.class); //initializing Logger.
	@Autowired
	private PersonService personService;
	@Autowired
	private ProductService productService;
	@Autowired
	private FeedbackService feedbackService;
	@Autowired
	private ReviewService reviewService;
	
	
	/**
	 * It will fetch all the person details
	 * from database by passing control to service by calling getAllPerson()
	 * @param empty
	 * @return List<Person> object
	 * @throws UserNotFoundException 
	 */
	@GetMapping("/allusers")
	public ResponseEntity<Message> getAllPerson() throws UserNotFoundException {
		LOGGER.info("admin view all user");
		Message msg=new Message();
		msg.setResMessage("SuccessFully Fetching Details.");
		msg.setPersonList(personService.getAllPerson());
		if (msg.getPersonList().isEmpty()) {
			LOGGER.error("No Users Found.");
			throw new UserNotFoundException("No Users Found.");
		} else {
			return new ResponseEntity<Message>(msg, HttpStatus.OK);
		}
	}

	
	/**
	 * It accepts product details from admin and pass it to
	 * the service layer addProduct() method.
	 * @param product
	 * @return Product Object
	 */

	@PostMapping("/addproduct")
	public  ResponseEntity<Product> addProduct(@Valid @RequestBody final Product product) throws ProductException {
		LOGGER.info("admin add product");
		if (product.getProductId()==0&&product.getProductName()!=null&&product.getSellerName()!=null&&product.getCategory()!=null&&product.getDescription()!=null&&product.getPrice()!=0) {
			return new ResponseEntity<Product>(productService.addProduct(product), HttpStatus.OK);
		}
		else {
			LOGGER.error("Product cannot be added");
			throw new ProductException("Product cannot be added");
		}
	}
	/**
	 * It takes product ID and pass it to the service layer deleteProduct() method.
	 * @param product Id
	 * @return ResponseEntity<String>
	 */
	
	@DeleteMapping("/deleteproduct/{productId}")
	public  ResponseEntity<Message> deleteProduct(@PathVariable("productId")final int productId) throws ProductException{
		LOGGER.info("admin delete product");
		Message msg=new Message();
		msg.setResMessage("After deletion, Fetching the Details.");
		msg.setProductList(productService.deletProduct(productId));
		if(msg.getProductList()!=null) {
			return new ResponseEntity<Message>(msg , HttpStatus.OK);
		}
		else {
			LOGGER.error("Product cannot be deleted, as id  not present");
			throw new ProductException("Product cannot be deleted, as id "+productId+" is not present");
		}
}

	/**
	 * It accepts product details from admin and pass it to
	 * the service layer updateProduct() method.
	 * @param product
	 * @return Product Object
	 */
	@PutMapping("/updateproduct")
	public ResponseEntity<Message> updateProduct(@Valid @RequestBody final Product product) throws ProductException{
		//	Product update = productService.updateProduct(product);
		LOGGER.info("admin updates product");
		Message msg=new Message();
		msg.setResMessage("After Updating, Fetching the Details.");
		msg.setProductList(productService.updateProduct(product));
			if(msg.getProductList()!=null) {
				return new ResponseEntity( msg,HttpStatus.OK);
			}
			else {
				LOGGER.error("Product cannot be updated, as id "+product.getProductId()+" is not present");
				throw new ProductException("Product could not be updated,as id "+product.getProductId()+" not present");
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
		LOGGER.info("admin views product");
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
	 *  by calling getAllProductList()
	 * @param empty
	 * @return List<Product> object
	 */
	@GetMapping("/getallproduct")
	public ResponseEntity<List<Product>> getProductList() throws ProductException{
		LOGGER.info("admin views all products");
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
	public  ResponseEntity<List<Product>> getProductByCategory(@PathVariable("category")final String category) throws ProductException{
		LOGGER.info("admin views products by category");
		List<Product> list = productService.getProductByCategory(category);
		if (!list.isEmpty()) {
			return new ResponseEntity<List<Product>>(list, HttpStatus.OK);
		} else {
			LOGGER.error("No Product Found by this category");
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
		LOGGER.info("admin views all feedbacks");
		List<Feedback> feedbackList=feedbackService.viewAllFeedback();
		if (!feedbackList.isEmpty()) {
			return new ResponseEntity<List<Feedback>>(feedbackList,HttpStatus.OK);
		} else {
			LOGGER.error("No feedbacks Found");
			throw new FeedbackNotFoundException("No feedbacks Found");
		}	
		
	}
	
	/**
	 * This method delete feedback by id parameter.
	 * @param id
	 * @return ResponseEntity<Feedback>
	 */
	@DeleteMapping("/deletefeedback/{id}")
	public ResponseEntity<Message> deleteFeedback(@PathVariable("id")final Integer feedbackId)throws FeedbackNotFoundException
	{
		LOGGER.info("admin delete feedback");
		boolean feedbackPresent=feedbackService.getFeedbackById(feedbackId);
		if(feedbackPresent) {
			Message msg=new Message();
			msg.setResMessage("Deleted Successfully..!");
			msg.setFeedbackList(feedbackService.deleteFeedback(feedbackId));
			return new ResponseEntity(msg,HttpStatus.OK);
		}
		else {
			LOGGER.error("Feedback cannot be deleted, as id "+feedbackId+" not present");
			throw new FeedbackNotFoundException("Feedback cannot be deleted, as id "+feedbackId+" is not present");
		}
	}
	/**
	 * This method delete review for product by passing productId and
	 * reviewId to service layer deleteReviewForProduct() method.
	 * @param productId
	 * @param reviewId
	 * @return ResponseEntity<Feedback>
	 * @throws ReviewNotFoundException
	 */
	@DeleteMapping("/deletereview/{productid}")
	public ResponseEntity<String> deleteReviewForProduct(@PathVariable("productid")final Integer productId,@RequestParam final Integer reviewId)throws ReviewNotFoundException
	{
		LOGGER.info("admin delete review of product");
		boolean reviewPresent=reviewService.deleteReviewForProduct(reviewId, productId);
		if(reviewPresent) {
			return new ResponseEntity("review deleted",HttpStatus.OK);
		}
		else {
			LOGGER.error("Review cannot be deleted,as review for productid "+productId+" is not present");
			throw new ReviewNotFoundException("Review cannot be deleted,as review for productid "+productId+" is not present");
		}
	}

}
