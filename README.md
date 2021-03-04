[![Build Status](https://travis-ci.com/CphTobias/dat3sem-week5-rest-persons-dto.svg?branch=main)](https://travis-ci.com/CphTobias/dat3sem-week5-rest-persons-dto)

# REST Persons dto assignment 

## Author
👤 **Tobias Zimmermann**

* [Github](https://github.com/CphTobias)

## API Documentation

### [Project link](https://tobias-z.com/insession-ca1/)

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
      "phoneNumber": string
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
      "phoneNumber": string
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
          "phoneNumber": string 
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
