package com.mfcms.laba.common.services;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * A class implementing the Dependency Container pattern that automatically
 * resolves dependencies between service types.
 */
public class ServiceCollection {
    private HashMap<Class<?>, Function<ServiceScope, ?>> service_factories = new HashMap<>();

    /**
     * Adds the type T as a service into this collection.
     * 
     * The first public constructor of the type T will be used to instantiate it,
     * its parameters filled from other service type instances.
     * 
     * @param <T>     service type
     * @param service Class object of the service type
     */
    public <T> void add(Class<T> service) {
        add(service, sp -> sp.construct(service));
    }

    /**
     * Adds the type R as an implementation of the service type T into this
     * collection.
     * 
     * The first public constructor of the type R will be used to instantiate it,
     * its parameters filled from other service type instances.
     * 
     * @param <T>         service type T
     * @param <R>         implementation type R
     * @param service     Class object of the service type
     * @param serviceImpl Class object of the implementation type
     */
    public <T, R extends T> void add(Class<T> service, Class<R> serviceImpl) {
        add(service, sp -> sp.construct(serviceImpl));
    }

    /**
     * Adds the type T as a service into this collection.
     * 
     * The provided function will be used to instantiate the service type, and the
     * ServiceProvider parameter can be used to get instance of other service types.
     * 
     * @param <T>     service type
     * @param service Class object of the service type
     */
    public <T> void add(Class<T> service, Function<ServiceScope, T> customCtor) {
        service_factories.put(service, customCtor);
    }

    public static class ServiceScope {
        private ServiceScope parent_scope;
        private HashMap<Class<?>, Function<ServiceScope, ?>> service_factories;
        private HashMap<Class<?>, Object> service_cache = new HashMap<>();
        private HashMap<Class<?>, Class<?>> dependency_stack = new HashMap<>();
        private Class<?> previous_get = null;



        /**
         * Instantiates the given type using the first public constructor, with
         * parameters filled in by instances of other service types.
         * 
         * @param <T>         a type
         * @param serviceType Class object of the type
         * @return an instance of the given type
         */
        @SuppressWarnings("unchecked")
        private <T> T construct(Class<T> serviceType) {
            var t = (Constructor<T>) serviceType.getConstructors()[0];
            Object[] ctorParameters = Stream.of(t.getParameterTypes())
                    .map(requirement -> get(requirement)).toArray();
            try {
                return t.newInstance(ctorParameters);
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

        private ServiceScope(HashMap<Class<?>, Function<ServiceScope, ?>> service_factories) {
            this.service_factories = service_factories;
            this.service_cache.put(ServiceScope.class, this);
        }

        private String getDependencyStack(Class<?> initial) {
            ArrayList<Class<?>> classes = new ArrayList<>();
            if (initial != null) {
                classes.add(initial);
            }
            classes.add(previous_get);
            for (var s = dependency_stack.get(previous_get); s != null
                    && s != previous_get; s = dependency_stack.get(s)) {
                classes.add(s);
            }
            return classes.stream()
                    .map(s -> s.getSimpleName())
                    .collect(Collectors.joining(" <- "));
        }

        /**
         * Retrieves an instance of the service type from the collection.
         * 
         * @param <T>     a service type
         * @param service Class object of the service type
         * @return an instance of the service type
         */
        @SuppressWarnings("unchecked")
        public <T> T get(Class<T> service) {
            if (service_cache.containsKey(service)) {
                return (T) service_cache.get(service);
            }

            if(parent_scope != null) {
                return parent_scope.get(service);
            }

            if (service_factories == null) {
                throw new IllegalStateException("No such service registered: " + service.getSimpleName());
            }

            if (!service_factories.containsKey(service)) {
                throw new IllegalStateException("No such service registered: " + getDependencyStack(service));
            }

            if (dependency_stack.containsKey(service)) {
                throw new IllegalStateException("Recursive service dependency detected: " + getDependencyStack(service));
            }

            Class<?> prev = previous_get;
            dependency_stack.put(service, previous_get);
            previous_get = service;

            T result = (T) service_factories.get(service).apply(this);

            dependency_stack.remove(service);
            previous_get = prev;

            service_cache.put(service, result);
            return result;
        }

        private ServiceScope(ServiceScope parent_scope) {
            this.parent_scope = parent_scope;
            this.service_cache.put(ServiceScope.class, this);
        }

        public ServiceScope createChildScope() {
            return new ServiceScope(this);
        }

        public <T> void override(Class<T> service, T value) {
            service_cache.put(service, value);
        }

        private void finish() {
            service_factories = null;
            dependency_stack = null;
        }
    }

    /**
     * Builds the service provider with all registered services
     * @return ServiceProvider
     */
    public ServiceScope build() {
        ServiceScope provider = new ServiceScope(service_factories);
        for (Class<?> service : service_factories.keySet()) {
            provider.get(service);
        }
        provider.finish();
        return provider;
    }
}
