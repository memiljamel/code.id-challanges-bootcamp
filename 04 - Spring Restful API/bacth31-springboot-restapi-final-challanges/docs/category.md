# Api Specification for Category

## Get Categories

Request:

- Method: GET
- Endpoint: `/api/categories`
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
      "categoryId": "number",
      "categoryName": "string",
      "description": "string",
      "pictureUrl": "string",
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

## Get Category

Request:

- Method: GET
- Endpoint: `/api/categories/{categoryId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "categoryId": "number",
    "categoryName": "string",
    "description": "string",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Category

Request:

- Method: POST
- Endpoint: `/api/categories`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: multipart/form-data
    - Accept: application/json

Form:

- categoryId: number
- categoryName: string
- description: string
- picture: file

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "categoryId": "number",
    "categoryName": "string",
    "description": "string",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Category

Request:

- Method: PUT
- Endpoint: `/api/categories/{categoryId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: multipart/form-data
    - Accept: application/json

Form:

- categoryName: string
- description: string
- picture: file

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "categoryId": "number",
    "categoryName": "string",
    "description": "string",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Category

Request:

- Method: DELETE
- Endpoint: `/api/categories/{categoryId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):
