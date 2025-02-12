package com.example.booksyne.utility;

import com.example.booksyne.service.FavouriteManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
@RequiredArgsConstructor
public class ReflectionServiceInvoker {
    private final FavouriteManagerService favouriteManagerService;

    public void invokeService(String serviceName, String methodName, Class<?>[] parameterTypes, Object[] parameters) {
        try {
            Class<?> clazz = FavouriteManagerService.class;
            Method method = clazz.getMethod(methodName, parameterTypes);
            method.invoke(favouriteManagerService, parameters);
        } catch (Exception e) {
            throw new RuntimeException("Failed to invoke method: " + methodName + " on service: " + serviceName, e);
        }
    }
}