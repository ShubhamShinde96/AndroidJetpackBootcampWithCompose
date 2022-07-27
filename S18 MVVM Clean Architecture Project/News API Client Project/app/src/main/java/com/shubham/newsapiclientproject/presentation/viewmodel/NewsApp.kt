package com.shubham.newsapiclientproject.presentation.viewmodel

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

// All apps that uses Hilt must contain an application class that is annotated with @HiltAndroidApp
// This annotation triggers Hilt code generation.

@HiltAndroidApp
class NewsApp: Application() // This class does not need to have body, but we'll need to add this class to the manifest