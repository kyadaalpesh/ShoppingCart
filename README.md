# ShoppingCart

### Here are the steps to execute the module
#### java -jar shopping-cart-1.0.jar 
[shopping_cart_logs.txt](shopping_cart_logs.txt)

#### Following is the output of my local excecution of this module
<pre>
****************************************************************************************************
|                            Hello alpesh!!, Welcome to Shopping cart.                             |
****************************************************************************************************
****************************************************************************************************
|                             Following are available discount slabs                               |
****************************************************************************************************
|                  1) DiscountSlab [0$ - 5000$                    ] [        0%]                   |
|                  2) DiscountSlab [5000$ - 10000$                ] [       10%]                   |
|                  3) DiscountSlab [10000$ - 9223372036854775807$ ] [       20%]                   |
****************************************************************************************************
Enter 0-3 to remove particular slab, Enter 4 For add new discount slab or press any key to countine :3
****************************************************************************************************
|                             Following are available discount slabs                               |
****************************************************************************************************
|                  1) DiscountSlab [0$ - 5000$                    ] [        0%]                   |
|                  2) DiscountSlab [5000$ - 10000$                ] [       10%]                   |
****************************************************************************************************
Enter 0-2 to remove particular slab, Enter 3 For add new discount slab or press any key to countine :3
Please pass the discount slab as : lowerLimit-upperLimit=discount(%). i.e 5000-10000=10:10000-25000=15
****************************************************************************************************
|                             Following are available discount slabs                               |
****************************************************************************************************
|                  1) DiscountSlab [0$ - 5000$                    ] [        0%]                   |
|                  2) DiscountSlab [5000$ - 10000$                ] [       10%]                   |
|                  3) DiscountSlab [10000$ - 25000$               ] [       15%]                   |
****************************************************************************************************
Enter 0-3 to remove particular slab, Enter 4 For add new discount slab or press any key to countine :4
Please pass the discount slab as : lowerLimit-upperLimit=discount(%). i.e 5000-10000=10:25000-~=20
****************************************************************************************************
|                             Following are available discount slabs                               |
****************************************************************************************************
|                  1) DiscountSlab [0$ - 5000$                    ] [        0%]                   |
|                  2) DiscountSlab [5000$ - 10000$                ] [       10%]                   |
|                  3) DiscountSlab [10000$ - 25000$               ] [       15%]                   |
|                  4) DiscountSlab [25000$ - 9223372036854775807$ ] [       20%]                   |
****************************************************************************************************
Enter 0-4 to remove particular slab, Enter 5 For add new discount slab or press any key to countine :
****************************************************************************************************
|                        Following are available customer purchase details                         |
****************************************************************************************************
| 1)       CustomterBill{customerName=Alpesh, customerType=Regular, purchaseAmount=5000}           |
| 2)      CustomterBill{customerName=Shailesh, customerType=Regular, purchaseAmount=10000}          |
| 3)      CustomterBill{customerName=Darious, customerType=Regular, purchaseAmount=15000}          |
****************************************************************************************************
Enter 0-3 to remove particular purchase detail, Enter 4 For add new purchase detail or press any key to countine :4
Please pass the purchase detail as :  customerName CustomerType purchaseAmount:Test Test 17000
****************************************************************************************************
|                        Following are available customer purchase details                         |
****************************************************************************************************
| 1)       CustomterBill{customerName=Alpesh, customerType=Regular, purchaseAmount=5000}           |
| 2)      CustomterBill{customerName=Shailesh, customerType=Regular, purchaseAmount=10000}          |
| 3)      CustomterBill{customerName=Darious, customerType=Regular, purchaseAmount=15000}          |
| 4)         CustomterBill{customerName=Test, customerType=Test, purchaseAmount=17000}             |
****************************************************************************************************
Enter 0-4 to remove particular purchase detail, Enter 5 For add new purchase detail or press any key to countine :5
Please pass the purchase detail as :  customerName CustomerType purchaseAmount:Test2 Test2 30000
****************************************************************************************************
|                        Following are available customer purchase details                         |
****************************************************************************************************
| 1)       CustomterBill{customerName=Alpesh, customerType=Regular, purchaseAmount=5000}           |
| 2)      CustomterBill{customerName=Shailesh, customerType=Regular, purchaseAmount=10000}          |
| 3)      CustomterBill{customerName=Darious, customerType=Regular, purchaseAmount=15000}          |
| 4)         CustomterBill{customerName=Test, customerType=Test, purchaseAmount=17000}             |
| 5)        CustomterBill{customerName=Test2, customerType=Test2, purchaseAmount=30000}            |
****************************************************************************************************
Enter 0-5 to remove particular purchase detail, Enter 6 For add new purchase detail or press any key to countine :
****************************************************************************************************
|                                   Generating the bill details                                    |
****************************************************************************************************
| Customer Name/Type                      Purchase Amount                              Bill Amount |
****************************************************************************************************
| Alpesh Regular                               5000$                                         5000$ |
| Shailesh Regular                             10000$                                         9000$ |
| Darious Regular                              15000$                                        12750$ |
| Test Test                                    17000$                                        14450$ |
| Test2 Test2                                  30000$                                        24000$ |
****************************************************************************************************
|                                 Bye Bye alpesh!!, See you again.                                  |
****************************************************************************************************
------------------------------------------------------------------------
</pre>
