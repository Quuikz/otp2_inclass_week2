package org.otp;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class FuelCalculatorController implements Initializable {

    @FXML
    private Label lblDistance;

    @FXML
    private Label lblConsumption;

    @FXML
    private Label lblPrice;

    @FXML
    private Label lblResult;

    @FXML
    private TextField txtDistance;

    @FXML
    private TextField txtConsumption;

    @FXML
    private TextField txtPrice;

    @FXML
    private Button btnCalculate;

    @FXML
    private Button btnEN;

    @FXML
    private Button btnFR;

    @FXML
    private Button btnJP;

    @FXML
    private Button btnIR;

    private ResourceBundle resourceBundle;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        resourceBundle = resources;
        applyLocalizedTexts();
    }

    @FXML
    private void onCalculate() {
        try {
            double distance = Double.parseDouble(txtDistance.getText().trim());
            double consumption = Double.parseDouble(txtConsumption.getText().trim());
            double price = Double.parseDouble(txtPrice.getText().trim());

            if (distance < 0 || consumption < 0 || price < 0) {
                lblResult.setText(resourceBundle.getString("invalid.input"));
                return;
            }

            double totalFuel = (consumption / 100.0) * distance;
            double totalCost = totalFuel * price;

            NumberFormat numberFormat = NumberFormat.getNumberInstance(resourceBundle.getLocale());
            numberFormat.setMaximumFractionDigits(2);
            numberFormat.setMinimumFractionDigits(2);

            String resultMessage = MessageFormat.format(
                    resourceBundle.getString("result.label"),
                    numberFormat.format(totalFuel),
                    numberFormat.format(totalCost)
            );
            lblResult.setText(resultMessage);
        } catch (NumberFormatException ex) {
            lblResult.setText(resourceBundle.getString("invalid.input"));
        }
    }

    @FXML
    private void onEnglishSelected() {
        setLanguage(Locale.US);
    }

    @FXML
    private void onFrenchSelected() {
        setLanguage(Locale.FRANCE);
    }

    @FXML
    private void onJapaneseSelected() {
        setLanguage(Locale.JAPAN);
    }

    @FXML
    private void onPersianSelected() {
        setLanguage(Locale.of("fa", "IR"));
    }

    private void setLanguage(Locale locale) {
        try {
            resourceBundle = ResourceBundle.getBundle("org.otp.messages", locale);
            applyLocalizedTexts();
        } catch (MissingResourceException ex) {
            lblResult.setText("Language resource missing: " + locale);
        }
    }

    private void applyLocalizedTexts() {
        lblDistance.setText(resourceBundle.getString("distance.label"));
        lblConsumption.setText(resourceBundle.getString("consumption.label"));
        lblPrice.setText(resourceBundle.getString("price.label"));
        btnCalculate.setText(resourceBundle.getString("calculate.button"));

        // Keep language buttons predictable in all locales.
        btnEN.setText("EN");
        btnFR.setText("FR");
        btnJP.setText("JP");
        btnIR.setText("IR");

        if (lblResult.getText() == null || lblResult.getText().isBlank()) {
            lblResult.setText(resourceBundle.getString("result.placeholder"));
        }

        Parent root = lblDistance.getScene() == null ? null : lblDistance.getScene().getRoot();
        if (root != null) {
            // Persian uses RTL orientation; other locales remain LTR.
            if ("fa".equals(resourceBundle.getLocale().getLanguage())) {
                root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
            } else {
                root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
            }
        }
    }
}
