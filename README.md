# Discount Code Handler

***

## Table of contents

* [**General info**](#general-info)
* [**Setup**](#setup)
* [**Application view**](#application-view)
* [**Testing**](#testing)

***

## General info

Technologies used:
* Spring Boot
* Junit 5
* Mockito
* Gradle
* H2 Database
* Lombok


This project addresses the challenge of generating new discount codes and simulating their application 
to purchases, resulting in discounted product prices when the conditions are met. 
The project helps with the management of product listings, discount codes, and purchase records, 
all while accommodating various currencies.
***

## Setup

To build the application u need to run the command bellow once you are in the project directory.
> ./gradlew build

To run the application after running aforementioned command you need to navigate to the root directory and run the command bellow.
> ./gradlew bootRun

First, you need to run DiscountCodeHandlerApplication then past the link into your browser \
http://localhost:8080/swagger-ui/index.html 
Now you can access the endpoints which correspond to methods:


## Application view


> REST Service URLs
> >DiscountCode
>* Get all discount codes: GET http://localhost:8080/promo
>* Get details of a specific discount code: GET http://localhost:8080/promo/{promoCode}
>* Create a new discount code: POST http://localhost:8080/promo
>* sample request body {
  "promoCode": "NEWCODE",
  "expirationDate": "2024-12-31",
  "price": {
  "amount": 25.00,
  "currency": "USD"
  },
  "maximalNumberUsage": 100
  }
> >Product
> * Create a new product: POST http://localhost:8080/products \
> {
    "name": "New Product",
    "price": {
    "amount": 50.00,
    "currency": "USD"
    },
    "description": "Description of the new product"
    }
> * Get all products: GET http://localhost:8080/products
> > Purchase Controller
> * Calculate discounted price: GET http://localhost:8080/purchase/getDiscountPrice/{productId}/{promoCode} \
> Example: GET http://localhost:8080/purchase/getDiscountPrice/1/BIKE10
> * Simulate purchase: POST http://localhost:8080/purchase/simulatePurchase/{productId}/{promoCode} \
> Example: POST http://localhost:8080/purchase/simulatePurchase/1/BIKE10

## Testing 

Test Database Initialization
To facilitate testing and exploration of the application, a set of sample data is automatically populated into the database upon application startup. This sample data includes:

Discount Codes
BIKE10

Expiration Date: 2024-12-31
Price: $10.00 USD
Maximal Number of Usage: 100
BIKE20

Expiration Date: 2024-12-31
Price: $20.00 USD
Maximal Number of Usage: 200
BIKE25EUR

Expiration Date: 2024-12-31
Price: €25.00 EUR
Maximal Number of Usage: 150
SUMMER15

Expiration Date: 2024-08-31
Price: $15.00 USD
Maximal Number of Usage: 50
BIKE50

Expiration Date: 2024-12-31
Price: $50.00 USD
Maximal Number of Usage: 300
Products
Mountain Bike

Price: $500.00 USD
Description: A rugged mountain bike designed for off-road adventures.
Road Bike

Price: $750.00 USD
Description: A sleek road bike optimized for speed on paved roads.
Hybrid Bike

Price: $600.00 USD
Description: A versatile hybrid bike suitable for city commuting and light trails.
Electric Bike

Price: $1200.00 USD
Description: An eco-friendly electric bike with pedal assist for effortless riding.
Kids Bike

Price: $150.00 USD
Description: A fun and colorful bike specially designed for kids.




***



