module com.mkrzyzowski.mynotepad {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens com.mkrzyzowski.mynotepad to javafx.fxml;
    exports com.mkrzyzowski.mynotepad;
}