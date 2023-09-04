# HotelApp

**HotelApp** - is a test app with multimodule system that uses Jetpack Compose, Dagger Hilt, retrofit2. 

# Screenrecorder, Redmi Xiaomi

https://github.com/MelonLemon/HotelApp/assets/26432711/09022eef-e23b-45e8-93c0-c8baef7d21e7


# Modularization
We have two feature, presentation layer: hotel_info that has 2 screens - Hotel Screen and Room Screen; booking - has Booking Screen and Payment Screen.
We create di's (dagger-hilt)  in :core:data and :hotel_info:domain:, :booking:domain. 

[hotelAppStructure.pdf](https://github.com/MelonLemon/HotelApp/files/12513804/hotelAppStructure.pdf)


# Navigation
For navigation we create two graph for each feature:hotelGraph and bookingGraph. Navigation between screens of the same feature we implement inside graph. 
Navigation between different feature implement in higher level - app level. 

# BackEnd
As it's a test app we use mock data. We are using regrofit2 and Gson. We create HotelApiService with 3 functions. 
They are suspend as we do not expect them to change - we fetch it's once at the start of activity. While it's loading, it shows progress bar. 
As well, we do not make error-handling in use-cases (domain layer) as it's a mock data and we are sure that link is right and data in needed format and so on. 

# Components 
I create coommon widgets to maintain same desing system and reduce writing code. 
Unfortunately, app doesn't use materialtheme 3 theme properly as design I used do not apply it. 
I create VisualTransformation functions for phone input and data input. 
For mail, I create Launcheffect to detect when user do not write anything in the input. Otherwise, it checks is valid email and if it's not it paints in light red. 









