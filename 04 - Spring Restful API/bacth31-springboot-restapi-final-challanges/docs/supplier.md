# Api Specification for Supplier

## Get Suppliers

Request:

- Method: GET
- Endpoint: `/api/suppliers`
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
      "supplierId": "number",
      "companyName": "string",
      "contactName": "string",
      "contactTitle": "string",
      "phone": "string",
      "fax": "string",
      "homepage": "string",
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

## Get Supplier

Request:

- Method: GET
- Endpoint: `/api/suppliers/{supplierId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "supplierId": "number",
    "companyName": "string",
    "contactName": "string",
    "contactTitle": "string",
    "phone": "string",
    "fax": "string",
    "homepage": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Supplier

Request:

- Method: POST
- Endpoint: `/api/suppliers`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "supplierId": "number",
  "companyName": "string",
  "contactName": "string",
  "contactTitle": "string",
  "phone": "string",
  "fax": "string",
  "homepage": "string"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "supplierId": "number",
    "companyName": "string",
    "contactName": "string",
    "contactTitle": "string",
    "phone": "string",
    "fax": "string",
    "homepage": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Supplier

Request:

- Method: PUT
- Endpoint: `/api/suppliers/{supplierId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "companyName": "string",
  "contactName": "string",
  "contactTitle": "string",
  "phone": "string",
  "fax": "string",
  "homepage": "string"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "supplierId": "number",
    "companyName": "string",
    "contactName": "string",
    "contactTitle": "string",
    "phone": "string",
    "fax": "string",
    "homepage": "string",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Supplier

Request:

- Method: DELETE
- Endpoint: `/api/suppliers/{supplierId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):
