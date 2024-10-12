# ProjectFXA4CCC

## Architecture
Main:
  - connect to HomePageController
    
ControllersPacakge:
  - Contains all the controllers
  - Each controller is connected to theirs unique fxml file
    - ex : FirstPageController is connected to firstPage.fxml 
      
GeneratedClasses:
  - All class that have special operation that controllers can use if they implement it or they extend it
    
module-info.java :
  - Link external packages and the main package

## Namming format

Naming Id:
  - Prefix + the name attribuate for the object
    - refers to these document to know wich prefix name to use
    - depends on the type of the object 
    - ex : the id for the buttton homePage is --> btnHomePage ).
    - If there is no name attribuate, it's what's the object does in the scene

Naming action:
  - Have to specify what do precisely the action binded to the object
     - ex : a button that goes to page Prices --> goToPrices
  - If we can understand what's the function does directly with the name's method renames it 
 
## Prefix dictionary 
Button --> btn

CheckBox --> ckB

Label --> lbl

ListView --> lstV

MenuButton --> mnBtn

scrollBar --> scrBar
  - if it's horizontal --> scrBarH
  - if it's veritcal --> scrBarV

slider --> sld
  - if it's horizontal -->sldH
  - if it's veritcal --> sldV

TableView --> tabV

TextArea --> txtA

TextField --> txtF

if you add another objec than this above --> write into the read me to update the namming format part

## Commit-Push Rule

You have to respect this format for the commit message --> prefix of the ticket
ex : 

the title ticket is A1 - SceneBuilder set HomePage 

you're commit must be A1 - SceneBuilder set HomePage
