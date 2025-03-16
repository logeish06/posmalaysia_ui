#Author: Logeish
@ratecalculator
Feature: Shipment Quote

  Scenario Outline: Verify Rate Calculator displays multiple quote for user - when To Country is not Malaysia
    Given user is on the pos malaysia rate calculator url
    When user enters "<fromcountry>", "<frompostcode>", "<fromstate>", "<tocountry>", "<topostcode>", "<toState>", "<itemweight>" and click calculate button
    Then verify multiple quotes are displayed for user

    Examples: 
      | fromcountry | frompostcode | fromstate | tocountry | topostcode | tostate | itemweight |
      | Malaysia    |        35600 | Perak     | India     |            |         |          1 |
