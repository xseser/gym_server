package com.gym.user.registration.controller.request.valid;

public class ValidUserLoginDto {
    private String mail;
    private String password;

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ValidUserLoginDto{" +
               "mail='" + mail + '\'' +
               ", password='" + "*******" + '\'' +
               '}';
    }
}
