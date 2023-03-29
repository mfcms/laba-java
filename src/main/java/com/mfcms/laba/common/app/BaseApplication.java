package com.mfcms.laba.common.app;

import java.util.function.Consumer;

import com.mfcms.laba.common.services.ServiceCollection;
import com.mfcms.laba.common.services.ServiceCollection.ServiceScope;

public abstract class BaseApplication {
    protected ServiceScope serviceProvider;

    protected abstract void configure(ServiceCollection services);

    public void initialize() {
        initialize(null, null);
    }

    public void initialize(Consumer<ServiceCollection> configureServices) {
        initialize(configureServices, null);
    }

    public void initialize(Consumer<ServiceCollection> configureServices,
            Consumer<ServiceScope> configureScope) {

        ServiceCollection serviceCollection = new ServiceCollection();
        
        configure(serviceCollection);
        
        if (configureServices != null) {
            configureServices.accept(serviceCollection);
        }

        serviceProvider = serviceCollection.build();
        
        if (configureScope != null) {
            configureScope.accept(serviceProvider);
        }
    }

    public abstract void run();
}