# Api Specification for Cart

## Get Carts

Request:

- Method: GET
- Endpoint: `/api/carts`
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
      "cartId": "number",
      "userId": "number",
      "items": [
        {
          "cartId": "number",
          "productId": "number",
          "productName": "string",
          "productPictureUrl": "string",
          "productImageUrls": [
            "string"
          ],
          "supplierName": "string",
          "unitPrice": "number",
          "quantity": "number",
          "discount": "number",
          "discountPercentage": "string",
          "selected": "boolean",
          "createdDate": "string",
          "modifiedDate": "string"
        }
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

## Get Cart

Request:

- Method: GET
- Endpoint: `/api/carts/{cartId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "cartId": "number",
    "userId": "number",
    "items": [
      {
        "cartId": "number",
        "productId": "number",
        "productName": "string",
        "productPictureUrl": "string",
        "productImageUrls": [
          "string"
        ],
        "supplierName": "string",
        "unitPrice": "number",
        "quantity": "number",
        "discount": "number",
        "discountPercentage": "string",
        "selected": "boolean",
        "createdDate": "string",
        "modifiedDate": "string"
      }
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Get Cart User

Request:

- Method: GET
- Endpoint: `/api/carts/user/{userId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "cartId": "number",
    "userId": "number",
    "items": [
      {
        "cartId": "number",
        "productId": "number",
        "productName": "string",
        "productPictureUrl": "string",
        "productImageUrls": [
          "string"
        ],
        "supplierName": "string",
        "unitPrice": "number",
        "quantity": "number",
        "discount": "number",
        "discountPercentage": "string",
        "selected": "boolean",
        "createdDate": "string",
        "modifiedDate": "string"
      }
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Cart

Request:

- Method: POST
- Endpoint: `/api/carts`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "userId": "number",
  "items": [
    {
      "productId": "number",
      "quantity": "number",
      "discount": "number"
    }
  ]
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "cartId": "number",
    "userId": "number",
    "items": [
      {
        "cartId": "number",
        "productId": "number",
        "productName": "string",
        "productPictureUrl": "string",
        "productImageUrls": [
          "string"
        ],
        "supplierName": "string",
        "unitPrice": "number",
        "quantity": "number",
        "discount": "number",
        "discountPercentage": "string",
        "selected": "boolean",
        "createdDate": "string",
        "modifiedDate": "string"
      }
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Cart

Request:

- Method: POST
- Endpoint: `/api/carts/{cartId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "merge": "boolean",
  "items": [
    {
      "productId": "number",
      "quantity": "number",
      "discount": "number"
    }
  ]
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "cartId": "number",
    "userId": "number",
    "items": [
      {
        "cartId": "number",
        "productId": "number",
        "productName": "string",
        "productPictureUrl": "string",
        "productImageUrls": [
          "string"
        ],
        "supplierName": "string",
        "unitPrice": "number",
        "quantity": "number",
        "discount": "number",
        "discountPercentage": "string",
        "selected": "boolean",
        "createdDate": "string",
        "modifiedDate": "string"
      }
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Cart

Request:

- Method: DELETE
- Endpoint: `/api/carts/{cartId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):

## Create Checkout

Request:

- Method: POST
- Endpoint: `/api/carts/{cartId}/checkout`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Body:

```json
{
  "productIds": [
    "number"
  ]
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "cartId": "number",
    "userId": "number",
    "items": [
      {
        "cartId": "number",
        "productId": "number",
        "productName": "string",
        "productPictureUrl": "string",
        "productImageUrls": [
          "string"
        ],
        "supplierName": "string",
        "unitPrice": "number",
        "quantity": "number",
        "discount": "number",
        "discountPercentage": "string",
        "selected": "boolean",
        "createdDate": "string",
        "modifiedDate": "string"
      }
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```
