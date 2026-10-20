module com.dcgallaga.liebreytortuga {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dcgallaga.liebreytortuga to javafx.fxml;
    exports com.dcgallaga.liebreytortuga;
}
