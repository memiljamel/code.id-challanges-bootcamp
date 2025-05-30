# Api Specification for Order

## Get Order

Request:

- Method: GET
- Endpoint: `/api/orders/{orderId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "orderId": "number",
    "orderNo": "string",
    "orderDate": "string",
    "requiredDate": "string",
    "shippedDate": "string",
    "freight": "number",
    "shipName": "string",
    "totalAmount": "number",
    "totalDiscount": "number",
    "paymentType": "string",
    "cardNo": "string",
    "transacNo": "string",
    "transacDate": "string",
    "refNo": "string",
    "details": [
      {
        "orderId": "number",
        "productId": "number",
        "unitPrice": "number",
        "quantity": "number",
        "discount": "number",
        "createdDate": "string",
        "modifiedDate": "string"
      }
    ],
    "userId": "number",
    "locationId": "number",
    "shipperId": "number",
    "bankCode": "string",
    "employeeId": "number",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Order

Request:

- Method: POST
- Endpoint: `/api/orders`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "userId": "number",
  "locationId": "number",
  "shipperId": "number",
  "bankCode": "string"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "orderId": "number",
    "orderNo": "string",
    "orderDate": "string",
    "requiredDate": "string",
    "shippedDate": "string",
    "freight": "number",
    "shipName": "string",
    "totalAmount": "number",
    "totalDiscount": "number",
    "paymentType": "string",
    "cardNo": "string",
    "transacNo": "string",
    "transacDate": "string",
    "refNo": "string",
    "details": [
      {
        "orderId": "number",
        "productId": "number",
        "unitPrice": "number",
        "quantity": "number",
        "discount": "number",
        "createdDate": "string",
        "modifiedDate": "string"
      }
    ],
    "userId": "number",
    "locationId": "number",
    "shipperId": "number",
    "bankCode": "string",
    "employeeId": "number",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Order

Request:

- Method: DELETE
- Endpoint: `/api/orders/{orderId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):
