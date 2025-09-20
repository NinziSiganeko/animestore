package za.ac.cput.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.cput.domain.Admin;
import za.ac.cput.domain.Customer;
import za.ac.cput.domain.User;
import za.ac.cput.repository.UserRepository;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173") // React dev server
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    // SignUp
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody Map<String, String> signupRequest) {
        String username = signupRequest.get("username");
        String email = signupRequest.get("email").toLowerCase();
        String password = signupRequest.get("password");

        User user;


        if (email.endsWith("@animestore.co.za")) {
            user = new Admin.Builder()
                    .setUsername(username)
                    .setEmail(email)
                    .setPassword(password)
                    .build();
        } else {
            user = new Customer.Builder()
                    .setUsername(username)
                    .setEmail(email)
                    .setPassword(password)
                    .build();
        }


        userRepository.save(user);

        return ResponseEntity.ok(user);
    }


    @PostMapping("/signin")
    public ResponseEntity<?> signin(@RequestBody Map<String, String> loginRequest) {
        String email = loginRequest.get("email").toLowerCase();
        String password = loginRequest.get("password");

        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password)) // replace with BCrypt in production
                .<ResponseEntity<?>>map(u -> {
                    Map<String, Object> response = new HashMap<>();
                    response.put("id", u.getUserId());
                    response.put("username", u.getUsername());
                    response.put("email", u.getEmail());
                    response.put("role", (u instanceof Admin) ? "Admin" : "Customer");
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(401).body(Map.of("error", "Invalid email or password")));
    }
}