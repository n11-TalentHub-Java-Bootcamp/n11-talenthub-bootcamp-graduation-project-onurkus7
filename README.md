# n11 Talenthub Bootcamp Graduation Project: Credit Application

# About The Project
Web application that answers the credit application requests made by the customer according to the created business rules. This project includes functions such as giving credit to the customer and determining the credit limit, adding new users to the system, that inform the user of credit information via SMS. These functions are determined according to certain.
## Table of Contents
- [Tech Stack](#tech-stack)
- [Installing](#installing)
    - [Maven](#maven)
- [Services](#services)
    - [Customer Service](#customer-service)
    - [Credit Service](#credit-service)
    - [SMS Service](#sms-service)
  

## Tech Stack
- OpenJDK 11
- Maven
- Spring Boot
- Spring Data JPA
- Spring Swagger
- Lombok
- PostgreSQL
- Twilio SMS API


## Installing
Can be installed and run as:
### Maven
**1. Clone the application**

```bash
git clone https://github.com/n11-TalentHub-Java-Bootcamp/n11-talenthub-bootcamp-graduation-project-onurkus7
```
**2. Go to the project directory**
```bash
cd n11-talenthub-bootcamp-graduation-project-onurkus7
```
**3. Run**
```bash
mvnw spring-boot:run
```
## Services

### Customer Service
Customer Service is a service that includes methods to save the information of customers who will apply for credit in the database, to update customer information, to delete customer information, to return customer information by ID number and all customers' information.

**Endpoints**

| Endpoint | Method |  Params | Description                                                                                                                                                           
|: -------------:|:--------:|:-------:|: --------------------------------------------------------------------------------------------------------------------------------------------------------------------- :|
|    `/api/customers/{identityId}` | GET | identityId | Returns customer information based on `identityId`.|                  
|    `/api/customers/{identityId}` | PUT | identityId | Updates customer information based on `identityId`.|
|    `/api/customers/{identityId}` | DELETE | identityId | Deletes customer information based on `identityId`.|
|    `/api/customers` | GET | none | List all customer.
|    `/api/customers` | POST | customerSaveDto | Creates a new customer according to the `customerSaveDto` request body.|

### Credit Service
Credit Service includes the information (salary, collateral) previously received from the user and recorded in the database, and the methods that determine the credit limit that can be given to the user over the randomly generated credit score. It sends the credit information to the Message Service.

**Endpoints**

| Endpoint | Method |  Params | Description |                                                                                                                                                  
|: -------------:|:--------:|:-------:|: --------------------------------------------------------------------------------------------------------------------------------------------------:|      
| `/api/credits/{identityId}` | POST | identityId | Customer credit information is generated based on `identityId`.|                                                         
| `/api/credits/{identityId}/{birthdayDate}`| GET | identityId, birthdayDate |Returns customer Credit information based on `identityId` and `birthdayDate` parameters.|

### Message Service
It accesses the customer's phone number information according to the identityId from the credit information coming from the Credit Service, and then sends the credit information to the user as an SMS with the help of Twilio SMS API. Records the outgoing SMS content to the database.