package br.com.splessons.lesson12.exception;

public class ExceptionInfo {

    private final long timestamp;
    private final String message;
    private final String status;
    private final String error;
    private final String exception;
    private final String path;
    private String key;

    private ExceptionInfo(ExceptionInfoBuilder builder) {
        this.timestamp = builder.timestamp;
        this.message = builder.message;
        this.status = builder.status;
        this.error = builder.error;
        this.exception = builder.exception;
        this.path = builder.path;
        this.key = builder.key;
    }

    public String getMessage() {
        return message;
    }

    public String getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getException() {
        return exception;
    }

    public String getPath() {
        return path;
    }

    public String getKey() {
        return key;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public static class ExceptionInfoBuilder {
                
        private long timestamp;
        private String message;
        private String status;
        private String error;
        private String exception;
        private String path;
        private String key;
        
        public ExceptionInfo build() {
            return new ExceptionInfo( this );
        }
        
        public ExceptionInfoBuilder timestamp(long value) { 
            this.timestamp = value;
            return this; 
        }
        public ExceptionInfoBuilder message(String value) { 
            this.message = value; 
            return this; 
        }
        public ExceptionInfoBuilder status(String value) { 
            this.status = value; 
            return this; 
        }
        public ExceptionInfoBuilder error(String value) { 
            this.error = value; 
            return this;
        }
        public ExceptionInfoBuilder exception(String value) { 
            this.exception = value; 
            return this;
        }
        public ExceptionInfoBuilder path(String value) { 
            this.path = value; 
            return this;
        }
        public ExceptionInfoBuilder key(String value) {
            this.key = value;
            return this;
        }
    }

}
