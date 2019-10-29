package com.test.webApp.superWebApp.service;

import com.test.webApp.superWebApp.dao.MyDao;


public interface MyService<T> {
    MyDao<T> getMainDao();
}
