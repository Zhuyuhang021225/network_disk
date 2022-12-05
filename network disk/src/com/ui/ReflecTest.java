package com.ui;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflecTest {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        /*获取Field */
//        Class<LoginForm> loginFormClass = LoginForm.class;
//        Field[] declaredFields = loginFormClass.getDeclaredFields();
//        for (int i=0;i<declaredFields.length;i++){
//            System.out.println(declaredFields[i]);
//        }

        /*获取Methods*/
        Class<HomePage> clientFormClass = HomePage.class;
        Method[] declaredMethods = clientFormClass.getDeclaredMethods();
        for (int i=0;i<declaredMethods.length;i++){
            System.out.println(declaredMethods[i]);
        }

        /*获取Constractor并创建对象*/
//        Class<LoginForm> loginFormClass = LoginForm.class;
//        Constructor<LoginForm> declaredConstructor = loginFormClass.getDeclaredConstructor();
//        LoginForm loginForm = declaredConstructor.newInstance();

        /*Class对象创建无参构造*/
//        Class<LoginForm> loginFormClass = LoginForm.class;
//        LoginForm loginForm = loginFormClass.newInstance();
    }
}

