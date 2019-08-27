package com.example.fourtitudetask1.di.component;

import android.app.Application;
import android.content.Context;

import com.example.fourtitudetask1.model.SearchApiResponse;
import com.example.fourtitudetask1.di.InitApplication;
import com.example.fourtitudetask1.di.module.AppModule;
import com.example.fourtitudetask1.di.module.ContextModule;
import com.example.fourtitudetask1.di.module.DataModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class, ContextModule.class})
public interface AppComponent {
    void inject(InitApplication initApplication);

    Context getContext();

    SearchApiResponse getSearchApiResponse();

    Application getApplication();
}