# propertyRentalBackend
A Real-estate web application backend

## Tech
- Java
- Spring-Boot
- Maven
- Spring Security (Basic Auth)
- ORM(Hibernate)
- My-Sql
- Aws & Aws-s3

## Plugins
- Project Lombok

## Problem Statement
- Getting a home/flat in metro cities was quite tough, hard and revolves arround the brokers and their syndicate of their brokerage amount
- So this project solves the problem of rental problem in metro cities

## Project Description
1 This project was a backend part of an property ad-posting website <br>
2 A user can surf though ads posted by the owner of properties(No Authentication required for surfing the ads posted) <br>
3 A user can directly get contact details of the owners <br>
4 Owner requires authentication to post a ad of his/her property online <br>
5 A owner can register him/her self a confirmation mail will be sent (not impl yet) <br>
6 All proprty images uploaded by the owner was uploaded to aws-s3 bkt as s3 objects and their aws-key,bkt name was stored to database along with propertyId, to fetch it again<br>
7 Implemented Rest Apis for all types GET requests, which will be use full while users surfing for property <br>
8 GET request responses was paginated and sorted(according to special and non-special, a property tagged as special will be arranged first)<br>
9 CRUD operations was done for the owner, needs authentication to perform any POST or PUT or DELETE operation<br>
10 My-Sql was the database to store all information regarding properties and owners and etc info <br>

## Setup
>git clone https://github.com/umamahesh33/e-wallet-application.git
- need to generate aws-keys for putting and getting objects from s3-bucket

## REST-API documentation

