package com.example.mhmsbmrapp.Login;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import static com.example.mhmsbmrapp.Login.GlobalVariables.PUBLIC_KEY;

public class Encryption {

    public String RSAEncryptOne(final String plainText) throws NoSuchAlgorithmException, NoSuchPaddingException,
            InvalidKeyException, IllegalBlockSizeException {
        String encodedString = "";
        byte[] encrypted = null;
        try {
            //PowerMockito.mockStatic(Base64.class);
            byte[] publicBytes = Base64.decode(PUBLIC_KEY, Base64.DEFAULT);
            System.out.println("dafdafasdfas"+publicBytes.toString());
/*            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicBytes);

            KeyFactory keyFactory = KeyFactory.getInstance("RSA");

            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1PADDING"); //or try with "RSA"
            cipher.init(Cipher.ENCRYPT_MODE, pubKey);
            encrypted = cipher.doFinal(plainText.getBytes());
            encodedString = Base64.encodeToString(encrypted, Base64.DEFAULT);*/
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return encodedString;
        //return "v03v5bVbom9xQj1YdJbim7NXmdr5hqZiC//XtWxUY3J2IyC5UzpOinqat++IsDLCQ66oV3dx3C++iF4YKzFVQIamDAek51RKAx1aFK805kDhHNsHJcnRc4kuBRCEq4kdAi3E0XSt+bzV+CXYLBJMFGwOER5nfo9aeRdk66t8cC+eME8ijvvUu64xNU1Zz7367e+AS9pe9bOzRVCtuSvOFilbZ7SM1sJ3KretI+umsBMQ1CBl5OpIwLbRmeGJ2XADW5aEsxi3/a83igfo388yLC4M/WgHDjuFhkf5dOFyCAqCM1EYIx+Tsu3FZixZkc4kD34nCLgaO4uEjNvD+ddsnvGs0jQhDINPExjxuUvqfEoPSRJ98uQnFbnNkqX2SRtf61hz0xnTHo9lwg4yuhhhkEbxgk1oJ2269sfLpVoDet0OdUblOzLdbfyBTshJ6h+EYNJzICdHFuyJQC+0OBP5f5m0TcKB6m+LHrhMjPaqJxftN8p5AT/nBpNBdnqPtulPsl12rhkhZx5TR2pnjH9KURyy+mhUMn+JBjlAhEakR/GbWmN/dzX88HMPhz/jRzzh6sUdkP3hv4jVKRxa81wiWp4SxaPFTt66uw5ntKUa7CzyiJD8ZNxFK3GFYnWJxVB/CfasVI/ufrJMUekj1qwbGYLJN7C70nz/2cbJhYFB9lM=";

    }



}
