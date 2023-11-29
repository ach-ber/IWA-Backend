package com.iwa.serviceuser;
import com.iwa.serviceuser.entity.User;
import com.iwa.serviceuser.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class UserDatabaseTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserRepositoryOperations() {
        // Création d'un utilisateur de test
        User user = new User();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        user.setRole("ROLE_USER");

        // Sauvegarde de l'utilisateur dans la base de données H2
        userRepository.save(user);

        // Récupération de l'utilisateur à partir de la base de données
        User retrievedUser = userRepository.findByEmail("test@example.com").orElse(null);

        // Vérification si l'utilisateur récupéré n'est pas nul et que les données sont correctes
        assertNotNull(retrievedUser);
        assertEquals("test@example.com", retrievedUser.getEmail());
        assertEquals("password123", retrievedUser.getPassword());
        assertEquals("ROLE_USER", retrievedUser.getRole());
    }
}
