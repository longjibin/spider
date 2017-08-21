package com.nmw.pss.module.login.exception;

import org.apache.shiro.authc.AuthenticationException;

public class AccountDisableException extends AuthenticationException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AccountDisableException() {
        super();
    }

    public AccountDisableException(String message) {
        super(message);
    }

    public AccountDisableException(Throwable cause) {
        super(cause);
    }

    public AccountDisableException(String message, Throwable cause) {
        super(message, cause);
    }
}
