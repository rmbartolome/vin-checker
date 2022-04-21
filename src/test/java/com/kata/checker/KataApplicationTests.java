package com.kata.checker;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
@SpringBootTest
class KataApplicationTests {

    @Test
    @DisplayName("Test 1: 5YJ3E1EA7HF000337 should be valid!")
    public void test1_5YJ3E1EA7HF000337() {
        assertThat(KataApplication.checkVin("5YJ3E1EA7HF000337")).isTrue();
    }

    @Test
    @DisplayName("Test 2: 5YJ3E1EAXHF000347 should be valid!")
    public void test2_5YJ3E1EAXHF000347() {
        assertThat(KataApplication.checkVin("5YJ3E1EAXHF000347")).isTrue();
    }

    @Test
    @DisplayName("Test 3: 5VGYMVUX7JV764512 should be valid!")
    public void test3_5VGYMVUX7JV764512() {
        assertThat(KataApplication.checkVin("5VGYMVUX7JV764512")).isTrue();
    }

    @Test
    @DisplayName("Test 4: 7WDMMTDV9TG739741 should be invalid!")
    public void test4_7WDMMTDV9TG739741() {
        assertThat(KataApplication.checkVin("7WDMMTDV9TG739741")).isFalse();
    }

    @Test
    @DisplayName("Test 5: 7JTRH08L5EJ234829 should be invalid!")
    public void test5_7JTRH08L5EJ234829() {
        assertThat(KataApplication.checkVin("7JTRH08L5EJ234829")).isFalse();
    }

}
