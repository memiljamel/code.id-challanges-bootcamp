# Api Specification for User

## Get Users

Request:

- Method: GET
- Endpoint: `/api/users`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json
- Query:
    - sort: null
    - page: 0
    - size: 20

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "userId": "number",
      "username": "string",
      "roles": [
        "string"
      ],
      "createdDate": "string",
      "modifiedDate": "string"
    }
  ],
  "meta": {
    "currentPage": "number",
    "perPage": "number",
    "total": "number",
    "totalPage": "number",
    "hasPreviousPage": "boolean",
    "hasNextPage": "boolean"
  }
}
```

## Get User

Request:

- Method: GET
- Endpoint: `/api/users/{userId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "userId": "number",
    "username": "string",
    "roles": [
      "string"
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create User

Request:

- Method: POST
- Endpoint: `/api/users`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "username": "string",
  "password": "string"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "userId": "number",
    "username": "string",
    "roles": [
      "string"
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update User

Request:

- Method: PUT
- Endpoint: `/api/users/{userId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "username": "string",
  "password": "string"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "userId": "number",
    "username": "string",
    "roles": [
      "string"
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete User

Request:

- Method: DELETE
- Endpoint: `/api/users/{userId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):