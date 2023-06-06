package com.clinic.helpers;

public class CaesarCipher {

  public static String encrypt(String text, int shift) {
    StringBuilder result = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      char ch = text.charAt(i);
      if (Character.isUpperCase(ch)) {
        char c = (char) (((int) ch + shift - 65) % 26 + 65);
        result.append(c);
      } else {
        char c = (char) (((int) ch + shift - 97) % 26 + 97);
        result.append(c);
      }
    }
    return result.toString();
  }

  public static String decrypt(String text, int shift) {
    return encrypt(text, 26 - shift);
  }
}

