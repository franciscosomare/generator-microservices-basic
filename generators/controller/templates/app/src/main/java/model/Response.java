package <%= packageName %>.model;

public class <%= entityName %>Response {

    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public <%= entityName %>Response(String message) {
        super();
        this.message = message;
    }

    public <%= entityName %>Response() {
        super();
    }
}