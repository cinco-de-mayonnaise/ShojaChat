module com.abdullah.shojachat {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.validator;
    requires jdk.compiler;
    requires org.slf4j;
    requires ch.qos.logback.core;
    requires ch.qos.logback.classic;


    //opens com.abdullah.shojachat to javafx.fxml;
    exports com.abdullah.shojachat;
    //exports com.abdullah.shojachat.util;
    //opens com.abdullah.shojachat.util to javafx.fxml;
    //exports com.abdullah.shojachat.actors;
    //opens com.abdullah.shojachat.actors to javafx.fxml;
    opens com.abdullah.shojachat.ui.controller to javafx.fxml;   // omg you bastardo.... turns out this was needed to make the controllers accessible to the fxml!

}