package com.Gongdae9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class ProfileUploadTest {

    @Test
    public void Base64인코딩() throws Exception {
        //given
        String encoded="";
        File oriImg = new File("./bono.jpg");

        //when
        if(oriImg.exists()){
            //System.out.println(toBase64String(oriImg));
            encoded = toBase64String(oriImg);
        }

        //then
        toByteArray(encoded);
    }

    private void toByteArray(String encoded) {
        byte decode[] = Base64.decodeBase64(encoded);
        FileOutputStream fos;

        try{
            File target = new File("./decoded.jpg");
            target.createNewFile();
            fos = new FileOutputStream(target);
            fos.write(decode);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("DONE");
    }

    private String toBase64String(File file) {
        String encodedStr = "";
        FileInputStream fis;

        try {
            byte[] bArr = new byte[(int)file.length()];
            fis = new FileInputStream(file);
            fis.read(bArr, 0, bArr.length - 1);
            fis.close();
            encodedStr = Base64.encodeBase64String(bArr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return encodedStr;
    }
}
