# Api Specification for Permission

## Get Permissions

Request:

- Method: GET
- Endpoint: `/api/permissions`
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
      "permissionId": "number",
      "permissionType": "string",
      "roleId": "number",
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

## Get Permission

Request:

- Method: GET
- Endpoint: `/api/permissions/{permissionId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "permissionId": "number",
    "permissionType": "string",
    "roleId": "number",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Create Permission

Request:

- Method: POST
- Endpoint: `/api/permissions`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "permissionType": "string",
  "roleId": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "permissionId": "number",
    "permissionType": "string",
    "roleId": "number",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Update Permission

Request:

- Method: PUT
- Endpoint: `/api/permissions/{permissionId}`
- Header:
    - Authorization: Bearer <token>
    - Content-Type: application/json
    - Accept: application/json

Body:

```json
{
  "permissionType": "string",
  "roleId": "number"
}
```

Response:

```json
{
  "code": "number",
  "status": "string",
  "data": {
    "permissionId": "number",
    "permissionType": "string",
    "roleId": "number",
    "createdDate": "string",
    "modifiedDate": "string"
  }
}
```

## Delete Permission

Request:

- Method: DELETE
- Endpoint: `/api/permissions/{permissionId}`
- Header:
    - Authorization: Bearer <token>
    - Accept: application/json

Response (No Content):