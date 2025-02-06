Feature: Addition

  Scenario Outline: Adding two numbers
    Given I have two numbers <num1> and <num2>
    When I add them
    Then the result should be <sum>

    Examples:
      | num1 | num2 | sum |
      |  2   |  3   |  5  |
      |  5   |  7   |  12 |
      |  10  |  20  |  30 |
