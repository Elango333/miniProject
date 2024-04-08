package com.bookingFunctionality;

import java.util.Scanner;
import com.login.Login;
import com.login.Register;
import com.utils.ConstantFile;

public class LoginPage {
  Scanner loginPageSnr = new Scanner(System.in);
  public void loginPage() {
    System.out.println("\n" +
      "╔═══════════════════════════════════╗\n" +
      "║                                   ║\n" +
      "║    Register          - Press (1)  ║\n" +
      "║                                   ║\n" +
      "║    Login             - Press (2)  ║\n" +
      "║                                   ║\n" +
      "║    Back to home page - Press (3)  ║\n" +
      "║                                   ║\n" +
      "╚═══════════════════════════════════╝\n\n");

    int optionForLoginPage = loginPageSnr.nextInt();
    if (optionForLoginPage == ConstantFile.registerUser) {
      Register registerUser = new Register();
      registerUser.register();
    } else if (optionForLoginPage == ConstantFile.loginUser) {
      Login loginUser = new Login();
      loginUser.login();
    } else {
      HomePage homepage = new HomePage();
      homepage.homePage();
    }
    loginPageSnr.close();
  }

}