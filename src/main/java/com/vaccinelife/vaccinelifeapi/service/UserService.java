package com.vaccinelife.vaccinelifeapi.service;

import com.vaccinelife.vaccinelifeapi.dto.SignupRequestDto;
import com.vaccinelife.vaccinelifeapi.model.Timestamped;
import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.UserRole;
import com.vaccinelife.vaccinelifeapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
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

        Optional<User> found = userRepository.findByUsername(username);
        Optional<User> nicknameFound = userRepository.findByNickname(nickname);

        Pattern usernamePattern = Pattern.compile("^[a-zA-Z0-9]*$");
        Matcher usernameMatcher = usernamePattern.matcher(username);

        if (username.equals("") || password.equals("") || passwordChecker.equals("")|| nickname.equals("")) {
            throw new IllegalArgumentException("username || password || passwordChecker가 비어있습니다.");
        } else if (password.length() < 8) {
            throw new IllegalArgumentException("password는 최소 8글자입니다.");
        } else if (!password.equals(passwordChecker)) {
            throw new IllegalArgumentException("password와 passwordChecker가 다릅니다.");
        }else if (found.isPresent() && nicknameFound.isPresent()) {
                throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다. " + " 중복된 닉네임이 존재합니다.");
        } else if (found.isPresent()) {
            throw new IllegalArgumentException("중복된 사용자 ID가 존재합니다.");
        }else if (nicknameFound.isPresent()) {
            throw new IllegalArgumentException("중복된 닉네임이 존재합니다.");
        }else if(!usernameMatcher.find() || username.length()<6 || username.length()>12){
            throw new IllegalArgumentException("아이디는 영문 조합하여 6~12자로 구성하세요.");
        }


        password = passwordEncoder.encode(requestDto.getPassword());
        UserRole role = UserRole.USER;
        User user = new User( id, username, password, role, nickname, isVaccine, type, degree , gender, age, disease, afterEffect);
        userRepository.save(user);
    }
}