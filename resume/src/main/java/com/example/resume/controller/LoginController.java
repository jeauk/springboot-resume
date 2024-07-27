package com.example.resume.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

  @PostMapping("/upload")
  public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
    try {
        // 파일 저장 로직 (예: 로컬 파일 시스템 또는 클라우드 스토리지)
        String fileName = file.getOriginalFilename();
        Path path = Paths.get("uploads/" + fileName);
        Files.write(path, file.getBytes());

        return ResponseEntity.ok("File uploaded successfully: " + fileName);
    } catch (IOException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
    }
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
          response.put("msg", "로그인 성공");
          jwt = jwtUtil.createJwt(users.getEmail());
          response.put("name", users.getName());
          response.put("id", users.getId());
          response.put("jwt", jwt);  // JWT 토큰을 반환
        } else {
          response.put("code", 401);
          response.put("msg", "비밀번호가 잘못되었습니다");
        }
      } else {
        response.put("code", 404);
        response.put("msg", "이메일이 잘못되었습니다");
      }

    return response;
  }
}
