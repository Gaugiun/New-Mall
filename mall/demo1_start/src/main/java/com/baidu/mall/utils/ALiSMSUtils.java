package com.baidu.mall.utils;


import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ALiSMSUtils {


    public static void sentSMS(String phone, String verrifyCode) {
        String accessKeyId = "LTAI4Fr5gfYhcVjLMqeRGbuT";
        String accessKeySecret = "IrkcHu6dZyrjPZRushg076P5392HJ1";
        String signName = "stone4j";
        String templateCode = "SMS_173765187";
        DefaultProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();

        request.setMethod(MethodType.POST);
        request.setDomain("dysmsapi.aliyuncs.com");
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");

        request.putQueryParameter("PhoneNumbers", phone);


        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        String temlateParam = "{\"code\":\"" + verrifyCode + "\"}";
        request.putQueryParameter("TemplateParam", temlateParam);

        CommonResponse response;

        {
            try {
                response = client.getCommonResponse(request);
                System.out.println(response.getData());
            } catch (ClientException e) {
                e.printStackTrace();
            }
        }
    }



}
