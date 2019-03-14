package com.example.library.proxy;



import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by lizw on 2019/3/14.
 */
public class ShapeInvocationHandler implements InvocationHandler {
    IShape iShape;
    public ShapeInvocationHandler(IShape iShape){
        this.iShape=iShape;
    }
    public IShape getIshapeProxy(){
        return (IShape)Proxy.newProxyInstance(iShape.getClass().getClassLoader(), iShape.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object o, Method method, Object[] objects) throws Throwable {
        return  method.invoke(iShape,objects);
    }
}
