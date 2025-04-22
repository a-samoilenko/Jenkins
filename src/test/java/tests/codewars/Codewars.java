package tests.codewars;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;


public class Codewars {

    public static int findEvenIndex(int[] arr) {
        for(int i:arr){

        }
        return -1;
    }

    @Test
    void basicTest() {
        Assertions.assertThat(findEvenIndex(new int[] {1,2,3,4,3,2,1})).isEqualTo(3);
    }
}
