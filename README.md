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
>git clone https://github.com/umamahesh33/propertyRentalBackend.git
- need to generate aws-keys for putting and getting objects from s3-bucket

## REST-API documentation
- http://localhost:8080/swagger-ui.html

## Screenshots
-Database <br>
![gitKdd1](https://user-images.githubusercontent.com/63411924/155556649-8ad66958-890b-4522-a9e9-93eb89340835.png)

-Property Table <br>
![gitkdd2](https://user-images.githubusercontent.com/63411924/155556681-3eb70ead-604a-40a2-a799-aace94c9409f.png)
<br>
![gitkdd3](https://user-images.githubusercontent.com/63411924/155556700-7fa364f6-bcec-4e6e-9409-838fd2cdac2d.png)

-Admin Table(Initially project was coded in a way that only admins can upload the property and owner should conatct admin to upload their property)<br>
-In later I wanted to changet it admin to owner<br>
![gitkdd4](https://user-images.githubusercontent.com/63411924/155556716-14ac6d5e-f9a4-42ee-8d9d-09f9c6c344c7.png)

-Owner table<br>
![gitkdd5](https://user-images.githubusercontent.com/63411924/155556751-49114a5f-7e51-4724-931f-dc19142e7246.png)

