# HotelApp

**HotelApp** - is a test app with multimodule system that uses Jetpack Compose, Dagger Hilt, retrofit2. 

# Screenrecorder, Redmi Xiaomi

https://github.com/MelonLemon/HotelApp/assets/26432711/3962dbdc-8d35-437b-981d-352f1ad47c69



# Components 
I create common widgets to maintain the same design system and reduce redundant code.
Unfortunately, the app doesn't use the Material Theme 3 theme properly, as the design I used does not apply it.
I create visual transformation functions for phone input and data input.


# Modularization
We have two feature, presentation layer: hotel_info that has 2 screens - Hotel Screen and Room Screen; booking - has Booking Screen and Payment Screen.
We create di's (dagger-hilt)  in :core:data and :hotel_info:domain:, :booking:domain. 

![hotelApp_diagram](https://github.com/MelonLemon/HotelApp/assets/26432711/3eb2de10-942e-42ac-87de-058234b126e5)

# Navigation
For navigation, I create two graphs for each feature: the hotel graph and the booking graph(presentation layer). Navigation between screens of the same feature is implemented inside graph. 
Navigation between different features is implemented at a higher level (app layer).

# BackEnd
As it's a test app, we use mock data. I used regrofit2 and Gson. I created HotelApiService with three functions.
They are suspended as we do not expect them to change; we fetch them once at the start of activity. While it's loading, it shows a progress bar.
As well, we do not handle errors in use-cases (domain layer) as it's mock data and we are sure that the link is right and the data is in the needed format, and so on.














