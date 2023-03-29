package com.mfcms.laba.common.model;

import java.io.Serializable;

import com.mfcms.laba.common.exceptions.UnauthorizedException;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class SecuredHolder<T> implements Serializable {
    String ownerName;
    T content;

    // Required for JAXB
    @SuppressWarnings("unused")
    private SecuredHolder() {
    }
    
    public SecuredHolder(Credentials owner, T content) {
        this.ownerName = owner.getUsername();
        this.content = content;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) throws UnauthorizedException {
        this.content = content;
    }
}
