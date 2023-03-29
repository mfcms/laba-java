package com.mfcms.laba.common.net.requests;

public class CommandResponse extends ContextHolder {
    private NetStatus status;
    private String message;

    public CommandResponse(NetStatus status) {
        this(status, null, null);
    }

    public CommandResponse(NetStatus status, String message) {
        this(status, message, null);
    }

    public CommandResponse(NetStatus status, String message, Object context) {
        super(context);
        this.status = status;
        this.message = message;
    }

    public NetStatus getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
