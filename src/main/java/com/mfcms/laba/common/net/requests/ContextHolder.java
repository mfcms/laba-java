package com.mfcms.laba.common.net.requests;

import java.io.Serializable;

public class ContextHolder implements Serializable {
    private Object context;

    public ContextHolder(Object context) {
        this.context = context;
    }
    
    /**
     * Gets the context associated with this holder if it is assignable from the given type.
     * 
     * @param <T> context type
     * @param type expected context type
     * @return context object
     * @throws IllegalStateException in case when context is null or does not match the expected type
     */
    @SuppressWarnings("unchecked")
    public <T> T getContext(Class<T> type) throws IllegalStateException {
        if(context == null) {
            throw new IllegalStateException("Expected context of type " + type.getSimpleName() + " but found null");
        }
        if(type.isAssignableFrom(context.getClass())) {
            return (T)context;
        } else {
            throw new IllegalStateException("Expected context of type " + type.getSimpleName() + " but found " + context.getClass().getSimpleName());
        }
    }
}
