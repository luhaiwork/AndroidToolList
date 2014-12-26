package com.example.luhai.daggerdemo;

/**
 * Created by luhai on 2014/12/24.
 */
public interface IAppService {
    void showName(ICallback<String> callback);
    String getName();
    public interface ICallback<T>{
        void callBack(T returnObj);
    }
}
