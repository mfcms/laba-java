package com.mfcms.laba.common.model;

import java.io.Serializable;

import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAccessType;

@XmlAccessorType(XmlAccessType.FIELD)
public class Credentials implements Serializable {
    String username;
    String passwordMd2;

    // Required for JAXB
    @SuppressWarnings("unused")
    private Credentials() {
    }
    
    public Credentials(String username, String passwordMd2) {
        this.username = username;
        this.passwordMd2 = passwordMd2;
    }

    public String getUsername() {
        return username;
    }

    public String getPasswordMd2() {
        return passwordMd2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || !(o instanceof Credentials creds))
            return false;
        return username.equals(creds.username) && passwordMd2.equals(creds.passwordMd2);
    }

}