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
    requires java.desktop;
    requires io.netty.handler;
    requires io.netty.transport;
    requires io.netty.buffer;
    requires io.netty.common;
    exports org.example.vo;
    exports org.example.modelDTO;
    exports org.example.netty.controller;
    exports org.example.http;
    opens org.example;
    opens org.example.controller;
    opens org.example.modeldata to javafx.base;
//    opens org.example.handler; // <- package 請跟著自己的專案結構改變
}