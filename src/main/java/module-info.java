module Demofx {
    requires javafx.controls;
    requires java.datatransfer;
    requires sikulixapi;
    requires javafx.fxml;
    requires json.simple;
    requires com.fasterxml.jackson.databind;
    requires lombok;
    requires javafx.media;
    requires org.slf4j;
    exports org.example.vo;
    opens org.example;
    opens org.example.controller;
//    opens org.example.handler; // <- package 請跟著自己的專案結構改變
}