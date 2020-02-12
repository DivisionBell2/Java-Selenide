package logic;

import java.util.Optional;

public interface Env {
    default String getEnv(String envName) {
        /*
        for remote: change default -> linkName
        for local: change linkName -> default
         */
        return Optional.ofNullable(System.getenv(envName)).orElse("default");
    }
}
