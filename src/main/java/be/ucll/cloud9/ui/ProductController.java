package be.ucll.cloud9.ui;
import java.util.Arrays;
import java.util.List;

import be.ucll.cloud9.domain.model.EmailSubscriber;
import be.ucll.cloud9.domain.model.Feedback;
import be.ucll.cloud9.domain.model.Product;
import be.ucll.cloud9.domain.service.EmailSubscriberService;
import be.ucll.cloud9.domain.service.FeedbackService;
import be.ucll.cloud9.domain.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin( origins = "http://webserver-ucllteam09.ocp-ucll-40cb0df2b03969eabb3fac6e80373775-0000.eu-de.containers.appdomain.cloud", allowCredentials = "true")
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private EmailSubscriberService emailSubscriberService;

    @RequestMapping("/products")
    public List<Product> getAllDbProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/check")
    public boolean greeting(@RequestParam(value = "name", defaultValue = "World") String name,
                            @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In GET Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("partner"));
        if (partnerRole) {
            return true;
        } else {
            return false;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/products")
    public String addProduct(@Valid @RequestBody Product product, @AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In POST Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("partner");

        if (partnerRole) {
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
            System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("partner"));
            productService.addProduct(product);
            return "Product added";
        } else {
            return "Not Authorized to add product";
        }
    }

    @GetMapping("/feedbacks")
    public List<Feedback> greeting(@AuthenticationPrincipal Jwt accessToken) {
        System.out.println("In GET Request");
        String scope = accessToken.getClaims().get("scope").toString();
        Boolean partnerRole = scope.contains("admin");
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString());
        System.out.println("Contains sequence 'partner': " + accessToken.getClaims().get("scope").toString().contains("admin"));
        if (partnerRole) {
            return feedbackService.getAllFeedback();
        } else {
            return null;
        }
    }

    @RequestMapping("/addEmail")
    public String addEmail(@RequestParam String email) {
        if (email.isEmpty()) {
            return "Couldn't add your email";
        } else {
            EmailSubscriber k = new  EmailSubscriber(email);
            emailSubscriberService.addEmail(k);
            System.out.println(k.getId());
            System.out.println(k.getEmail());

            return "Succesfully added your email to the email list";
        }
    }

    @PostMapping("/addFeedback")
    public String addFeedback(@RequestBody Feedback feedback, @AuthenticationPrincipal Jwt accessToken) {
        if (feedback != null) {
            //feedback.setId((long) 111111111);
            feedbackService.addFeedback(feedback);
            return "Feedback added";
        } else {
            return "Couldn't add feedback";
        }
    }
}