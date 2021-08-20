package com.vaccinelife.vaccinelifeapi.repository;

import com.vaccinelife.vaccinelifeapi.model.User;
import com.vaccinelife.vaccinelifeapi.model.VacBoard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.Optional;

@EnableJpaAuditing
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(User user);
    Optional<User> findByUsername(String username);
    Optional<User> findByNickname(String nickname);
    boolean existsByUsername(String username);
    boolean existsByNickname(String nickname);
}
