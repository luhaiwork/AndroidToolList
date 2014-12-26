package com.example.luhai.daggerdemo;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by luhai on 2014/12/24.
 */
@Module(library = true)
public class AppServiceModule {
    private Context _context;
    AppServiceModule(Application context){
        _context=context;
    }
    @Provides
    @Singleton
    IAppService provideSystemService() {
        return new AppServiceImpl1(_context);
    }
}
