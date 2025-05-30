# Api Specification for Product

## Get Products

Request:

- Method: GET
- Endpoint: `/api/products`
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
      "productId": "number",
      "productName": "string",
      "supplierId": "number",
      "categoryId": "number",
      "quantityPerUnit": "string",
      "unitPrice": "number",
      "unitsInStock": "number",
      "unitsInOrder": "number",
      "reorderLevel": "number",
      "discontinued": "number",
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

## Get Products Search

Request:

- Method: GET
- Endpoint: `/api/products/search`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json
- Query:
    - keyword: null
    - page: 0
    - size: 20

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "productId": "number",
      "productName": "string",
      "supplierId": "number",
      "categoryId": "number",
      "quantityPerUnit": "string",
      "unitPrice": "number",
      "unitsInStock": "number",
      "unitsInOrder": "number",
      "reorderLevel": "number",
      "discontinued": "number",
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

## Get Product

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
    "productId": "number",
    "productName": "string",
    "supplierId": "number",
    "categoryId": "number",
    "quantityPerUnit": "string",
    "unitPrice": "number",
    "unitsInStock": "number",
    "unitsInOrder": "number",
    "reorderLevel": "number",
    "discontinued": "number",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Product

Request:

- Method: POST
- Endpoint: `/api/categories`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: multipart/form-data
    - Accept: application/json

Body:

```json
{
  "productName": "string",
  "supplierId": "number",
  "categoryId": "number",
  "quantityPerUnit": "string",
  "unitPrice": "number",
  "unitsInStock": "number",
  "unitsInOrder": "number",
  "reorderLevel": "number",
  "discontinued": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "productId": "number",
    "productName": "string",
    "supplierId": "number",
    "categoryId": "number",
    "quantityPerUnit": "string",
    "unitPrice": "number",
    "unitsInStock": "number",
    "unitsInOrder": "number",
    "reorderLevel": "number",
    "discontinued": "number",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Product Upload

Request:

- Method: POST
- Endpoint: `/api/categories/upload`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: multipart/form-data
    - Accept: application/json

Form:

- data: json
- file: file

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "productId": "number",
    "productName": "string",
    "supplierId": "number",
    "categoryId": "number",
    "quantityPerUnit": "string",
    "unitPrice": "number",
    "unitsInStock": "number",
    "unitsInOrder": "number",
    "reorderLevel": "number",
    "discontinued": "number",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Product

Request:

- Method: PUT
- Endpoint: `/api/categories/{categoryId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: multipart/form-data
    - Accept: application/json

Body:

```json
{
  "productName": "string",
  "supplierId": "number",
  "categoryId": "number",
  "quantityPerUnit": "string",
  "unitPrice": "number",
  "unitsInStock": "number",
  "unitsInOrder": "number",
  "reorderLevel": "number",
  "discontinued": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "productId": "number",
    "productName": "string",
    "supplierId": "number",
    "categoryId": "number",
    "quantityPerUnit": "string",
    "unitPrice": "number",
    "unitsInStock": "number",
    "unitsInOrder": "number",
    "reorderLevel": "number",
    "discontinued": "number",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Product Upload

Request:

- Method: PUT
- Endpoint: `/api/categories/{categoryId}/upload`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: multipart/form-data
    - Accept: application/json

Form:

- data: json
- file: file

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "productId": "number",
    "productName": "string",
    "supplierId": "number",
    "categoryId": "number",
    "quantityPerUnit": "string",
    "unitPrice": "number",
    "unitsInStock": "number",
    "unitsInOrder": "number",
    "reorderLevel": "number",
    "discontinued": "number",
    "pictureUrl": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Product

Request:

- Method: DELETE
- Endpoint: `/api/categories/{categoryId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):

## Create Product Image

Request:

- Method: POST
- Endpoint: `/api/products/{productId}/images`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: multipart/form-data
    - Accept: application/json

Form:

- productId: number
- images: file[]

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": [
    {
      "productImageId": "number",
      "fileName": "string",
      "fileSize": "number",
      "fileType": "string",
      "fileUri": "string",
      "productId": "number"
    }
  ]
}
```

## Delete Product Image

Request:

- Method: DELETE
- Endpoint: `/api/products/{productId}/images/{imageId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):
