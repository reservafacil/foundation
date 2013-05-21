package com.brazoft.foundation.job.api;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class JobException
    extends RuntimeException {

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    /**
	 * 
	 */
    public JobException() {
	super();
    }

    /**
     * @param message
     * @param cause
     */
    public JobException(String message, Throwable cause) {
	super(message, cause);
    }

    /**
     * @param message
     */
    public JobException(String message) {
	super(message);
    }

    /**
     * @param cause
     */
    public JobException(Throwable cause) {
	super(cause);
    }
}