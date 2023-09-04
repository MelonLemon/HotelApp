# HotelApp

**HotelApp** - is a test app with multimodule system that uses Jetpack Compose, Dagger Hilt, retrofit2. 

# Screenrecorder, Redmi Xiaomi

https://github.com/MelonLemon/HotelApp/assets/26432711/09022eef-e23b-45e8-93c0-c8baef7d21e7


# Modularization
We have two feature, presentation layer: hotel_info that has 2 screens - Hotel Screen and Room Screen; booking - has Booking Screen and Payment Screen.
We create di's (dagger-hilt)  in :core:data and :hotel_info:domain:, :booking:domain. 

[hotelAppStructure.pdf](https://github.com/MelonLemon/HotelApp/files/12513804/hotelAppStructure.pdf)

![hotelAppStr](https://github.com/MelonLemon/HotelApp/assets/26432711/828af7b4-bc42-4dc7-a36d-f97f6d1a001c)



# Navigation
For navigation, I create two graphs for each feature: the hotel graph and the booking graph. Navigation between screens of the same feature is implemented inside graph. 
Navigation between different features is implemented at a higher level (app level).

# BackEnd
As it's a test app, we use mock data. I used regrofit2 and Gson. I created HotelApiService with three functions.
They are suspended as we do not expect them to change; we fetch them once at the start of activity. While it's loading, it shows a progress bar.
As well, we do not handle errors in use-cases (domain layer) as it's mock data and we are sure that the link is right and the data is in the needed format, and so on.

# Components 
I create common widgets to maintain the same design system and reduce redundant code.
Unfortunately, the app doesn't use the Material Theme 3 theme properly, as the design I used does not apply it.
I create visual transformation functions for phone input and data input.
For mail, I create a launch effect to detect when the user does not write anything in the input. Otherwise, it checks if it's a valid email, and if it's not, it paints it in light red.












