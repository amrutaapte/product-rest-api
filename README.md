# product-rest-api
Spring Boot Rest API that consumes and generates JSON allowing users to add new products and retrieve them based on their category.

## Installation 
To run the project, clone and import as Maven project in Spring Tool Suite or any other IDE. It can also be built using Maven.
This application will run on localhost.

## Design considerations
The application uses Java 8, Spring Boot, Hibernate and JPA. The Product entity is designed to have a many-to-many relationship with Tag entity. Further, the entries Tag table is kept unique for normalization.

## Database
In its default configuration, the application uses an in-memory database, H2. The h2 console is automatically exposed, for example at http://localhost:8080/h2-console.
The datasource is generated with url jdbc:h2:mem:products, accessible by user "centric". No password has been set.

## Tests
Apllication has basic unit test for ProductController Post and Get methods and functional test using the Rest Assured dependency. 

## Error handling
The application handles generic 4XX errors and has a fallback error handler to mask stack trace messages.

## Logging
Global logger settings help log messages to the Common.log file in /logs folder. 


## REST endpoints

**POST /v1/products** 

Add new Product using */v1/products* using the following sample JSON:
````
{
"name":"Red Hugo",
"description":"Red Hugo Boss shirt",
"brand":"Hugo Boss",
"tags":["red", "slim fit"],
"category":"apparel"
}
````
with expected response:
````
{
    "id": "ea4f99db-05d4-4980-9677-bf1239d21eb2",
    "name": "Red Hugo",
    "description": "Red Hugo Boss shirt",
    "brand": "Hugo Boss",
    "tags": [
        "red",
        "slim fit"
    ],
    "category": "apparel",
    "createdAt": "2021-10-25T18:58:41.1478863Z"
}
````

**GET v1/products/{category}**

Get list of products filtered on Category field, ordered by the Date of Creation, newest first using *v1/products/{category}*
The default pagination variables are set as pageNo=0 and pageSize=5. These can be set by user as Request Parameters like so *v1/products/{category}?pageNo=1&pageSize=10*

Expected response:
````
{
    "content": [
        {
            "id": "ea4f99db-05d4-4980-9677-bf1239d21eb2",
            "name": "Red Hugo",
            "description": "Red Hugo Boss shirt",
            "brand": "Hugo Boss",
            "tags": [
                "red",
                "slim fit"
            ],
            "category": "apparel",
            "createdAt": "2021-10-25T18:58:41.147886Z"
        }
    ],
    "pageable": {
        "sort": {
            "empty": false,
            "sorted": true,
            "unsorted": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 5,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalElements": 1,
    "totalPages": 1,
    "size": 5,
    "number": 0,
    "sort": {
        "empty": false,
        "sorted": true,
        "unsorted": false
    },
    "first": true,
    "numberOfElements": 1,
    "empty": false
}
````





