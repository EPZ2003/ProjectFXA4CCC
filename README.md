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


## Commit-Push Rule

You have to respect this format for the commit message --> prefix of the ticket
ex : 

the title ticket is A1 - SceneBuilder set HomePage 

you're commit must be A1 - SceneBuilder set HomePage

## MySQL Table Configuration with the project

Command to copy to a Query Console to have the different table to use:
___
create table table_products (
id int auto_incremented primary key,
product_name varchar(100),
stock int,
specialAttribute double
);

create table table_products_prices (
id_products int auto_incremented primary key,
discount double,
sellPrice double,
purchasePrice double
);

create table table_money (
capital double,
income double,
outcome double
);
___
## Feature of all the pages and theirs features
This tables constitute the different pages of the project : 
1. **table_products** is managed by firstController and represents the Inventory where you can add or delete a products and managed the stocks
    ######     FEATURE 1 : This page is not to manage the purchase/sell product. In case of you check another time the inventory, and you find that the number that you put is not correct you can modify only the stock. When the product's stock reaches 0, and you click on the minus it deleted automatically 
    ######     FEATURE 2 : You can add a new Product manually without pass by the purchase/sell Page. When you will click on the addProduct Button and a new page will appear and you have to fill all the input field.
    ######     FEATURE 3 : You can update a field of your products
3. **table_products_prices** is managed by SecondController and represents the place where to buy or sell an product **_already existed_**
    ######     FEATURE 1 : You can apply a discount with a toggle Button
    ######     FEATURE 2 : You can sell/purchase a product which impacted the Inventory page explain above
3. **table_money** is managed by ThirdController and represents a display page 
    ######     FEATURE : Juste display the different elements on the table table_money
