# User API Specs

## Register User

Endpoint: POST `/api/user/register`

Request Body:

```json
{
  "email": "yourname@example.com",
  "password": "secret_password",
  "name": "John Doe"
}
```

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": "OK",
  "message": "User registered successfully"
}
```

Response Body(Failed):

```json
{
  "status": 400,
  "errors": true,
  "data": null,
  "message": "username must not blank, email must not blank, password must not blank"
}
```

## Get User

Endpoint: GET `/api/user/current`

Request Header:
- Authorization: `Bearer TOKEN (MANDATORY)`

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "id": "UUID",
    "name": "John Doe",
    "email": "yourname@example.com"
  },
  "message": "User data retrieved successfully"
}
```

Response Body(Failed):

```json
{
  "status": 401,
  "errors": true,
  "data": null,
  "message": "Invalid credentials"
}
```

## Login User

Endpoint: POST `/api/auth/login`

Request Body:

```json
{
  "email": "yourname@example.com",
  "password": "secret_password"
}
```

Response Body(Success):

```json
{
  "status": 200,
  "errors": false,
  "data": {
    "token": "TOKEN",
    // milliseconds
    "expiredAt": 123456789
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
  "message": "Invalid credentials"
}
```