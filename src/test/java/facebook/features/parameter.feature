@parameter
Feature: Parameter feature
 
#Background: 
	#Given Open facebook application
  @no_param
  Scenario: Scenario have no parameter
    #Given Open facebook application
    And Input to Username textbox
    And Input to Password textbox
    And Click to Submit button
    #And Close application
    
  @param_mark  
  Scenario: Scenario have parameter
    #Given Open facebook application
    And Input to Username textbox with "automationtest@gmail.com"
    And Input to Password textbox with "123456"
    And Click to Submit button
    #And Close application

	@param_no_mark  
  Scenario: Scenario have parameter
    #Given Open facebook application
    And Input to Username textbox with automationtest@gmail.com
    And Input to Password textbox with 123456
    And Click to Submit button
    #And Close application
    
  @multiple_param 
  Scenario: Scenario have parameter
    #Given Open facebook application
    And Input to Username with "automationtest@gmail.com" and Password with "123456"
    And Click to Submit button
    #And Close application
