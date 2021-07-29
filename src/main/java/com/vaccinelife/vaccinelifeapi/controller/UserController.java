package com.vaccinelife.vaccinelifeapi.controller;
import com.vaccinelife.vaccinelifeapi.controller.security.JwtTokenProvider;
import com.vaccinelife.vaccinelifeapi.dto.SignupRequestDto;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import com.vaccinelife.vaccinelifeapi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserService userService;
    private final UserRepository userRepository;


    @PostMapping("/api/signup")
    public void registerUser(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.registerUser(signupRequestDto);
    }

    @PostMapping("/api/login")
    public String login(@RequestBody SignupRequestDto requestDto) {
        User user = userRepository.findByUsername(requestDto.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("가입되지 않은 유저입니다."));
        if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호입니다.");
        }
        return jwtTokenProvider.createToken(user.getUsername(), user.getRole(), user.getNickname(),user.getType(),user.getDegree(),user.getGender(),user.getAge(),user.getDisease(),user.getAfterEffect());
    }







}