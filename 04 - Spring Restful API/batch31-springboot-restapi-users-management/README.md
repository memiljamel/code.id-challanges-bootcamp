# User, Role, and Permission Management

This project implements a basic user, role, and permission management system using Java records (class record) for data
transfer objects (DTOs) and entities. It demonstrates field mapping, validation, and basic business logic handling using
modern Java features.

### References

- Java Records: [https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html](https://docs.oracle.com/en/java/javase/17/docs/api/java.base/java/lang/Record.html)

## Features

- Create user, role and permission data using entity, and DTO by using class `record`.
- Create field mapping a simple request to a class record's field.
- Validating the field against the record's constructor.
- Mapping using the stream API library and an anonymous class (nice to have).
- Unit testing is a must.
- Optional frontend integration using:
    - ReactJS
    - Angular
    - VueJS
    - Spring WebMVC

## Business Case

The business scenario involves managing users and their associated roles and permissions using an ORM framework.

### Entity Definitions

#### User

- `UserID` (Primary Key)
- `Username`
- `Password`

#### Role

- `RoleID` (Primary Key)
- `RoleName`

#### Permission

- `PermissionID` (Primary Key)
- `PermissionType` (Enum: `READ`, `READ_WRITE`)

### Relationships

- A **User** can have multiple **Roles** (One-to-Many).
- A **Role** can have multiple **Permissions** (One-to-Many).

### Functional Requirements

| Entity     | Operations                          |
|------------|-------------------------------------|
| User       | Create, Read, Update, Delete (CRUD) |
| Role       | Create, Read, Update, Delete (CRUD) |
| Permission | Create, Read, Update, Delete (CRUD) |
