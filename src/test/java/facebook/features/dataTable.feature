@datatable
Feature: DataTable feature
  @datatable_step
    Scenario Outline: Scenario have parameter
    #Given Open facebook application
      And Input to Username and Password
      | Username   | Password   |
      | auto01@gmail.com | 123456 |
      | auto02@gmail.com | 123456 |
      And Click to Submit button
      #And Close application
    Examples: 
      | Username                 | Password |
      | automationfc@hotmail.com | 123456   |
      
  @data_driven
  Scenario Outline: Scenario have parameter
    #Given Open facebook application
    And Input to Username textbox with "<Username>"
    And Input to Password textbox with "<Password>"
    And Click to Submit button
    #And Close application
  Examples: 
      | Username                 | Password |
      | auto01@gmail.com				 | 123456   |
      | auto02@gmail.com         | 123456   |