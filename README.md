# Rabobank Assignment for Authorizations Area

### **Prerequisites**
- JDK 11+
- Maven


### **Build**
This project is maven project so can be build using following maven command

    mvn clean install

### **Deploy**
- Run nl.rabobank.RaboAssignmentApplication from your IDE
  OR
- Run following command after maven build

        java -jar rabobank-assignment-api-0.0.1-SNAPSHOT.jar

### **API Information**
API can be accessed from following swagger url
http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/

Following endpoints can be used

####    Account Endpoints
- ####  Get Account Details
    - Get account details.

      Sample Request

             curl --location --request GET http://localhost:8080/rest/account/{ACCOUNT_NUMBER} 

      Sample Response (With HTTP Status Code - 200)

             {
                "accountNumber": "NL01BANK12341234",
                "accountHolderName": "Pranit",
                "balance": 100.0,
                "accountType": "SAVINGS"
             }
    

- #### Add new Account
  Creates a new account and returns it.

  Sample Request

          curl --header "Content-Type: application/json" \
            --request POST \
            --data '{"accountNumber":"NL01BANK12341234","accountHolderName":"Pranit","balance":100.0,"accountType":"SAVINGS"}' \
            http://localhost:8080/rest/account

  Sample Response (With HTTP Status Code - 201)

          {
            "accountNumber": "NL01BANK12341234",
            "accountHolderName": "Pranit",
            "balance": 100.0,
            "accountType": "SAVINGS"
          }

####    Power Of Attorney Endpoints
- ####  Add new authorization for user
  Adds new authorization for User

  Sample Request

        curl --header "Content-Type: application/json" \
            --request POST \
            --data '{"granteeName" : "Ash", "grantorName" : "Pranit", "accountNumber" : "NL01BANK12341234", "authorization" : "READ"}' \
            http://localhost:8080/rest/poa

  Sample Response (With HTTP Status Code - 201)


####    User Endpoints
- ####  Get User Details
    - Get user details along with account on which the user has read and write access.

      Sample Request

             curl --location --request GET http://localhost:8080/rest/user/{USER_NAME} 

      Sample Response (With HTTP Status Code - 200)

             {
                  "name": "Ash",
                  "readAccounts": [
                      {
                      "accountNumber": "NL01BANK12341234",
                      "accountHolderName": "Pranit",
                      "balance": 100.0,
                      "accountType": "SAVINGS"
                      }
                  ],
                  "writeAccounts": [
                      {
                      "accountNumber": "NL03BANK12341234",
                      "accountHolderName": "X",
                      "balance": 100.0,
                      "accountType": "SAVINGS"
                      }
                  ]
             }
    


### **Future Improvements**

- Integration Tests for error scenarios
- Extra endpoints for user and account


### **Author**
##### **Pranit Patil**


---------------------------------------------------------------------------------

## Rabobank Assignment for Authorizations Area

This project contains several premade modules for you to implement your code. We hope this helps you with ´what to put
where´.

### API

This module is where you have to implement the API interface and connect the other two modules

### Data

This module is where you implement all stateful Mongo data. We have provided an embedded Mongo configuration for you.
You just need to design the data you need to store and the repositories to store or retrieve it with.

### Domain

This module represents the domain you will be working with. The domain module presents classes for the power of attorney
model that contains a Read or Write authorization for a Payment or Savings account.

## The task at hand

Implement the following business requirement

- Users must be able to create write or read access for payments and savings accounts
- Users need to be able to retrieve a list of accounts they have read or write access for

Boundaries

- You can add dependencies as you like
- You can design the data and API models as you like (what a dream, isn't it?)

Notes

- The code should be ready to go to production on delivery

## Background information

A Power of Attorney is used when someone (grantor) wants to give access to his/her account to someone else (grantee). This
could be read access or write access. In this way the grantee can read/write in the grantors account.
Notice that this is a simplified version of reality.
