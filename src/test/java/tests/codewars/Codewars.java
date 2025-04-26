package tests.codewars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.stream.Collectors;


public class Codewars {

    public static String reverseWords(final String original) {
        return Arrays.stream(original.split(" ")).map(e->new StringBuilder(e).reverse().toString()).collect(Collectors.joining(" "));
    }

    @Test
    public void basicTest() {
    Assertions.assertThat(reverseWords("  double  spaced  words  ")).isEqualTo("  elbuod  decaps  sdrow  ");
    }
}
