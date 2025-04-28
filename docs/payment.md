# Payment API Specs

## Create Payment

Endpoint: POST `/api/payment`

Request Header: 

- Authorization: `Bearer TOKEN (MANDATORY)`

Request Body:

```json
{
  "amount": 1000000,
  "status": ENUM("pending", "completed", "failed")
}
```

Response Body(Failed):

```json
{
  "status": 400,
  "errors": true,
  "data": null,
  "message": "Amount must not empty"
}
```

Response Body(Failed):

```json
{
  "status": 401,
  "errors": true,
  "data": null,
  "message": "Unauthorized"
}
```

## Get Payment

Endpoint: GET `/api/payment`

Request Header:
- Authorization: `Bearer TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": [
    {
      "id": "UUID",
      "amount": 1000000,
      "status": ENUM("pending", "completed", "failed"),
      "created_at": "DD-MM-YYYY, hh:mm:ss",
      "updated_at": "DD-MM-YYYY, hh:mm:ss"
    },
    {
      "id": "UUID",
      "amount": 1000000,
      "status": ENUM("pending", "completed", "failed"),
      "created_at": "DD-MM-YYYY, hh:mm:ss",
      "updated_at": "DD-MM-YYYY, hh:mm:ss"
    }
  ],
  "message": "Payment data retrieved successfully"
}
```

Response Body(Failed):

```json
{
  "status": 401,
  "errors": true,
  "data": null,
  "message": "Unauthorized access"
}
```

## Get Paymet

Endpoint: POST `/api/payment/{id}`

Request Header:

- Authorization: `Bearer TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "amount": 1000000,
    "status": ENUM("pending", "completed", "failed"),
    "created_at": "DD-MM-YYYY, hh:mm:ss",
    "updated_at": "DD-MM-YYYY, hh:mm:ss"
  },
  "message": "Login success"
}
```

Response Body(Failed):

```json
{
  "status": 401,
  "errors": true,
  "data": null,
  "message": "Unauthorized access"
}
```

Response Body(Failed, 404):

```json
{
  "status": 404,
  "errors": true,
  "data": null,
  "message": "Payment not found"
}
```

## Update Payment

Endpoint: PATCH `/api/payment/{id}`

Request Header:

- Authorization: `Bearer TOKEN (MANDATORY)`

Request Body:

```json
{
  "status": ENUM("pending", "completed", "failed")
}
```

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "amount": 1000000,
    "status": ENUM("pending", "completed", "failed"),
    "created_at": "DD-MM-YYYY, hh:mm:ss",
    "updated_at": "DD-MM-YYYY, hh:mm:ss"
  },
  "message": "Payment updated successfully"
}
```

Response Body(Failed):

```json
{
  "status": 400,
  "errors": true,
  "data": null,
  "message": "Payment status must not empty",
}
```

Response Body(Failed, 404):

```json
{
  "status": 404,
  "errors": true,
  "data": null,
  "message": "Payment not found"
}
```

Response Body(Failed, 401):

```json
{
  "status": 401,
  "errors": true,
  "data": null,
  "message": "Unauthorized access"
}
```

## Delete Payment

Endpoint: DELETE `/api/payment/{id}`

Request Header:
- Authorization: `Bearer TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": null,
  "message": "Payment deleted successfully"
}
```

Response Body(Failed):

```json
{
  "status": 401,
  "errors": true,
  "data": null,
  "message": "Unauthorized access"
}
```

Response Body(Failed, 404):

```json
{
  "status": 404,
  "errors": true,
  "data": null,
  "message": "Payment not found"
}
```