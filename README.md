PROJECT TITLE: RAILWAY RESERVATION PORTAL

PURPOSE OF PROJECT:TO CREATE A PORTAL TO BOOK OR CANCEL TICKET OR CHECK PNR STATUS. THE CONCEPTS OF WAITLISTS AND RAC IS ALSO IMPLEMENTED.

HOW TO START THIS PROJECT: I HAVE TYPED THIS PROJECT IN BLUEJ 3.1.7 WITH JDK VERSION 1.8 in UBUNTU 16.08.KEEP list.txt file IN THE WORKING DIRECTORY. TO EXECUTE THE CODE, JUST OPEN THE PROJECT AND EXECUTE THE main() method OF THE Portal class. (Right click on the Portal class and execute void main()).
(Shown in main screen.png and terminal.png)

USER INSTRUCTIONS:

TO CREATE A NEW ACCOUNT- ENTER CHOICE 1
ENTER YOUR NAME AND ATM CARD NUMBER AND CONFIRM.
(shown in createNewAccount.png)

TO PERFORM ANOTHER OPERATION PRESS Y.
TO LOGIN ENTER CHOICE 2 AND THEN ENTER YOUR ACCOUNT NUMBER AND ATM CARD NUMBER TO CONFIRM LOGIN.
(shown in loginPage.png)

AFTER LOGGING IN YOU CAN BOOK OR CANCEL TICKETS AND ALSO CHECK PNR.
TO BOOK TICKET PRESS 1, ENTER THE DETAILS AND CONFIRM BOOKING. THE FOLLOWING OUTPUT IS OBTAINED.
(shown in BookTicket.png)

TO CHECK PNR STATUS: PRESS Y AND THEN 3.
ENTER YOUR PNR NUMBER AND THE DETAILS APPEAR.
(shown in checkPNR.png)

TO PERFORM ANOTHER OPERATION:PRESS Y.
TO CANCEL TICKET PRESS 2.
ENTER THE PNR NUMBER. THE TICKET DETAILS ARE SHOWN. TO CONFIRM CANCELLATION PRESS Y.
(shown in CancelTicket.png)

TO LOG OUT EITHER PRESS N OR PRESS Y THEN 4.
TO EXIT PORTAL PRESS N WHEN ASKED FOR ANOTHER OPERATION AFTER LOGGING OUT OR PRESS Y THEN 4.
(shown in exit.png)



--------MORE INFORMATION---------

The confirmed seats are limited to 50 and RAC to 20 and number of Coaches to 1 in order to show the implementation of waitList easier. It can be increased to as many we want with just few changes in the code to making all 50 to maxseats and 20 to maxRacs and assigning values to both constants. And the number of coaches assigned can be calculated by using divide and modulus function to the seat number allotted appropriately.

The implementation of waitlist is shown in Screenshot(1-8).png where cancellation of tickets moves the waitlist to confirm,RAC and waitlist above.


