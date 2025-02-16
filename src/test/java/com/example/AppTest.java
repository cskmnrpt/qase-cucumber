package com.example;

import org.testng.Assert;
import org.testng.annotations.Test;

public class AppTest {

    @Test
    public void Test_to_check_the_build() {
        System.out.println("Running test...");
        Assert.assertTrue(true, "The test should always pass");
    }
}
