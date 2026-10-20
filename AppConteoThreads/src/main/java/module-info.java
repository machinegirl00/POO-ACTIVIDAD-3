module com.dcgallaga.appconteothreads {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.dcgallaga.appconteothreads to javafx.fxml;
    exports com.dcgallaga.appconteothreads;
}
