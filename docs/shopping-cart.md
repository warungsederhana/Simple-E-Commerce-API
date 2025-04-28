# Shopping Cart API Specs

## Create Shopping Cart (Add product to shopping cart)

Endpoint: POST `/api/shopping-cart`

Request Header:

- Authorization: `Bearer TOKEN (MANDATORY)`

Request Body:

```json
{
  "quantity": 5,
  "product_id": "UUID"
}
```

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "quantity": 5,
    "product_id": "UUID"
  },
  "message": "Shopping Cart added successfully"
}
```

Response Body(Failed):

```json
{
  "status": 400,
  "errors": true,
  "data": null,
  "message": "Quantity must not empty, Product must not empty"
}
```

## Get Shopping Carts

Endpoint: GET `/api/shopping-cart`

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
      "quantity": 5,
      "product_id": "UUID"
    },
    {
      "id": "UUID",
      "quantity": 5,
      "product_id": "UUID"
    }
  ],
  "message": "Shopping Cart data retrieved successfully"
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

## Get Shoping Cart

Endpoint: POST `/api/shopping-cart/{id}`

Request Header:

- Authorization: `Bearer TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "quantity": 5,
    "product_id": "UUID"
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
  "message": "Shopping Cart not found"
}
```

## Update Product

Endpoint: PATCH `/api/shopping-cart/{id}`

Request Header:

- Authorization: `Bearer TOKEN (MANDATORY)`

Request Body:

```json
{
  "quantity": 5
}
```

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "quantity": 3,
    "product_id": "UUID"
  },
  "message": "Shopping Cart updated successfully"
}
```

Response Body(Failed):

```json
{
  "status": 400,
  "errors": true,
  "data": null,
  "message": "Quantity must not empty"
}
```

Response Body(Failed, 404):

```json
{
  "status": 404,
  "errors": true,
  "data": null,
  "message": "Shopping Cart not found"
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

## Delete Product

Endpoint: DELETE `/api/shopping-cart/{id}`

Request Header:
- Authorization: `Bearer TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": null,
  "message": "Shopping Cart deleted successfully"
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
  "message": "Shopping Cart not found"
}
```