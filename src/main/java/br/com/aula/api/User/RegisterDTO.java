package br.com.aula.api.User;


public record RegisterDTO(String login, String password, UserRole role) {

}
