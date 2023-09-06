@regSharkUK
@regSharkDE
@regNinjaUK
@regSharkIT
@regNinjaIT
@regSharkES
@regNinjaES
@regNinjaDE
@smokeSharkUK
@smokeSharkDE
@smokeNinjaUK
@smokeSharkIT
@smokeNinjaIT
@smokeNinjaDE
@smokeSharkES
@smokeNinjaES
@regSharkDESuite2
@regNinjaDESuite2
@regSharkITSuite2
@regNinjaITSuite2
@regSharkESSuite2
@regNinjaESSuite2
@regNinjaUKSuite2
@regSharkUKSuite2
@regSharkFRSuite2
@regNinjaFRSuite2
Feature: Footer: Verify links in footer, click all footer links and verify is redirect to correct page.


	Background: Go To Home Page
		When I navigate to the "Home" page
		And click on accept cookies if visible
		And click on accept email if visible
		Given User is setting the stock quantity for GB with the Sku as IZ320UK and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as IZ202UKTDB and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as AG551UK and the quantity is 200
		Given User is setting the stock quantity for GB with the Sku as KT200UK and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as NZ801EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as CB100EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ300EUTDB and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as HB150EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as AG551EU and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ300EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as IZ320EUT and the quantity is 200
		Given User is setting the stock quantity for EU with the Sku as OL650EU and the quantity is 200

	@excludeNinjaUK
	@excludeNinjaDE
	@excludeSharkDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Shark UK footer links
		Then I should see Shark clean text in footer
		Then I should see About Us link in footer
		Then I should see Reviews link in footer
		Then I should see Cleaning Tips & Advice link in footer
		Then I should see Terms and Conditions link in footer
		Then I should see Privacy Notice link in footer
		Then I should see Uk Modern Slavery Statement link in footer
		Then I should see Gender Pay Gap Link in footer
		Then I should see Consumer Rights link in footer
		Then I should see Vulnerability Disclosure Policy link in footer
		Then I should see Shark Ninja Checkout Fair Processing Notice link in footer
		Then I should see Shark Ninja Careers link in footer
		Then I should see Register My Guarantee link in footer
		Then I should see Log In link in footer
		Then I should see Facebook Icon in footer
		Then I should see Twitter Icon in footer
		Then I should see Instagram Icon in footer
		Then I should see Pinterest Icon in footer
		Then I should see Youtube Icon in footer
		Then I should see Newsletter Email text box in footer
		Then I should see offers checkbox in footer
		Then I should see newsletter submit button in footer
		#Then I should see unsubscribe link in footer
		When I click about shark link in footer
		Then I should be on about shark page
		When I click Register My Guarantee link in footer
		Then I should be on register my guarantee page
		When I click Log In link in footer
		Then I should be on log in page
		When I click customer care link in footer
		Then I should be on customer care footer page

	@excludeSharkUK
	@excludeSharkDE
	@excludeNinjaDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Ninja UK footer links
		Then I should see "About Ninja" link in footer
		Then I should see "Recipes & Tips" link in footer
		Then I should see "Customer Care" link in footer
		Then I should see "Terms and Conditions" link in footer
		Then I should see "Privacy Notice" link in footer
		Then I should see "UK Modern Slavery Statement" link in footer
		#Then I should see "Online Dispute Resolution" link in footer
		Then I should see "Vulnerability Disclosure Policy" link in footer
		Then I should see "Gender Pay Gap" link in footer
		Then I should see "Consumer Rights" link in footer
		Then I should see "SharkNinja Careers" link in footer
		Then I should see "Register My Guarantee" link in footer
		Then I should see "Log in" link in footer
		Then I should see "Discount" link in footer
		#Then I should see "Unsubscribe" link in footer

	@excludeSharkUK
	@excludeNinjaUK
	@excludeNinjaDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Shark DE footer links
		Then I should see "Impressum" link in footer
		Then I should see "Kundensupport" link in footer
		Then I should see "Datenschutzerklärung" link in footer
		Then I should see "allgemeine geschäftsbedingungen" link in footer
		Then I should see "Cookie-Hinweis" link in footer
		Then I should see "aktivieren sie ihre garantie" link in footer
		Then I should see "Anmelden" link in footer


	@excludeSharkUK
	@excludeNinjaUK
	@excludeNinjaDE
	@excludeSharkDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Shark IT footer links
		Then I should see "Shark: chi siamo" link in footer
		Then I should see "Black Friday" link in footer
		Then I should see "Termini e condizioni" link in footer
		Then I should see "Privacy. Protezione dei dati" link in footer
		Then I should see "Informativa sui cookie" link in footer
		Then I should see "800 961655" link in footer
		Then I should see "Maggiori informazioni per contattarci" link in footer
		Then I should see "Registra la garanzia" link in footer
		Then I should see "Accedi" link in footer

	@excludeSharkUK
	@excludeNinjaUK
	@excludeNinjaDE
	@excludeSharkDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Ninja IT footer links
		Then I should see "Ninja: chi siamo" link in footer
		Then I should see "Risparmio energetico" link in footer
		Then I should see "Ricette e consigli" link in footer
		Then I should see "Black Friday" link in footer
		Then I should see "Termini e condizioni" link in footer
		Then I should see "Privacy. Protezione dei dati" link in footer
		Then I should see "Informativa sui cookie" link in footer
		Then I should see "800 961655" link in footer
		Then I should see "Maggiori informazioni per contattarci" link in footer
		Then I should see "Registra la garanzia" link in footer
		Then I should see "Accedi" link in footer

	@excludeSharkUK
	@excludeNinjaUK
	@excludeNinjaDE
	@excludeSharkDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeNinjaIT
	@excludeSharkIT
	@excludeNinjaES
	Scenario: Verify Shark ES footer links
		Then I should see "Acerca de Shark y SharkNinja" link in footer
		Then I should see "Términos y Condiciones" link in footer
		Then I should see "Aviso de privacidad" link in footer
		Then I should see "Aviso de Cookies" link in footer
		Then I should see "Ofertas" link in footer
		Then I should see "Black Friday" link in footer
		Then I should see "Inicia sesión" link in footer
		Then I should see "Registra tu garantía" link in footer
		Then I should see "900 839 453" link in footer
		Then I should see "Más información de contacto" link in footer
		Then I should see "Pago seguro" link in footer

	@excludeSharkUK
	@excludeNinjaUK
	@excludeNinjaDE
	@excludeSharkDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeNinjaIT
	@excludeSharkIT
	@excludeSharkES
	Scenario: Verify Ninja ES footer links
		Then I should see "Acerca de Ninja y SharkNinja" link in footer
		Then I should see "Recetas y consejos" link in footer
		Then I should see "Términos y Condiciones" link in footer
		Then I should see "Aviso de privacidad" link in footer
		Then I should see "Aviso de Cookies" link in footer
		Then I should see "Ahorro energético" link in footer
		Then I should see "Ofertas" link in footer
		Then I should see "Black Friday" link in footer
		Then I should see "Inicia sesión" link in footer
		Then I should see "Registra tu garantía" link in footer
		Then I should see "900 839 453" link in footer
		Then I should see "Más información de contacto" link in footer
		Then I should see "Pago seguro" link in footer


	@excludeSharkUK
	@excludeNinjaUK
	@excludeSharkDE
	@excludeSharkFR
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Ninja DE footer links
		Then I should see "Impressum" link in footer
		Then I should see "Kundensupport" link in footer
		Then I should see "Datenschutzhinweis" link in footer
		Then I should see "Allgemeine Geschäftsbedingungen" link in footer
		Then I should see "Cookie-Hinweis" link in footer
		Then I should see "Aktivieren Sie Ihre Garantie" link in footer
		Then I should see "Anmelden" link in footer



	@excludeSharkUK
	@excludeNinjaUK
	@excludeSharkDE
	@excludeNinjaDE
	@excludeSharkFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Ninja FR footer links
		Then I should see "À propos de Ninja" link in footer
		Then I should see "Conditions générales d'utilisation" link in footer
		Then I should see "Conditions générales de vente" link in footer
		Then I should see "Politique de cookies" link in footer
		Then I should see "Politique de Confidentialité" link in footer
		Then I should see "Livraison" link in footer
		Then I should see "Recyclage" link in footer
		Then I should see "Black Friday" link in footer
		Then I should see "Enregistrer ma garantie" link in footer
		Then I should see "Connexion" link in footer
		Then I should see "Contactez-nous" link in footer

	@excludeSharkUK
	@excludeNinjaUK
	@excludeSharkDE
	@excludeNinjaDE
	@excludeNinjaFR
	@excludeSharkIT
	@excludeNinjaIT
	@excludeSharkES
	@excludeNinjaES
	Scenario: Verify Shark FR footer links
		Then I should see "À propos de Shark" link in footer
		Then I should see "Conditions générales d'utilisation" link in footer
		Then I should see "Conditions générales de vente" link in footer
		Then I should see "Politique de cookies" link in footer
		Then I should see "Politique de Confidentialité" link in footer
		Then I should see "Livraison" link in footer
		Then I should see "Recyclage" link in footer
		Then I should see "Black Friday" link in footer
		Then I should see "Où acheter en magasin" link in footer
		Then I should see "Connexion" link in footer
		Then I should see "Contactez nous" link in footer
		Then I should see "Enregistrer ma garantie" link in footer
