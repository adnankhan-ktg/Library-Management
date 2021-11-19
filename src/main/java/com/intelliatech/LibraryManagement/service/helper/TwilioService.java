package com.intelliatech.LibraryManagement.service.helper;

import com.twilio.Twilio;
import org.springframework.web.client.RestTemplate;

public class TwilioService {


    public static final String account_sid = "ACbae77ba08dc521338b52cd569c0210ba";
    public static final String auth_token = "60edd37510a6a36fdd707a8d9aae02e8";
    public static final String trial_number = "+16155675562";

    static {
        Twilio.init(account_sid, auth_token);
    }

    public String otpSendToMobile(String mobileNumber, Integer otp) {

        RestTemplate restTemplate = new RestTemplate();
        String countryCode = "91";
        try {
            restTemplate.getForEntity("http://2factor.in/API/V1/9e879a29-2417-11eb-83d4-0200cd936042/SMS/" + countryCode
                    + mobileNumber + "/ " + otp + "/PlayStoreMaikhaneUser", Object.class);

            restTemplate.getForEntity("http://2factor.in/API/V1/9e879a29-2417-11eb-83d4-0200cd936042/SMS/" + countryCode
                    + mobileNumber + "/ " + otp + "/MaikhaneServiceProviderLoginOTP", Object.class);


            System.out.println("success");
        } catch (Exception e) {
            return "UnSuccess";
        }
        System.out.println(otp);
        return "Success";
    }



}
