module com.abdullah.shojachat {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.validator;
    requires jdk.compiler;
    requires org.slf4j;
    requires ch.qos.logback.core;


    opens com.abdullah.shojachat to javafx.fxml;
    exports com.abdullah.shojachat;
    exports com.abdullah.shojachat.util;
    opens com.abdullah.shojachat.util to javafx.fxml;
    exports com.abdullah.shojachat.actors;
    opens com.abdullah.shojachat.actors to javafx.fxml;
}