# WiproNews - MVVM clean Architecture
### by Jason J Calvert

## Android technical assignment for Wipro

This app shows a list of the latest news headlines taken from different websites around the internet.
I have implemented an advance **SearchView** mechanism which allows the user to search any news topic
they desire.

The user will be able to tap on a news item in the **RecyclerView** and view the full news article
which is displayed on a **WebView**. If the user desires to read the article at a later date they
can save the article by tapping on the save **FloatingActionButton** of the save news page.

I have also implemented a **BottomNavigationView** with a save news icon, which allows the user
view a list of saved news articles and view them by tapping on each item list.
The user can then delete these saved news articles by swiping to the left or right, I have also
provided a facility to undo the last action if the user has swiped by mistake.user

There are two types of data sources implemented in this project, a remote data source and a local
data source. I have used **Retrofit** for remote data and **Room** for local data.

**DaggerHilt** was used for **Dependency Injection**.
To display data effectively a **RecyclerView** was used and **Kotlin Coroutines** was used for
background processing.
**Kotlin Coroutines Flow** was used to fetch data from the repository and the sent to the **ViewModel**.

Other jetpack libraries were used such as **ViewModel** **LiveData** **Navigation** **DataBinding**.
**Secret Keys** were also implemented along with **UnitTesting** which was very influential to the
project with the help of **JUnit4** **Truth** **Mockito** and **MockWebServer**.

A powerful **Rest Api** was used call newsapi.org in which i needed an api key











