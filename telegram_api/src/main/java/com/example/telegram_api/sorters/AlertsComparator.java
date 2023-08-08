package com.example.telegram_api.sorters;

import com.example.telegram_api.models.entities.Alerts;
import org.checkerframework.checker.units.qual.A;

import java.util.Comparator;

public class AlertsComparator implements Comparator<Alerts> {
    @Override
    public int compare(Alerts o1, Alerts o2) {
        String str1 = o1.getTicker();
        String str2 = o2.getTicker();
        int minLength = Math.min(str1.length(), str2.length());
        for (int i = 0; i < minLength; i++) {
            char char1 = str1.charAt(i);
            char char2 = str2.charAt(i);
            if (char1 != char2) {
                return Character.compare(char1, char2);
            }
        }

        // If we reach this point, it means all common characters are the same
        // and the strings are either equal up to the minLength or one string is a substring of the other.
        // In this case, the shorter string should come first in the alphabetical order.
        return Integer.compare(str1.length(), str2.length());
    }
}
