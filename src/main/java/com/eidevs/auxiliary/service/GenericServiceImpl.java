/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.eidevs.auxiliary.service;

import  com.eidevs.auxiliary.payload.TextValueDto;
import java.util.List;
import  com.eidevs.auxiliary.repository.GenericRepository;
import com.google.gson.Gson;
import java.math.RoundingMode;
import java.security.MessageDigest;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author bokon
 */
@Service
public class GenericServiceImpl implements GenericService {

    @Autowired
    GenericRepository genericRepository;
    @Autowired
    MessageSource messageSource;
    @Autowired
    Environment env;
    private static String AUTHORIZATION = "";
    private static String NONCE = "";
    private static String SIGNATURE = "";
    private static String TIMESTAMP = "";
    private static final SimpleDateFormat TIMESTAMP_FORMAT = new SimpleDateFormat("yyyyMMddHHmmss");
    
    private static String BASE_URL = "";
    private static String USER_SECRET_KEY = "";
    private static String USERNAME = "";
    private static String PASSWORD = "";
    private static String SIGNATURE_METHOD = "";
    private static final String ACCEPT = "application/json";
    private static String MIDDLEWARE_HOST = "";
    private static String MIDDLEWARE_PORT = "";
    private String currentBranchCode = "";
    public String overrideMessage = "";
    private static final String IS_BASE_URL = "http://127.0.0.1:8080/accion/api/service";

    Gson gson;
    RestTemplate restTemplate;

    @Override
    public String endPointPostRequest(String url, String requestJson) {
        AUTHORIZATION = "Basic " + Base64.getEncoder().encodeToString((env.getProperty("accion.authorization.username") + ":" + env.getProperty("accion.authorization.password")).getBytes());
        NONCE = String.valueOf(Math.random());
        TIMESTAMP = TIMESTAMP_FORMAT.format(new Date());
        String SignaturePlain = String.format("%s:%s:%s:%s", NONCE, TIMESTAMP, env.getProperty("accion.authorization.username"), env.getProperty("accion.authorization.secret.key"));
        SIGNATURE = hash(SignaturePlain, env.getProperty("accion.authorization.signature.method"));
        String responseString = "";
        Client client = ClientBuilder.newClient();
        Response response = client.target(env.getProperty("accion.middleware.base.url") + url)
                .request()
                .header("Authorization", AUTHORIZATION)
                .header("SignatureMethod", env.getProperty("accion.authorization.signature.method"))
                .header("Accept", "application/json")
                .header("Timestamp", TIMESTAMP)
                .header("Nonce", NONCE)
                .header("Signature", SIGNATURE)
                .header("ContentType", "application/json")
                .post(Entity.json(requestJson));
        responseString = response.readEntity(String.class);
        response.close();
        client.close();
        return responseString;
    }

    @Override
    public String endPointGetRequest(String url) {
        AUTHORIZATION = "Basic " + Base64.getEncoder().encodeToString((env.getProperty("accion.authorization.username") + ":" + env.getProperty("accion.authorization.password")).getBytes());
        NONCE = String.valueOf(Math.random());
        TIMESTAMP = TIMESTAMP_FORMAT.format(new Date());
        String SignaturePlain = String.format("%s:%s:%s:%s", NONCE, TIMESTAMP, env.getProperty("accion.authorization.username"), env.getProperty("accion.authorization.secret.key"));
        SIGNATURE = hash(SignaturePlain, env.getProperty("accion.authorization.signature.method"));
        String responseString = "";
        Client client = ClientBuilder.newClient();
        Response response = client.target(env.getProperty("accion.middleware.base.url") + url)
                .request()
                .header("Authorization", AUTHORIZATION)
                .header("SignatureMethod", env.getProperty("accion.authorization.signature.method"))
                .header("Accept", "application/json")
                .header("Timestamp", TIMESTAMP)
                .header("Nonce", NONCE)
                .header("Signature", SIGNATURE)
                .header("ContentType", "application/json")
                .get();
        responseString = response.readEntity(String.class);
        response.close();
        client.close();
        return responseString;
    }
    
    @Override
    public String endPointPostRequest(String url, String requestJson, String username, String password) {
        USERNAME = username;
        PASSWORD = password;
        SIGNATURE_METHOD = env.getProperty("accion.authorization.signature.method");
        USER_SECRET_KEY = env.getProperty("accion.authorization.secret.key");
        AUTHORIZATION = "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes());
        MIDDLEWARE_HOST = env.getProperty("accion.middleware.host");
        MIDDLEWARE_PORT = env.getProperty("accion.middleware.port");
        BASE_URL = "http://" + MIDDLEWARE_HOST + ":" + MIDDLEWARE_PORT + "/T24Gateway/services";
        NONCE = String.valueOf(Math.random());
        TIMESTAMP = TIMESTAMP_FORMAT.format(new Date());
        String SignaturePlain = String.format("%s:%s:%s:%s", NONCE, TIMESTAMP, USERNAME, USER_SECRET_KEY);
        SIGNATURE = SignaturePlain; //this must be computation of SignaturePlain using SHA512
        SIGNATURE = hash(SignaturePlain, SIGNATURE_METHOD);
        String responseString = "";
        Client client = ClientBuilder.newClient();
        Response response = null;
        try {
            response = client.target(BASE_URL + url)
                    .request()
                    .header("Authorization", AUTHORIZATION)
                    .header("SignatureMethod", SIGNATURE_METHOD)
                    .header("Accept", "application/json")
                    .header("Timestamp", TIMESTAMP)
                    .header("Nonce", NONCE)
                    .header("Signature", SIGNATURE)
                    .header("ContentType", "application/json")
                    .post(Entity.json(requestJson));
            responseString = response.readEntity(String.class);
            response.close();
            client.close();
            return responseString;
        } catch (Exception ex) {
            return ex.getMessage();
        }
    }

    public static String hash(String plainText, String algorithm) {
        StringBuilder hexString = new StringBuilder();
        if (algorithm.equals("SHA512")) {
            algorithm = "SHA-512";
        }
        try {
            MessageDigest md = MessageDigest.getInstance(algorithm);
            md.update(plainText.getBytes());

            byte byteData[] = md.digest();

            //convert the byte to hex format method 1
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < byteData.length; i++) {
                sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
            }

            System.out.println("Hex format : " + sb.toString());

            //convert the byte to hex format method 2
            for (int i = 0; i < byteData.length; i++) {
                String hex = Integer.toHexString(0xff & byteData[i]);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return hexString.toString().toUpperCase();
    }

    @Override
    public String generateTransRef(String transType) {
        long number = (long) Math.floor(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return transType + number;
    }

    @Override
    public String getSystemParameter(String paramName) {

        if (genericRepository.getParameterValue(paramName) == null) {
            return null;
        }
        return genericRepository.getParameterValue(paramName);
    }

    @Override
    public String getStringFromOFSResponse(String response, String stringToGet) {
        //Create an array of the response string
        if (response.equals("")) {
            return "";
        } else if (!response.equals("") && !response.isEmpty()) {
            String[] responseArray = response.split(",");
            for (String str : responseArray) {
                //Split each string by the =
                String fields[] = str.split("=");
                if (fields.length == 0) {
                    return "";
                }

                if (fields[0].equals(stringToGet)) {
                    return fields.length == 2 ? fields[1] : "";
                }
            }
        }
        return "";
    }

    @Override
    public String getT24TransIdFromResponse(String response) {
        String[] splitString = response.split("//");
        return splitString[0].replace("\"", "");
    }

    @Override
    public String[] decodeT24RecordStatus(String t24RecordStatus) {
        String[] status = new String[2];
        switch (t24RecordStatus) {
            case "IHLD": {
                status[0] = "09";
                status[1] = "Request processing in progress";
                return status;
            }
            case "INAU": {
                status[0] = "09";
                status[1] = "Request processing in progress";
                return status;
            }
            case "REV": {
                status[0] = "09";
                status[1] = "Request processing in progress";
                return status;
            }
            default: {
                status[0] = "01";
                status[1] = "Status unknown, please wait for settlement report";
                return status;
            }
        }
    }

    @Override
    public String ofsResponse(String environment, String ofsRequest) {
        //Get from the environment where the menu is pointed to
        String response = "";
        if (environment == null || ofsRequest == null || environment.equals("") || ofsRequest.equals("")) {
            return null;
        }

        switch (environment.trim()) {
            case "Production":
                response = endPointPostRequest("/generic/payment/postofs", ofsRequest, env.getProperty("accion.middleware.production.username"), env.getProperty("accion.middleware.production.password"));
                break;
            case "Staging":
                response = endPointPostRequest("/generic/payment/postofs", ofsRequest, env.getProperty("accion.middleware.staging.username"), env.getProperty("accion.middleware.staging.password"));
                break;
            default:
                return null;
        }
        return response;
    }

    @Override
    public String validateResponse(String response) {
        if (response.contains("Maximum T24 users")) {
            return messageSource.getMessage("appMessages.maxLicense", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("Failed to receive message")) {
            return messageSource.getMessage("appMessages.serverDisconnect", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("No records were found") || response.contains("No entries for the period")) {
            return messageSource.getMessage("appMessages.recordRetrievalFailed", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("INVALID COMPANY SPECIFIED")) {
            return messageSource.getMessage("appMessages.invalidCompany", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("java.lang.OutOfMemoryError")) {
            return messageSource.getMessage("appMessages.outOfMemory", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("Failed to connect to host")) {
            return messageSource.getMessage("appMessages.serverDisconnect", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("Unreadable")) {
            return messageSource.getMessage("appMessages.dataTooLarge", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("MANDATORY INPUT")) {
            return response;
        }

        if (response.contains("Some errors while encountered")) {
            return response;
        }

        if (response.contains("Some override conditions have not been met")) {
            return response;
        }

        if (response.equals("<Unreadable>")) {
            return response;
        }

        if (response.contains("User has no id")) {
            return response;
        }

        if (response.equals("java.net.SocketException: Unexpected end of file from server")) {
            return response;
        }

        String[] responseSplit = response.split("//");
        if (responseSplit.length == 2) {
            if ("-1".equals(responseSplit[1].substring(0, 2))) {
                return response;
            } else if ("-2".equals(responseSplit[1].substring(0, 2))) {
                //Set the override message
                overrideMessage = response;
                return messageSource.getMessage("appMessages.overrideRequired", new Object[0], Locale.ENGLISH);
            }
        }

        if (response.contains("INVALID ACCOUNT")) {
            return response;
        }

        if (response.contains("MISSING") && !response.substring(0, 4).equals("\"//1")) {
            return response;
        }

        if (response.contains("java.net.SocketException")
                || response.contains("java.net.ConnectException")
                || response.contains("java.net.NoRouteToHostException")) {
            return response;
        }

        if (response.contains("SECURITY VIOLATION")) {
            return response;
        }

        if (response.contains("NOT SUPPLIED")) {
            return response;
        }

        if (response.contains("NO EN\\\"\\t\\\"TRIES FOR PERIOD")) {
            return messageSource.getMessage("appMessages.recordRetrievalFailed", new Object[0], Locale.ENGLISH);
        }

        if (response.contains("CANNOT ACCESS RECORD IN ANOTHER COMPANY")) {
            return response;
        }

        return null;
    }

    @Override
    public List<TextValueDto> parseOfsResponse(String ofsResponse) {
        List<TextValueDto> res = new ArrayList();
        ofsResponse = ofsResponse.replaceAll(":\\d{1,2}:\\d=", "=");
        String text;
        String value;
        for (String item : ofsResponse.split(",")) {

            String[] textValuePair = item.split("=");

            text = textValuePair[0];
            //exception is handled becuase value might not exist.
            try {
                value = textValuePair[1];
            } catch (Exception ex) {
                value = "";
            }
            res.add(new TextValueDto(value, text));
        }
        return res;
    }

    @Override
    public String getOfsValue(String ofsResponse, String fieldName) {
        List<TextValueDto> lst = parseOfsResponse(ofsResponse);
        if (lst != null && lst.size() > 0) {
            Optional<TextValueDto> value = lst.stream().filter(p -> p.getText().equals(fieldName)).findFirst();
            if (value.isPresent()) {
                return value.get().getValue();
            } else {
                return "";
            }
        } else {
            return "";
        }
    }

    @Override
    public String formatDate(String dateToFormat) {
        StringBuilder newDate = new StringBuilder(dateToFormat);
        if (dateToFormat.length() == 8) {
            newDate.insert(4, "-").insert(7, "-");
            return newDate.toString();
        }

        return "";
    }

    @Override
    public String cleanTextContent(String text) {
        // strips off all non-ASCII characters
        text = text.replaceAll("[^\\x00-\\x7F]", "");
        // erases all the ASCII control characters
        text = text.replaceAll("[\\p{Cntrl}&&[^\r\n\t]]", "");
        // removes non-printable characters from Unicode
        text = text.replaceAll("\\p{C}", "");
        return text.trim();
    }
    
    @Override
    public String cleanTextForT24(String textToClean) {
        //Remove the following , & ; etc from the text
        String replacedText = textToClean.trim()
                .replace(",", "")
                .replace("&", "");

        //Check if the string is a date
        if (replacedText.length() == 10 && replacedText.substring(4, 5).equals("-") && replacedText.substring(7, 8).equals("-")) {
            replacedText = replacedText.replace("-", "");
        }
        return replacedText;
    }
    
    @Override
    public char getTimePeriod() {
        char timePeriod = 'M';
        int hour = LocalDateTime.now().getHour();
        int morningStart = new Integer(env.getProperty("a24core.morning.start").trim());
        int morningEnd = new Integer(env.getProperty("a24core.morning.end").trim());
        int afternoonStart = new Integer(env.getProperty("a24core.afternoon.start").trim());
        int afternoonEnd = new Integer(env.getProperty("a24core.afternoon.end").trim());
        int eveningStart = new Integer(env.getProperty("a24core.evening.start").trim());
        int eveningEnd = new Integer(env.getProperty("a24core.evening.end").trim());
        int nightStart = new Integer(env.getProperty("a24core.night.start").trim());
        int nightEnd = new Integer(env.getProperty("a24core.night.end").trim());
        //Check the the period of the day
        if (hour >= morningStart && hour <= morningEnd) {
            timePeriod = 'M';
        }
        if (hour >= afternoonStart && hour <= afternoonEnd) {
            timePeriod = 'A';
        }
        if (hour >= eveningStart && hour <= eveningEnd) {
            timePeriod = 'E';
        }
        if (hour >= nightStart && hour <= nightEnd) {
            timePeriod = 'N';
        }
        return timePeriod;
    }
    
    @Override
    public String formatAmountWithComma(String amount) {
        if (amount == null || amount.equals("")) {
            return "";
        }

        if (!amount.matches("[0-9.]{1,}")) {
            return amount;
        }

        double value = new Double(amount.replace(",", ""));
        NumberFormat nf = NumberFormat.getNumberInstance();
        nf.setMaximumFractionDigits(2);
        nf.setRoundingMode(RoundingMode.FLOOR);
        String formattedAmount = nf.format(value);
        return formattedAmount;
    }
    
    @Override
    public String getAuthorizationHeader(String username, String password) {
        AUTHORIZATION = "Basic "+ Base64.getEncoder().encodeToString((username.concat(":").concat(password)).getBytes());
        return AUTHORIZATION;
    }
}
