Feature: Actions
Scenario: Search Item
  Given the user wants to buy a guitar
  When the user searches for ‘Fender Stratocaster’
  Then the user should only see items called ‘Fender Stratocaster’

  Scenario: Browse categories
    Given the user is browsing the categories menu
    And the user is interested in drums and percussion
    When the user selects the acoustic drums option
    Then the user should be able to see the ‘acoustic drums’ category

  Scenario: Filter price
    Given the user wants to buy a guitar
    And the user searches for ‘Gibson Les Paul’
    When the user sets a price filter between $1200 and $2300
    Then the user should only see guitars in that price range
    And the user should only see items called ‘Gibson Les Paul’

  Scenario: Order price form high to low
    Given the user wants to buy a microphone
    When the user searches for ‘Condenser Microphones’
    And the user orders the price of the items from high to low
    Then the user should see the price of the items in descending order
    And the user should only see items called ‘Condenser Microphone’