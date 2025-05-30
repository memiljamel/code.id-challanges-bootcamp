# Api Specification for Shipper

## Get Shippers

Request:

- Method: GET
- Endpoint: `/api/shippers`
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
      "shipperId": "number",
      "companyName": "string",
      "phone": "string",
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

## Get Shipper

Request:

- Method: GET
- Endpoint: `/api/shippers/{shipperId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "shipperId": "number",
    "companyName": "string",
    "phone": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Shipper

Request:

- Method: POST
- Endpoint: `/api/shippers`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "shipperId": "number",
  "companyName": "string",
  "phone": "string"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "shipperId": "number",
    "companyName": "string",
    "phone": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Shipper

Request:

- Method: PUT
- Endpoint: `/api/shippers/{shipperId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "companyName": "string",
  "phone": "string"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "shipperId": "number",
    "companyName": "string",
    "phone": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Shipper

Request:

- Method: DELETE
- Endpoint: `/api/shippers/{shipperId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):
