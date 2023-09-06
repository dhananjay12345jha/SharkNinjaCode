@testAll
@regressionNinjaCA
@regNinjaCASuit2
Feature: Product Registration - Register My Guarantee

  Background: Go To Home Page Of SharkNinja CA
    Given I navigate to the "Home" page
    And click on accept cookies if visible
    And select language defined in property file

  @registerMyGuaranteeHeadingSharkUS
  @smokeNinjaCA
  Scenario: Verify register my guarantee page heading
    When I click on register my guarantee link of Shark CA
    And select language defined in property file
    Then I should be on register my guarantee page having text either "Register my Warranty" or "Enregistrer un produit"


  @registerMyGuaranteeWithoutLogin
  @smokeNinjaCA
  Scenario: Verify successful product registration (without sign-in)
    When I click on register my guarantee link of Shark CA
    And select language defined in property file
    And click on accept cookies if visible
    And language selected should be equal to "select.language.to.test"
    When I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday   | login.email | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
    And Model Number should be displayed as "HV300C"
    And language selected should be equal to "select.language.to.test"

  @registerMyGuaranteeInvalidInputs
  Scenario: Verify product registration with invalid inputs
    When I click on register my guarantee link of Shark CA
    And select language defined in property file
    And click on accept cookies if visible
    And language selected should be equal to "select.language.to.test"
    And I Click Submit button without filling any information
    Then I should see error messages for all mandatory fields
#	Then I should see error messages as all fields are empty
    When I fill invalid first name in register my guaranty
    Then I should see error message for first name
    When I fill valid first name in register my guaranty
    Then error message for invalid first name should not be shown
    When I fill invalid last name in register my guaranty
    Then I should see invalid error message for last name
    When I fill valid last name in register my guaranty
    Then error message for invalid last name should not be shown
    When I fill invalid email address in register my guaranty
    Then error message for invalid email should be shown
    When I fill valid email address in register my guaranty
    Then Error message for invalid email should not be shown

  @registerMyGuaranteeWithLogin
  Scenario: Verify successful product registration (with sign-in)
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I enter email as "login.email" and password as "login.password" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page
    When I click on register my guarantee link of Shark CA
    And select language defined in property file
    And click on accept cookies if visible
    And language selected should be equal to "select.language.to.test"
    And I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday   | login.email | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
    And Model Number should be displayed as "HV300C"

  @validateProductGuaranty
  Scenario: Validate product registration
    When I click on register my guarantee link of Shark CA
    And select language defined in property file
    And click on accept cookies if visible
    And language selected should be equal to "select.language.to.test"
    And I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday    | login.register.product.email | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
    And Model Number should be displayed as "HV300C"
    And Save the guarantee information
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I enter email as "login.register.product.email" and password as "login.register.product.password" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page
#    When I click on My ProductsWarranties
#    Then I should be on Products and Warranties page
#    And I should see registered product warranty

    @verifyRegisterMyGuaranteeUrlValidationWithoutLogin
  Scenario: Verify Register my Warranty (Not Logged IN) with URL validation for each Warranty sections
      When I click on register my guarantee link of Shark CA
      And select language defined in property file
      And click on accept cookies if visible
      And language selected should be equal to "select.language.to.test"
      Then I should be on register my guarantee page having text either "Register my Warranty" or "Enregistrer un produit"
      And I fill register my guarantee form of Shark CA with below details
        | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
        | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday    | login.register.product.email | Canada  | Yes                    |
      And Click Submit button of Shark CA
      Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
      And Model Number should be displayed as "HV300C"
      And Purchase date should be one less than the currentDate
      #And Warranty Expiration should be displayed as "09 Feb, 2027 (1594 days left)" or "09 févr., 2027 (1594 Jours restants)"
      When I click on My Parts Accessories On Product Registration Confirmation Page
      Then I should be on Parts & Accessories Page page having text either "Shark® Rocket® Ultra-Light Corded Stick Vacuum" or "Aspirateur-balai ultra léger avec fil Shark® Rocket®"
      When I click on User Manuals On Product Registration Confirmation Page
      Then I should be on User Manuals page having text either "Product Information & FAQs" or "Informations sur les Produits et FAQ"
#      When I click on Five Year Ltd Warranty On Product Registration Confirmation Page
#      Then I should be on Five Year Warranty page having text either "Warranty and Returns" or "Garantie et Retours"
#      When I click on Faqs On Product Registration Confirmation Page
#      Then I should be on Faqs page having text either "Product Information & FAQs" or "Informations sur le produit et FAQ"

  @verifyRegisterMyGuaranteeUrlValidationWithLogin
  Scenario: Verify Register my Warranty (With Logged IN) with URL validation for each Warranty sections
    When I click on sign in link for SNUS
    Then I should be on log in page of SNUS
    When I enter email as "login.email" and password as "login.password" over SNCA
    And I clicked on sign in button
    Then I should be on My Account page
    When I click on register my guarantee link of Shark CA
    And select language defined in property file
    And click on accept cookies if visible
    And language selected should be equal to "select.language.to.test"
    Then I should be on register my guarantee page having text either "Register my Warranty" or "Enregistrer un produit"
    And I fill register my guarantee form of Shark CA with below details
      | FirstName | LastName | ModelNo | StoreOfPurchase | SerialNo | PurchaseDate | Email       | Country | SpecialOfferPromotions |
      | Sanket    | Jha      | HV300C  | Amazon          |          | yesterday    | login.register.product.email | Canada  | Yes                    |
    And Click Submit button of Shark CA
    Then Guarantee get registered and text "Your product has been registered!" or "Votre produit a été enregistré !" should be shown
    And Model Number should be displayed as "HV300C"
    And Purchase date should be one less than the currentDate
      #And Warranty Expiration should be displayed as "09 Feb, 2027 (1594 days left)" or "09 févr., 2027 (1594 Jours restants)"
    When I click on My Parts Accessories On Product Registration Confirmation Page
    Then I should be on Parts & Accessories Page page having text either "Shark® Rocket® Ultra-Light Corded Stick Vacuum" or "Aspirateur-balai ultra léger avec fil Shark® Rocket®"
    When I click on User Manuals On Product Registration Confirmation Page
    Then I should be on User Manuals page having text either "Product Information & FAQs" or "Informations sur les Produits et FAQ"
#    When I click on Five Year Ltd Warranty On Product Registration Confirmation Page
#    Then I should be on Five Year Warranty page having text either "Warranty and Returns" or "Garantie et Retours"
#   When I click on Faqs On Product Registration Confirmation Page
#   Then I should be on Faqs page having text either "Product Information & FAQs" or "Informations sur le produit et FAQ"


  @WarrantyInformationAtPDP&AddToCartPage

  Scenario: Verify Warranty details is populated for the applicable Product at PDP, Add to Cart and Checkout Page
    When I open search bar and entered the product as "product1"
    And I clicked on the search button
    And language selected should be equal to "select.language.to.test"
    When I click on product which is not out of stock from search list
    Then I should be on PDP page of that product
    Then Warranty Information and text "Includes One (1) Year Limited Warranty" or "Inclus Garantie limitée d'un an" should be shown on PDP and Cart Page
    And I click on add to cart button
    Then I click on cart icon
    Then I should be on cart page where text shown is "Cart" or "Panier"
    Then Warranty Information and text "Includes One (1) Year Limited Warranty" or "Inclus Garantie limitée d'un an" should be shown on PDP and Cart Page
    When i click on checkout button at cart page
    Then i should navigate to "Secure Checkout" page or "Paiement sécurisé"
    Then Warranty Information and text "Includes One (1) Year Limited Warranty" or "Inclus Garantie limitée d'un an" should be shown on PDP and Cart Page




