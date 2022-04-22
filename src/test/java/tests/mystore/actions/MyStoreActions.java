package tests.mystore.actions;

import models.config.User;
import pages.mystore.auth.LoginPage;
import pages.mystore.auth.RegisterPage;
import pages.mystore.home.HomePage;
import tests.base.Pages;

public class MyStoreActions extends Pages {
    public HomePage registerUser(User user) {
        RegisterPage registerPage = at(HomePage.class).inHeader()
                .goToLogin()
                .goToRegister();

        registerPage.setSocialTitle(user.getSocialTitle())
                .fillFirstName(user.getFirstName())
                .fillLastName(user.getLastName())
                .fillEmail(user.getEmail())
                .fillPassword(user.getPassword())
                .fillBirthDate(user.getBirthDate());

        registerPage.acceptCustomerPrivacy()
                .acceptTOS();

        return registerPage.submit();
    }

    public HomePage loginAs(User user) {
        LoginPage loginPage = at(HomePage.class).inHeader()
                .goToLogin();

        loginPage.fillEmail(user.getEmail())
                .fillPassword(user.getPassword());

        return loginPage.submit();
    }
}
