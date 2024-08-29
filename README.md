# EasyPrefs
A lightweight Android library that simplifies working with SharedPreferences,providing type safety, object serialization, and a convenient API for storing and retrieving data.

## Features
* Type-safe getters and setters for primitive data types (String, Int, Boolean, etc.)
* Support for storing and retrieving complex objects using Gson
* Default value handling
* Centralized key management using the `PrefsKeys` enum
* Lazy initialization for simplified usage

## Installation
1. Add JitPack to your root `build.gradle` at the end of repositories:
```
allprojects {
  repositories {
    // ... other repositories
    maven { url 'https://jitpack.io'  }
  }
}
```

2. Add the dependency in app module build.gradle:
```
dependencies {
  implementation 'com.github.abdnezar:EasyPrefsLibrary:1.0.1
}
```

## latest  version on maven: 
[![](https://jitpack.io/v/abdnezar/EasyPrefsLibrary.svg)](https://jitpack.io/#abdnezar/EasyPrefsLibrary)

## Usage
```
// Get a string preference with a default value 
val userName: String = EasyPreferences.getString( PrefsKeys. USER_ NAME. name,  "Guest")

// Set an integer preference 
EasyPrefs.putInt(PrefsKeys. USER_ AGE. name,  30)

// Store a complex object 
val userProfile = UserProfile("John Doe", 30) 
EasyPreferences.putObject( PrefsKeys. USER_ PROFILE. name,  userProfile)

// Retrieve the object 
val retrievedProfile: UserProfile? = EasyPreferences.getObject( PrefsKeys. USER_ PROFILE. name,  UserProfile::class.java) 
```

## License
MIT, Apache 2.0

