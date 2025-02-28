# Requirement
    Install the Docker

# To run the application run these two command
    1. docker-compose up --build 

# Functional Requirement
    1. make transaction
    2. get transaction

# Entities
    1. ServiceEntity - it is own application services
    2. MerchantEntity - it is deliver that we want make transaction
    3. AgentEntity - it is consumer from us that wants to consume our service
    4. TransactionEntity - it is main entity that stores all data related transaction
    
# Api
    1. Register Agent - /api/v1/agent
    2. Get Jwt access token - /api/v1/agent/login
    3. do transaction for check - /api/v1/transaction/check
    4. do transaction for pay - /api/v1/transaction/pay
    5. get transactions - /api/v1/transaction (for now returned all list but pagination is not problem)

# Out of scope I would enhance application
    1. build microservice architecture - to be more scability
    2. would add kafka - to asyn process
    3. would use sql and no sql both regarding CAP theorem but sql postgres best chose for ACID
    
# Used technology & framework
    1. Java 17
    2. Spring boot '3.4.3' (Jpa, Spring security, Jwt)
    3. Postgres
