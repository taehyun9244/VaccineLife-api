package com.vaccinelife.vaccinelifeapi.service;


import com.vaccinelife.vaccinelifeapi.dto.SignupRequestDto;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.UserRole;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Slf4j
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private static final String ADMIN_TOKEN = "AAABnv/xRVklrnYxKZ0aHgTBcXukeZygoC";


    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public Long update(Long id, SignupRequestDto requestDto) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NullPointerException("아이디가 존재하지 않습니다.")
        );
        user.update(requestDto);
        return id;
    }


    @Transactional
    public void registerUser(SignupRequestDto requestDto) {
        Long id=requestDto.getId();
        String username = requestDto.getUsername();
        String password = requestDto.getPassword();
        String passwordChecker = requestDto.getPasswordChecker();
        String nickname = requestDto.getNickname();
        Boolean isVaccine=requestDto.getIsVaccine();
        String type=requestDto.getType();
        int degree=requestDto.getDegree();
        String gender=requestDto.getGender();
        String age=requestDto.getAge();
        String disease=requestDto.getDisease();
        String afterEffect=requestDto.getAfterEffect();



        password = passwordEncoder.encode(requestDto.getPassword());
        UserRole role = UserRole.USER;
        User user = new User( id, username, password, role, nickname, isVaccine, type, degree , gender, age, disease, afterEffect);
        userRepository.save(user);
    }
}