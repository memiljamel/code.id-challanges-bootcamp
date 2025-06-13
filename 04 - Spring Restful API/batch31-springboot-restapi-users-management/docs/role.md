# Api Specification for Role

## Get Roles

Request:

- Method: GET
- Endpoint: `/api/roles`
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
      "roleId": "number",
      "roleName": "string",
      "userId": "number",
      "permissions": [
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

## Get Role

Request:

- Method: GET
- Endpoint: `/api/roles/{roleId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "roleId": "number",
    "roleName": "string",
    "userId": "number",
    "permissions": [
      "string"
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Role

Request:

- Method: POST
- Endpoint: `/api/roles`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "roleName": "string",
  "userId": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "roleId": "number",
    "roleName": "string",
    "userId": "number",
    "permissions": [
      "string"
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Role

Request:

- Method: PUT
- Endpoint: `/api/roles/{roleId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "roleName": "string",
  "userId": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "roleId": "number",
    "roleName": "string",
    "userId": "number",
    "permissions": [
      "string"
    ],
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Role

Request:

- Method: DELETE
- Endpoint: `/api/roles/{roleId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):