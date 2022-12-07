package alberto.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import alberto.model.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Integer> {

  AppUser findByUsername(String username);

}
