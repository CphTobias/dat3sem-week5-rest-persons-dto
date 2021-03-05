[![Build Status](https://travis-ci.com/CphTobias/dat3sem-week5-rest-persons-dto.svg?branch=main)](https://travis-ci.com/CphTobias/dat3sem-week5-rest-persons-dto)

# REST Persons dto assignment 

## Author
👤 **Tobias Zimmermann**

* [Github](https://github.com/CphTobias)

## API Documentation

### [Project link](https://tobias-z.com/rest-persons-dto/)

Base URL for all endpoints https://tobias-z.com/rest-persons-dto/api

#### Person

```http
GET /person/{id}
```
| Description |
| :--- |
| Find person by id |

```http
GET /person/
```

| Description |
| :--- |
| Find all people |

___

#### Required body for POST and PUT
```javascript
{
      "firstName": string,
      "lastName": string,
      "phoneNumber": string,
      "address": {
          "street": string,
          "zip": string,
          "city": string
      } 
}
```

___

```http
POST /person/
```

| Description | 
| :--- | 
| Create a new person |

```http
PUT /person/{id}
```

| Description | 
| :--- | 
| Updates an existing person by their id |

```http
DELETE /person/{id}
```

| Description | 
| :--- | 
| Delete a person by id |

## Responses

A single person:
```javascript
{
      "id": int,
      "firstName": string,
      "lastName": string,
      "phoneNumber": string,
      "address": {
          "street": string,
          "zip": string,
          "city": string
      }
}
```

Multiple people:
```javascript
{
    "all": [ 
        { 
          "id": int,
          "firstName": string,
          "lastName": string,
          "phoneNumber": string,
          "address": {
              "street": string,
              "zip": string,
              "city": string
          }
        }, 
    ]
}
```

Error response:
```javascript
{
      "code": int,
      "message": string
}
```
