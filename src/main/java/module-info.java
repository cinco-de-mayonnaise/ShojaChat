module com.abdullah.shojachat {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.validator;
    requires java.logging;


    opens com.abdullah.shojachat to javafx.fxml;
    exports com.abdullah.shojachat;
    exports com.abdullah.shojachat.util;
    opens com.abdullah.shojachat.util to javafx.fxml;
}