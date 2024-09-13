package org.santana.controller.helpers;

public class StringHelper {

/**
 * Remove the last character on String.
 *
 * @param param1 String to remove the last charater.
 * @return New String without the original`s last character.
 */
    public static String trimL(String string) {

        String trimmed = null;

        if (string != null && string.length() > 0) {
            trimmed = string.substring(0, string.length() - 1);
        }

        return trimmed;
    }
}
