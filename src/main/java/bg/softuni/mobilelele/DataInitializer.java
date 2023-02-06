package bg.softuni.mobilelele;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {
    private final JdbcTemplate jdbcTemplate;

    private static final Path DATA_PATH = Path.of("src/main/resources/data.sql");

    private static final Path ENTITY_COUNT_PATH = Path.of("src/main/resources/entityCount.sql");

    public DataInitializer(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) throws Exception {
        boolean isEmpty = Arrays.stream(Files.readString(ENTITY_COUNT_PATH).split(";"))
                .allMatch(sql -> jdbcTemplate.queryForObject(sql, Integer.class) == 0);

        if (isEmpty) {
            Arrays.stream(Files.readString(DATA_PATH).split(";")).forEach(jdbcTemplate::execute);
        }
    }
}
