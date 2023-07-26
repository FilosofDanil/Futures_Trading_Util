package com.example.telegram_api.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResponseParser {
    public static String extractMessage(String response) {
        // Define the regular expression pattern to find the message parameter
        Pattern pattern = Pattern.compile("\"message\":\"([^\"]+)\"");
        Matcher matcher = pattern.matcher(response);

        if (matcher.find()) {
            return matcher.group(1);
        }

        return "Something went wrong on the server!"; // Return null if the message parameter is not found in the response
    }
}
