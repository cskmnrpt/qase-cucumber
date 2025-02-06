package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;

public class AdditionSteps {
    
    private int num1, num2, result;

    @Given("I have two numbers {int} and {int}")
    public void i_have_two_numbers(int a, int b) {
        this.num1 = a;
        this.num2 = b;
        Assert.assertTrue(true);
    }

    @When("I add them")
    public void i_add_them() {
        result = num1 + num2;
        Assert.assertTrue(true);
    }

    @Then("the result should be {int}")
    public void the_result_should_be(int expectedSum) {
        Assert.assertEquals(result, expectedSum);
        System.err.println("Result is" + result);
    }
}
