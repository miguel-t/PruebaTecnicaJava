package sat.recruitment.api.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sat.recruitment.api.user.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Boolean existsByUserName(String username);
    Boolean existsByEmail(String email);
}
