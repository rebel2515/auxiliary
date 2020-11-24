/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.eidevs.auxiliary.service;

import  com.eidevs.auxiliary.payload.TextValueDto;
import java.util.List;
/**
 *
 * @author bokon
 */
public interface GenericService {

    String endPointPostRequest(String url, String requestJson);

    String endPointPostRequest(String url, String requestJson, String username, String password);

    String endPointGetRequest(String url);

    String generateTransRef(String transType);

    String getSystemParameter(String paramName);

    String getStringFromOFSResponse(String response, String stringToGet);

    String[] decodeT24RecordStatus(String t24RecordStatus);

    String getT24TransIdFromResponse(String response);

    String ofsResponse(String environment, String ofsRequest);

    String validateResponse(String response);

    String getOfsValue(String ofsResponse, String fieldName);

    List<TextValueDto> parseOfsResponse(String ofsResponse);

    String formatDate(String dateToFormat);

    String cleanTextContent(String text);

    String cleanTextForT24(String textToClean);

    char getTimePeriod();

    String formatAmountWithComma(String amount);
    
    String getAuthorizationHeader(String username, String password);
}
