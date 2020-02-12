package logic;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.Random;

public interface IntGenerator {
    default Integer generateInt(int max){
        return new Random().nextInt(max);
    }

    default String generateNumber(Integer count) {
        return RandomStringUtils.randomNumeric(count);
    }
}
