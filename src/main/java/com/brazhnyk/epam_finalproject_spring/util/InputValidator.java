package com.brazhnyk.epam_finalproject_spring.util;

import com.brazhnyk.epam_finalproject_spring.entity.Genre;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class InputValidator {

    private static final Logger logger = LogManager.getLogger(InputValidator.class);

    private InputValidator() {}

    /**
     * Validate user login form
     * @param login - User login
     * @param password - User password
     * @return result of validation
     */
    public static boolean validateLoginPassword(String login, String password) {
        return validateLogin(login) && validatePassword(password);
    }

    /**
     * Validate user registration form
     * @param login - User login
     * @param email - User email
     * @param password - User password
     * @param passwordConfirm - password confirmation
     * @return result of validation
     */
    public static boolean validateRegistrationForm(String login,
                                                   String email,
                                                   String password,
                                                   String passwordConfirm) {
        return validateLogin(login)
                && validatePassword(password)
                && validateEmail(email)
                && validateConfirmPassword(password, passwordConfirm);
    }

    /**
     * Validate input fields for edition
     * @param titleEn - English title
     * @param titleUa - Ukrainian title
     * @param textEn - English text
     * @param textUa - Ukrainian text
     * @param price - price
     * @param genre - genre
     * @return result of validation
     */
    public static boolean validateNewEdition(String titleEn,
                                             String titleUa,
                                             String textEn,
                                             String textUa,
                                             String price,
                                             Genre genre) {
        return validateTitleEn(titleEn)
                && validateTitleUa(titleUa)
                && validateText(textEn)
                && validateText(textUa)
                && validateMoney(price)
                && validateGenre(genre);
    }

    /**
     * Validate input to be a positive number
     * @param money - money input
     * @return validation result
     */
    public static boolean validateMoney(String money) {
        String moneyPattern = "^[1-9][\\d]*$";
        return money != null && money.matches(moneyPattern);
    }

    /**
     * Validate input to be a positive number
     * @param genre - genre id input
     * @return validation result
     */
    public static boolean validateGenre(Genre genre) {
        return genre != null;

    }

    /**
     * Validate id provided value is a digit.
     * @param number - value for validation
     * @return TRUE - if value is a digit, FALSE - if not or null;
     */
    public static boolean validateNumberValue(String number) {
        if (number == null) {
            return false;
        }

        try {
            Integer.parseInt(number);
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * Validate title_en input
     * @param titleEn - English title
     * @return validation result
     */
    private static boolean validateTitleEn(String titleEn) {
        String titleEnPattern = "^[A-Za-z0-9_ -|&?:]{2,50}$";
        return titleEn != null && titleEn.matches(titleEnPattern);
    }

    /**
     * Validate title_ua input
     * @param titleUa - English title
     * @return validation result
     */
    private static boolean validateTitleUa(String titleUa) {
        String titleUaPattern = "^[a-zA-Zа-яА-ЯіІйЙёЁ0-9 -]{2,50}$";
        return titleUa != null && titleUa.matches(titleUaPattern);
    }

    /**
     * Validate text input
     * @param text - text input
     * @return validation result
     */
    private static boolean validateText(String text) {
        return text != null && text.length() >= 2;
    }

    /**
     * Validate login input
     * @param login - user login
     * @return validation result
     */
    private static boolean validateLogin(String login) {
        return login != null && login.length() > 4;
    }

    /**
     * Validate password input
     * @param password - user password
     * @return validation result
     */
    private static boolean validatePassword(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d\\w\\W]{8,}$";
        return password != null && password.length() >= 8 && password.matches(passwordPattern);
    }

    /**
     * Compare password with password confirmation
     * @param password - user password
     * @param confirmPassword - password confirmation
     * @return validation result
     */
    private static boolean validateConfirmPassword(String password, String confirmPassword) {
        return validatePassword(password)
                && validatePassword(confirmPassword)
                && password.equals(confirmPassword);
    }

    /**
     * Validate email input
     * @param email - user email
     * @return validation result
     */
    private static boolean validateEmail(String email) {
        String emailPattern = "^[^ ]+@[^ ]+\\.[a-z]{2,4}$";
        return email != null && email.matches(emailPattern);
    }
}
