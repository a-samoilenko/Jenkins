package generators;

import java.util.*;
import org.instancio.Instancio;
import org.instancio.Select;
import java.lang.reflect.*;

public class TestDataGenerator {

    private static final Random random = new Random();

    public static <T> T generate(Class<T> clazz) throws Exception {
        return Instancio.create(clazz);
    }

//    public static <T> T generate(Class<T> clazz) throws Exception {
//        Constructor<?> constructor = clazz.getDeclaredConstructors()[0];
//        Parameter[] parameters = constructor.getParameters();
//
//
//        Object[] args = new Object[parameters.length];
//        for (int i = 0; i < parameters.length; i++) {
//            Class<?> type = parameters[i].getType();
//            if (type == int.class || type == Integer.class) {
//                args[i] = random.nextInt(100) + 1; // Генерация случайного числа от 1 до 100
//            } else if (type == String.class) {
//                args[i] = generateRandomString(10); // Генерация случайной строки длиной 10
//            } else {
//                throw new IllegalArgumentException("Unsupported type: " + type.getSimpleName());
//            }
//        }
//
//        return (T) constructor.newInstance(args);
//    }

    private static String generateRandomString(int length) {
        String allowedChars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allowedChars.length());
            sb.append(allowedChars.charAt(index));
        }
        return sb.toString();
    }
}
