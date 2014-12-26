package com.example.luhai.daggerdemo;

import android.app.Application;

import java.util.Arrays;
import java.util.List;

import dagger.ObjectGraph;

/**
 * Created by luhai on 2014/12/24.
 */
public class DemoApplication extends Application {
    private ObjectGraph graph;

    @Override public void onCreate() {
        super.onCreate();

        graph = ObjectGraph.create(getModules().toArray());
    }

    protected List<Object> getModules() {
        return Arrays.asList(
                new AndroidModule(this),
                new AppServiceModule(this),
                new DemoModule()
        );
    }

    public void inject(Object object) {
        graph.inject(object);
    }
}
