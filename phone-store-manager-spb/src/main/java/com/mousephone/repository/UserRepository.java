package com.mousephone.repository;

import com.mousephone.model.Staff;
import com.mousephone.model.User;
import com.mousephone.model.dto.user.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User getByUsername(String username);

    Optional<User> findByUsername(String username);

    @Query("SELECT NEW com.mousephone.model.dto.user.UserDTO (" +
            "u.id, " +
            "u.username" +
            ") " +
            "FROM User u " +
            "WHERE u.username = ?1"
    )
    Optional<UserDTO> findUserDTOByUsername(String username);

    Boolean existsByUsername(String username);

    @Query("SELECT " +
            "u.id, " +
            "u.username, " +
            "u.password, " +
            "u.role " +
            "FROM User AS u " +
            "WHERE u.username = :username " +
            "AND u.id <> :id"
    )
    Optional<Staff> findByUsernameAndIdIsNot(@Param("username") String username, @Param("id") Long id);
}
