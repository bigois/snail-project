package br.com.fiap.appglasseek.service;

public interface Service {
    String IP_ADDRESS = "192.168.1.139";
    String PORT = "6085";
    String PATH = "rest";
    String URL = "http://" + IP_ADDRESS + ":" + PORT + "/" + PATH;
    Boolean success = false;

}
