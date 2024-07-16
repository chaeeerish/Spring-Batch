package ab180.assignments.global;

import ab180.assignments.link.global.config.JpaAuditingConfiguration;
import ab180.assignments.link.global.config.QueryDslConfig;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

@DataJpaTest
@Import({QueryDslConfig.class, JpaAuditingConfiguration.class})
@ActiveProfiles("test")
public class RepositoryTest {
}
