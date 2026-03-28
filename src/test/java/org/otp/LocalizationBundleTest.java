package org.otp;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertTrue;

class LocalizationBundleTest {

    private static final List<String> REQUIRED_KEYS = List.of(
            "app.title",
            "language.label",
            "distance.label",
            "consumption.label",
            "price.label",
            "distance.prompt",
            "consumption.prompt",
            "price.prompt",
            "calculate.button",
            "result.placeholder",
            "result.label",
            "invalid.input"
    );

    @Test
    void allRequiredKeysExistInAllSupportedLocales() {
        List<Locale> locales = List.of(
                Locale.US,
                Locale.FRANCE,
                Locale.JAPAN,
                Locale.of("fa", "IR")
        );

        for (Locale locale : locales) {
            ResourceBundle bundle = ResourceBundle.getBundle("org.otp.messages", locale);
            for (String key : REQUIRED_KEYS) {
                assertTrue(bundle.containsKey(key), "Missing key '" + key + "' in locale " + locale);
            }
        }
    }
}
