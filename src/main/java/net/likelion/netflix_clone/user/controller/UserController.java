package net.likelion.netflix_clone.user.controller;

import io.swagger.v3.oas.annotations.Operation;
import net.likelion.netflix_clone.user.dto.LoginRequest;
import net.likelion.netflix_clone.user.dto.LoginResponse;
import net.likelion.netflix_clone.user.dto.SignupRequest;
import net.likelion.netflix_clone.user.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "회원가입",
            description = "이메일, 비밀번호, 이름을 입력받아 회원을 생성합니다."
    )
    @PostMapping("/signup")
    public String signup(@RequestBody SignupRequest request) {

        userService.signup(request);

        return "회원가입 성공";
    }

    @Operation(
            summary = "로그인",
            description = "이메일과 비밀번호를 확인하고 JWT Access Token을 발급합니다."
    )
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        return userService.login(request);
    }

}