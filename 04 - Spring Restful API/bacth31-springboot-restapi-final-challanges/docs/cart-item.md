# Api Specification for Cart Item

## Get Cart Item Selected

Request:

- Method: GET
- Endpoint: `/api/carts/{cartId}/items/selected`
- Header:
  - Authorization: Bearer <token>
  - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": [
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
  ]
}
```

## Create Cart Item

Request:

- Method: POST
- Endpoint: `/api/carts/{cartId}/items`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "productId": "number",
  "quantity": "number",
  "discount": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
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
}
```

## Update Cart

Request:

- Method: PUT
- Endpoint: `/api/carts/{cartId}/items/{productId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "quantity": "number",
  "discount": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
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
}
```

## Delete Cart Item

Request:

- Method: DELETE
- Endpoint: `/api/carts/{cartId}/items/{productId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):
