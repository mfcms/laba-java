package com.mfcms.laba.common;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ServiceCollection {
    private HashMap<Class<?>, Function<ServiceProvider, ?>> custom_services = new HashMap<>();

    public <T> void add(Class<T> service) {
        add(service, sp -> sp.construct(service));
    }

    public <T, R extends T> void add(Class<T> service, Class<R> serviceImpl) {
        add(service, sp -> sp.construct(serviceImpl));
    }

    public <T> void add(Class<T> service, Function<ServiceProvider, T> customCtor) {
        custom_services.put(service, customCtor);
    }

    public static class ServiceProvider {
        private HashMap<Class<?>, Function<ServiceProvider, ?>> custom_services;
        private HashMap<Class<?>, Object> built_services = new HashMap<>();
        private HashMap<Class<?>, Class<?>> is_building = new HashMap<>();
        private Class<?> prev_get = null;

        private <T> T construct(Class<T> serviceType) {
            var constl = (Constructor<T>) serviceType.getConstructors()[0];
            Object[] ctorParameters = Stream.of(constl.getParameterTypes())
                    .map(requirement -> get(requirement)).toArray();
            try {
                return constl.newInstance(ctorParameters);
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            } catch (InvocationTargetException e) {
                e.printStackTrace();
                throw new IllegalStateException(e);
            }
        }

        private ServiceProvider(HashMap<Class<?>, Function<ServiceProvider, ?>> custom_services) {
            this.custom_services = custom_services;
        }

        private String getDependencyStack() {
                ArrayList<Class<?>> classes = new ArrayList<>();
                classes.add(prev_get);
            for (var s = is_building.get(prev_get);
                    s != null && s != prev_get;
                    s = is_building.get(s)) {
                classes.add(s);
            }
            return classes.stream()
                    .map(s -> s.getSimpleName())
                    .collect(Collectors.joining(" <- "));
        }

        public <T> T get(Class<T> service) {
            if (built_services.containsKey(service)) {
                return (T) built_services.get(service);
            }

            if (is_building == null) {
                throw new IllegalStateException();
            }

            if (is_building.containsKey(service)) {
                throw new IllegalStateException("Circular service dependency detected: " + getDependencyStack());
            }
            
            if (!custom_services.containsKey(service)) {
                throw new IllegalStateException("No such service registered: " + getDependencyStack());
            }

            Class<?> prev = prev_get;
            is_building.put(service, prev_get);
            prev_get = service;

            T result = (T) custom_services.get(service).apply(this);

            is_building.remove(service);
            prev_get = prev;

            built_services.put(service, result);
            return result;
        }
    }

    public ServiceProvider build() {
        ServiceProvider provider = new ServiceProvider(custom_services);
        for (Class<?> service : custom_services.keySet()) {
            provider.get(service);
        }
        provider.is_building = null;
        return provider;
    }
}
