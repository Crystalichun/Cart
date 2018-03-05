# Cart
Cart system will load txt file which record all customer orders into cart and calculate total fee automatically. Also you can add your coupon and promotion info in the txt file then the cart will discount for you. 

## Github
https://github.com/Crystalichun/Cart/

## How to run ?
- 1.Import project into eclipse or intelliJ.
- 2.Config jdk>=1.8.
- 3.Write orders info into orders.txt(if not exist, create manually please) which under root directory of the project.
- 4.Select CartManager.java, run as java application,then you will see the total fee of your input orders in the console.

## How to run unit test?
- 1.Import project into eclipse or intelliJ.
- 2.Select project and click run all tests.

## Format for orders txt file
      2013.11.11|0.7|电子   //promotion info, can be muitiple, one line by one line, if no promotion, leave one empty line
      //only one empty line, divide different part
      1*ipad : 2399.00
      1* 显示器 : 1799.00
      12*啤酒 : 25.00
      5*面包 : 9.00
      //only one empty line, divide different part
      2013.11.11  //account date, required=true
      //only one empty line, divide different part
      2014.3.2 1000 200 //coupon
## Output
- total fee -> 3083.60(double format:0.00)

