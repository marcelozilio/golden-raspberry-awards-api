package texoit.com.goldenraspberryawards.util;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.math.BigInteger;

public class RandomUtils {

    public static String generateString(int size) {
        return RandomStringUtils.random(size, true, false);
    }

    public static BigInteger generateInteger(int size) {
        BigInteger result = new BigInteger(RandomStringUtils.random(size, false, true));
        if (result.toString().length() < size) {
            result = new BigInteger(StringUtils.rightPad(result.toString(), size, "0"));
        }

        return result;
    }

}