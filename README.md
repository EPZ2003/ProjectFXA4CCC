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

INSERT INTO table_products (product_name, stock, specialAttribute) VALUES
('Product 1', 100, 12.5),
('Product 2', 200, 15.0),
('Product 3', 50, 9.8),
('Product 4', 300, 17.2),
('Product 5', 120, 11.3),
('Product 6', 180, 14.9),
('Product 7', 75, 13.4),
('Product 8', 220, 8.7),
('Product 9', 60, 16.1),
('Product 10', 90, 10.5),
('Product 11', 250, 14.1),
('Product 12', 110, 12.3),
('Product 13', 190, 13.9),
('Product 14', 80, 15.7),
('Product 15', 140, 11.6);

INSERT INTO table_products_prices (discount, sellPrice, purchasePrice) VALUES
(5.0, 120.0, 80.0),
(10.0, 200.0, 150.0),
(7.5, 95.0, 60.0),
(15.0, 250.0, 180.0),
(8.0, 140.0, 100.0),
(12.0, 180.0, 130.0),
(6.0, 105.0, 75.0),
(10.0, 220.0, 170.0),
(9.0, 150.0, 100.0),
(7.0, 110.0, 70.0),
(12.5, 230.0, 160.0),
(5.5, 130.0, 90.0),
(11.0, 190.0, 140.0),
(9.5, 170.0, 120.0),
(6.5, 150.0, 100.0);

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
