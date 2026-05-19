module org.example.mental_therapy_orm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.naming;
    requires org.hibernate.orm.core;
    requires java.persistence;
    requires jbcrypt;
    requires static lombok;
    
    opens org.example.mental_therapy_orm.controller to javafx.fxml;
    opens org.example.mental_therapy_orm.entity to org.hibernate.orm.core;
    opens org.example.mental_therapy_orm.dto to javafx.base;
    
    exports org.example.mental_therapy_orm;
}
