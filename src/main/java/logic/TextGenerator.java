package logic;

import org.apache.commons.lang3.RandomStringUtils;

public interface TextGenerator extends IntGenerator {
    default String generatePhone() {
        return new StringBuilder()
                .append(100 + generateInt(899))
                .append(100 + generateInt(899))
                .append(10 + generateInt(89))
                .append(10 + generateInt(89))
                .toString();
    }

    default String generateUniqEmail() {
        return RandomStringUtils.randomAlphabetic(10).concat("@test.test");
    }
}
