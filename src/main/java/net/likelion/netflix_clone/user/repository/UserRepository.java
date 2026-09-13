package net.likelion.netflix_clone.user.repository;

import net.likelion.netflix_clone.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email); //회원가입용

    Optional<User> findByEmail(String email); //로그인용
}