# Products API Specs

## Create Products

Endpoint: POST `/api/products`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Request Body:

```json
{
  "name": "product name",
  "description": "product description",
  "price": 10000,
  "stock": 100
}
```

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "name": "product name",
    "description": "product description",
    "price": 10000,
    "stock": 100
  },
  "message": "Products added successfully"
}
```

Response Body(Failed):

```json
{
  "status": 400,
  "errors": true,
  "data": null,
  "message": "Product name must not blank, Description must not blank, Price must not blank, Stock must not blank",
}
```

## Get Products

Endpoint: GET `/api/pruducts`

Request Header:
- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": [
    {
      "id": "UUID",
      "name": "product name",
      "description": "product description",
      "price": 10000,
      "stock": 100
    },
    {
      "id": "UUID",
      "name": "product name",
      "description": "product description",
      "price": 10000,
      "stock": 100
    }
  ],
  "message": "Products data retrieved successfully"
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

## Get Product

Endpoint: POST `/api/products/:id`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "name": "product name",
    "description": "product description",
    "price": 10000,
    "stock": 100
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
  "message": "Product not found"
}
```

## Update Product

Endpoint: PUT `/api/products/:id`

Request Header:

- X-API-TOKEN: `TOKEN (MANDATORY)`

Request Body:

```json
{
  "name": "product name",
  "description": "product description",
  "price": 10000,
  "stock": 100
}
```

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "name": "new product name",
    "description": "new product description",
    "price": 10000,
    "stock": 100
  },
  "message": "Products updated successfully"
}
```

Response Body(Failed):

```json
{
  "status": 400,
  "errors": true,
  "data": null,
  "message": "Product name must not blank, Description must not blank, Price must not blank, Stock must not blank",
}
```

Response Body(Failed, 404):

```json
{
  "status": 404,
  "errors": true,
  "data": null,
  "message": "Product not found"
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

Endpoint: DELETE `/api/products/:id`

Request Header:
- X-API-TOKEN: `TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": null,
  "message": "Product deleted successfully"
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
  "message": "Product not found"
}
```