package com.gym.user.registration.repository;

import com.gym.user.registration.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {

    boolean existsByNicknameOrMail(String nickname, String mail);

    Optional<User> findByMail(String mail);
}
