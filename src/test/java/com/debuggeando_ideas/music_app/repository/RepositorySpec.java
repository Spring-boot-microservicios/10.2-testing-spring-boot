package com.debuggeando_ideas.music_app.repository;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ExtendWith(SpringExtension.class)
// @SpringBootTest // Esto es equivalente a esto = @ExtendWith(SpringExtension.class)
@ActiveProfiles("test") // Hacemos referencia a las properties que tenemos en los test
 @DataJpaTest // Para los Repositories - JPA (se puede omitir por herencia el @SpringBootTest) // con h2
// @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // Para configuraciones de Posgresql o servidores reales
public class RepositorySpec {
}
