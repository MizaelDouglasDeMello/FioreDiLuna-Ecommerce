# Fiore di Luna - Ecommerce


![Gravação de Tela 2025-02-08 às 09 23 14](https://github.com/user-attachments/assets/8162a60a-826a-4b52-a7bf-40976fc87421)


## Tech Stack
### Architecture
- **MVVM** (Model-View-ViewModel)
- LiveData
- Lifecycle-aware components
- Repository pattern

### Libraries
- **Retrofit & Gson** - Network requests
- **Picasso** - Image loading
- **Hilt** - Dependency injection
- **Firebase** - Authentication, Cloud Firestore, Storage, Analytics
- **AndroidX** - Core, AppCompat, Fragment, Activity, ConstraintLayout
- **Coroutines** - Asynchronous operations
- **Lifecycle** - ViewModel, LiveData, Lifecycle

### Testing
- **JUnit** - Unit testing
- **Mockito** - Mocking framework
- **Robolectric** - Android environment testing
- **AndroidX Test** - Instrumentation tests

### Tools
- Android Studio
- Gradle
- Kotlin DSL
- Firebase Platform

## Installation
1. Clone the repository
```
git clone [repository-url]
```
2. Open project in Android Studio
3. Build project (Ctrl/Cmd + F9)
4. Run on emulator or physical device (Shift + F10)
Note: Add your google-services.json file for Firebase integration.

### Architecture
```
📦app
┣ 📂data
┃ ┣ 📂remote   # Retrofit services, API interfaces
┃ ┗ 📂local    # Database, SharedPreferences
┣ 📂domain
┃ ┣ 📂models   # Data classes
┃ ┗ 📂repositories # Repository implementations
┗ 📂presentation
  ┣ 📂view     # Activities, Fragments
  ┣ 📂viewmodel # ViewModel classes
  ┗ 📂di       # Dependency Injection (Hilt)
```

