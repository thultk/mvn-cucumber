@address
Feature: Register/ Login and add new address
  @register_login
  Scenario: Register to system and login
    Given Open Register page
    When Input to First name textbox
    And Input to Last name textbox
    And Input to Email textbox
    And Input to Password textbox
    And Input to Confirm password textbox
    And Click to Register button
    Then Verify success message displays
   	And Verify My account link displays
   	When Click to Log out link
   	And Click to Log in link
   	And Input to Email textbox in Log in page
   	And Input to Password textbox in Log in page
   	And Click to Login button
   	Then Verify My account link displays
   	
   @new_address
   Scenario Outline: Add new address
   Given Open "My account" page
   And Open Address page
   When Click to "Add new" button
   When Input to "Address_FirstName" textbox with value "<Firstname>"
   And Input to "Address_LastName" textbox with value "<Lastname>"
   And Input to "Address_Email" textbox with value "<Email>"
   And Select in "Address.CountryId" dropdown with value "<Country>"
   And Select in "Address.StateProvinceId" dropdown with value "<StateProvince>"
   And Input to "Address_City" textbox with value "<City>"
   And Input to "Address_Address1" textbox with value "<Address1>"
   And Input to "Address_ZipPostalCode" textbox with value "<PostalCode>"
   And Input to "Address_PhoneNumber" textbox with value "<PhoneNumber>"
   And Click to "Save" button
   Then The valid text displays at "name" with value "<Username>"
   And The valid text displays at "email" with value "<Email>"
   And The valid text displays at "phone" with value "<PhoneNumber>"
   And The valid text displays at "address1" with value "<Address1>"
   And The valid text displays at "city-state-zip" with value "<CityPostalCode>"
   And The valid text displays at "country" with value "<Country>"
   
   Examples:
| Firstname | Lastname | Username|Email                 | Country  | StateProvince | City        | Address1   | PostalCode | PhoneNumber |CityPostalCode      | 
| David     | Le       | David Le|testing01@hotmail.com | Viet Nam | Other         | Ho Chi Minh | 123 Le Lai | 330000     | 0987654321  | Ho Chi Minh, 330000|

	@regex
	Scenario: Regex
	When I input first Account ID
	And I input second Account ID
	And I transfer to "<Amount>" USD 
	And I withdraw to "<Amount>" USD 
   
