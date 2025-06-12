package com.idrx.qa.util;

import java.io.FileInputStream;
import java.util.Collections;
import java.util.List;
import com.google.api.services.sheets.v4.*;
import com.google.api.services.sheets.v4.model.*;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.auth.http.HttpCredentialsAdapter;
import com.google.auth.oauth2.GoogleCredentials;

public class GoogleSheetUtil {
    private static final String APPLICATION_NAME = "Google Sheets Java Integration";
    private static final String SPREADSHEET_ID = "1KGheb3Is9Qf-LM463FAqihG5M6NDCYNYVVIlBlUCLbU"; // URL
    private static final String RANGE = "Sheet1!A1:B100"; // Adjust as needed

    public static String getReplacementText(String keyText) {
        try {
            var httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            var jsonFactory = GsonFactory.getDefaultInstance();

            GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream("credentials.json"))
                    .createScoped(Collections.singleton("https://www.googleapis.com/auth/spreadsheets.readonly"));

            Sheets sheetsService = new Sheets.Builder(httpTransport, jsonFactory,
                    new HttpCredentialsAdapter(credentials))
                    .setApplicationName(APPLICATION_NAME)
                    .build();

            ValueRange response = sheetsService.spreadsheets().values()
                    .get(SPREADSHEET_ID, RANGE)
                    .execute();

            List<List<Object>> values = response.getValues();
            if (values == null || values.isEmpty()) {
                throw new Exception("No data found in Google Sheet.");
            }

            for (List<Object> row : values) {
                if (row.size() >= 2 && row.get(0).toString().equalsIgnoreCase(keyText)) {
                    return row.get(1).toString();
                }
            }

            throw new Exception("Key text not found: " + keyText);
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch from Google Sheet", e);
        }

    }
}
