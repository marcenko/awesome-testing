package de.cronos.awesometesting.db;

import de.cronos.awesometesting.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
