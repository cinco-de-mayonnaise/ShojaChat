module com.abdullah.shojachat {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.abdullah.shojachat to javafx.fxml;
    exports com.abdullah.shojachat;
}