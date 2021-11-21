# Notify

## Events
### Problem Statement  :
Whenever we try to attend events like interviews, exams, marriage, trips, catching a flight, train. The most issue which people face is "not able to reach on time and missing out"
### Solution :
●  We will get the information regarding the event and notify the user. The information which we will get includes starting point, destination point, name of event, description, date, and time of the event.

●  Through the above information, we will notify the user which will help the user to reach the destination on time.

●  We will handle the traffic-related issue and calculate the time required to reach the destination. Which will solve the problem of "not able to reach on time and missing out"

## Tasks
### Problem Statement : 
People use task manager and used to note down the task which they want to complete. The problem is, very often people forgot to remember what task they have to do now.
### Solution : 
● Very often, people used to forget the task, which results in "not completing the task on time."
● We will help to complete the task by notifying the user based on the user's requirements.

## Goals
### Problem Statement :
Not having the relationship between task and goal which they want to achieve. People often do task which is not related to achieving the goals.
### Solution :
● We will map the task to the goal which will help the user to achieve the goals and be more productive.
● We will show the progress bar, status and predict whether you will be able to achieve your goals on time if you complete the task, the way you are doing it.

# Links

# 🔗Open-Source Library

* [Glide](https://github.com/bumptech/glide)
* [Firebase](https://firebase.google.com/docs/auth)
* [Google Map](https://developers.google.com/maps)
* [Jetpack](https://developer.android.com/jetpack)
* [Circular ImageView](https://github.com/hdodenhof/CircleImageView)
* [EazeGraph](https://github.com/blackfizz/EazeGraph)

# Things we used while making this application

* GitHub
* Firebase
* Recycler View
* Retrofit Library
* API
* Gif ImageView
* VideoView
* Fragments
* Retrofit Library
* Autoslider and Loti Animation
* Eaze Graph
* Google Maps Place API

# Tech Stack ✨

* [Android Studio](https://developer.android.com/studio)
* [Kotlin](https://kotlinlang.org/)

# Clone this Repo To Your System Using Android Studio✨

* Step 1: Open your Android Studio then go to the File > New > Project from Version Control as shown in the below image.
* Step 2: After clicking on the Project from Version Control a pop-up screen will arise like below. In the Version control choose Git from the drop-down menu.
* Step 3: Then at last paste the link in the URL and choose your Directory. Click on the Clone button and you are done.

# Clone this Repo To Your System Using GitBash✨

* Open Git Bash

* If Git is not already installed, it is super simple. Just go to the Git Download Folder and follow the instructions.

* Go to the current directory where you want the cloned directory to be added.

* To do this, input cd and add your folder location. You can add the folder location by dragging the folder to Git bash.

* Go to the page of the repository that you want to clone

* Click on “Clone or download” and copy the URL.

* Use the git clone command along with the copied URL from earlier. $ git clone https://github.com/Kingbond470/Notify

* Press Enter. $ git clone https://github.com/Kingbond470/Notify

Congratulations, you have created your local clone from your remote Github repository.

Open Android Studio. Go to File > New > Project From Version Control. Copy the link of this repositary. Paste the link in Url Box of Android Studio window and click on "Clone" button.

# Let's See
 
 <img src="https://github.com/Kingbond470/Android-Practice/blob/main/Notify%20Assest/home.png">
 
 ## Event
 
 <img src="https://github.com/Kingbond470/Android-Practice/blob/main/Notify%20Assest/event.png">
 
 ## Task
 
 <img src="https://github.com/Kingbond470/Android-Practice/blob/main/Notify%20Assest/task.png">
 
 ## Goal
 
 <img src="https://github.com/Kingbond470/Android-Practice/blob/main/Notify%20Assest/goal.png">
 
 ## Settings
 
 <img src="https://github.com/Kingbond470/Android-Practice/blob/main/Notify%20Assest/settings.png">
 
 ## Feature of Custom Calendar
 
 <img src="https://github.com/Kingbond470/Android-Practice/blob/main/Notify%20Assest/calendar.png">



# Dependencies 

    // Material UI
    implementation 'com.google.android.material:material:1.5.0-alpha03'


    // Firebase Google Authentication
    implementation 'com.google.firebase:firebase-auth-ktx:21.0.1'

    // Firestore
    implementation 'com.google.firebase:firebase-firestore-ktx:21.5.0'
    implementation 'com.firebaseui:firebase-ui-firestore:6.3.0'
    implementation 'com.google.firebase:firebase-storage:20.0.0'

    // Import the BoM for the Firebase platform
    implementation platform('com.google.firebase:firebase-bom:29.0.0')
    implementation 'com.google.firebase:firebase-database-ktx'

    // Also declare the dependency for the Google Play services library and specify its version
    implementation 'com.google.android.gms:play-services-auth:19.2.0'

    //Navigation component
    def nav_version = "2.3.5"
    implementation "androidx.navigation:navigation-fragment-ktx:$nav_version"
    implementation "androidx.navigation:navigation-ui-ktx:$nav_version"

    // Retrofit dependency which will be used to make network calls
    implementation 'com.squareup.retrofit2:retrofit:2.9.0'

    // GSON library which is used to convert POJO to JSON and vice versa
    implementation 'com.squareup.retrofit2:converter-gson:2.9.0'

    // okhttp library used to observe the api call logs on LogCat
    implementation 'com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2'

    //Glide
    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

    //Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.2-native-mt")
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"

    // Coroutine Lifecycle Scopes
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:2.3.1"

    // ViewModel and LiveData
    def arch_version = '2.2.0-alpha01'
    implementation "androidx.lifecycle:lifecycle-extensions:$arch_version"
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:$arch_version"
    implementation "androidx.lifecycle:lifecycle-runtime-ktx:$arch_version"

    // Architectural Components
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"

    // Kotlin Extensions and Coroutines support for Room
    implementation "androidx.room:room-ktx:2.3.0"

    // Room
    implementation "androidx.room:room-runtime:2.3.0"
    kapt "androidx.room:room-compiler:2.3.0"

    //Dagger - Hilt
    implementation "com.google.dagger:hilt-android:2.28-alpha"
    kapt "com.google.dagger:hilt-android-compiler:2.28-alpha"
    implementation "androidx.hilt:hilt-lifecycle-viewmodel:1.0.0-alpha02"
    kapt "androidx.hilt:hilt-compiler:1.0.0-alpha02"

    //lottie
    implementation 'com.airbnb.android:lottie:3.2.0'

    // circular imageview
    implementation 'de.hdodenhof:circleimageview:3.1.0'

    //animation
    implementation 'pl.droidsonroids.gif:android-gif-drawable:1.2.22'

    //about
    implementation 'io.github.medyo:android-about-page:2.0.0'

    //google maps
    implementation 'com.google.android.gms:play-services-maps:18.0.0'
    implementation 'com.google.android.gms:play-services-location:18.0.0'

    //chart library
    implementation 'com.github.lecho:hellocharts-library:1.5.8@aar'

    //implementation 'com.github.AnyChart:AnyChart-Android:1.1.2'

    // For Card view
    implementation 'androidx.cardview:cardview:1.0.0'

    // Chart and graph library
    implementation 'com.github.blackfizz:eazegraph:1.2.5l@aar'
    implementation 'com.nineoldandroids:library:2.4.0'
    
## [Contributors 🌸💯👏](#Contributors)

### Developed by

- [Abhimanyu Mishra](https://github.com/abhimanyumishra130)
- [Murali Krishna Sundara](https://github.com/MuraliKrishnaSundara)
- [Mausam Singh](https://github.com/Kingbond470)

    
# Lessons Learnt📚
We built the project in two days during Masai Hackathon. #3 Rank

### PS
Hard work beats the talent, when talent doesn't do the hardwork.
