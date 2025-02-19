# Overview
[DEMO LINK](http://43.203.253.201/javaforum/index) in AWS Lightsail<br>
[Introducion Slides](https://docs.google.com/presentation/d/1vBYzRyBnD6QBtBVS1ucBg5lsLNLAGAN6iVPz-sTVbyA/edit?usp=sharing)
This is a forum web application allows users to create posts, comment, interact with content, etc. <br>
It is built using **Spring Boot**, **PostgreSQL**, **Redis**, and **Nginx**, among others.
# Tech Stack
## Backend
* **Spring Boot**
* **PostgreSQL**: interact with Hibernate
* **Redis**: Like, Login
* **Nginx**
* AWS Lightsail
* AWS S3: upload avatars
## Frontend
* Thymeleaf
* jQuery
* Bootstrap

# Features
## User Authentication
* Login: Users can log in with their credentials. The system can remember login information, and CAPTCHA is required for added security.<br>
* Registration: Users can register with their email address. Email verification is required—an email is sent with a link, and the account is activated only once the user clicks the link.<br>
## User Profile
* Profile Picture: Users can set and update their profile picture to personalize their account.
## Post & Comment System
* Posts: Users can create posts, and posts can be liked by others.<br>
* Comments: Posts allow users to leave comments.<br>
* Reply to Comments: Comments can have replies, displayed as "UserA to UserB: Great comment!".

# E-R Diagram
![E-R Model](./E-R%20Model.png)

The following are some explanations of the columns in the tables.
## Table: comment
* entity_type: Represents the type of content the comment is related to.<br>
  If entity_type = 1, it refers to a discuss_post.<br>
  If entity_type = 2, it refers to a comment.
* entity_id: The ID of the targeted content.<br>
If entity_type = 1, this ID refers to the discuss_post ID.<br>
If entity_type = 2, this ID refers to the comment ID.<br>
* target_id: If the comment is a reply to another comment, this field points to the user_id of the target user. If not, this field remains empty.
## Table: comment
* salt: A random string added to the password before hashing for added security.
* status: Indicates whether the user's email has been verified.
* activation_code: A code that is included in the URL sent to the user's email for verification. The account is activated only when the user clicks the link in the email.
* header_url: The URL of the user's profile picture, stored in AWS S3.
