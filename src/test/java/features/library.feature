Feature: library API
Scenario Outline: Add the book successfully
Given Add the book with the payload "<name>" "<isbn>" "<aisle>" and "<author>"
When user calls "AddBookAPI" api with "POST"
Then Api got success with status code 200
Given get book api payload
When user calls "GetBookAPI" with get http request
Then Api got success with status code 200   
And verify "<name>" "<isbn>" "<aisle>" and "<author>"


Examples:
|name   | isbn    | aisle | author   |
|Book3  | ATP     | 1323  |pachu     |

#Scenario: Get the book details which is added
#Given get book api payload
#When user calls "GetBookAPI" with get http request
#Then Api got success with status code 200   
#And verify "<name>" "<isbn>" "<aisle>" and "<author>"