package com.example.resume.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.resume.entity.User;
import com.example.resume.repository.UserRepository;
import com.example.resume.util.JwtUtil;

@RestController
@CrossOrigin
public class LoginController {
  @Autowired
  UserRepository userRepository;

  @Autowired
  JwtUtil jwtUtil;

  @PostMapping("/signup")
  public Map<String, Object> signUp(@RequestBody User signUp) {
    Optional<User> opt = userRepository.findByEmail(signUp.getEmail());
    Map<String, Object> map = new HashMap<>();

    if (opt.isPresent()) {
      map.put("code", 401);
      map.put("msg", "이메일 중복");
    } else {
      User result = userRepository.save(signUp);
      map.put("code", 200);
      map.put("msg", "가입완료");
    }

    return map;
  }

  

  @PostMapping("/signin")
  public Map<String, Object> signin(@RequestBody User user) {
    Optional<User> opt = userRepository.findByEmail(user.getEmail());

    Map<String, Object> response = new HashMap<>();

    String jwt = null;

    if (opt.isPresent()) {
      User users = opt.get();

        if (users.getPassword().equals(user.getPassword())) {
          response.put("code", 200);
          response.put("msg", "Login successful");
          jwt = jwtUtil.createJwt(users.getEmail());
          response.put("name", users.getName());
          response.put("id", users.getId());
          response.put("jwt", jwt);  // JWT 토큰을 반환
        } else {
          response.put("code", 401);
          response.put("msg", "Invalid password");
        }
      } else {
        response.put("code", 404);
        response.put("msg", "User not Email");
      }

    return response;
  }
}
