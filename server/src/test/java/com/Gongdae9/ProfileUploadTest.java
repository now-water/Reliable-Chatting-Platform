package com.Gongdae9;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import org.apache.tomcat.util.codec.binary.Base64;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProfileUploadTest {

    @Test
    public void Base64인코딩() throws Exception {
        //given
        File oriImg = new File("./bono.jpg");
        if(oriImg.exists()){
            System.out.println(toBase64String(oriImg));
        }
        //when

        //then
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
