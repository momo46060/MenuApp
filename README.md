# 📱 Restaurant Menu App

An Android application that displays a restaurant menu retrieved from a remote JSON API.  
The app allows users to view, sort, and filter menu items, with full offline support.  
Built using **Kotlin**, **Jetpack Compose**, and **MVVM Clean Architecture**.

---

## ✨ Features

- **View Menu Items** with:
  - Image
  - Name
  - Description
  - Price
  - Rating
  - Tags (Vegan / Hot)
  - Availability

- **Sorting & Filtering**
  - Sort by:
    - Price (Ascending / Descending)
    - Rating
  - Filter by:
    - Vegan / Non-Vegan
    - Hot / Not Hot
    - Availability

- **Offline Support**
  - Caches data locally using **Room**
  - Loads menu even when offline

- **Connectivity Awareness**
  - Detects internet status changes
  - Shows a message when offline

---

## 🛠 Tech Stack

- **Kotlin**
- **Jetpack Compose** - UI
- **MVVM Architecture**
- **Room** - Local database
- **Coroutines & Flow** - Asynchronous programming
- **Hilt** - Dependency Injection
- **Coil** - Image loading
- **ConnectivityManager** - Network monitoring

---

