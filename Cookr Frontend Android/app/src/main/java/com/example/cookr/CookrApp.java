package com.example.cookr;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CookrApp extends Application {
    ExecutorService srv  = Executors.newCachedThreadPool();

}
