package com.github.superkoh.wechat2.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.superkoh.wechat2.bean.sns.WxaSnsUserInfo;
import java.io.IOException;
import java.security.AlgorithmParameters;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import lombok.val;
import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WxaDecryptUtils {

  private static final Logger logger = LoggerFactory.getLogger(WxaDecryptUtils.class);

  private static final ObjectMapper objectMapper;

  static {
    objectMapper = new ObjectMapper();
    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    objectMapper.setSerializationInclusion(Include.NON_NULL);

    Security.addProvider(new BouncyCastleProvider());
  }


  public static WxaSnsUserInfo decryptUserInfo(String encryptedData, String iv,
      String sessionKey) {
    val res = decrypt(Base64.decodeBase64(encryptedData),
        Base64.decodeBase64(sessionKey), Base64.decodeBase64(iv));
    if (null != res && res.length > 0) {
      try {
        val resStr = new String(res, "UTF8");
        return objectMapper.readValue(resStr, WxaSnsUserInfo.class);
      } catch (IOException e) {
        logger.error(e.getMessage(), e);
        return null;
      }
    }
    return null;
  }

  private static byte[] decrypt(byte[] content, byte[] keyByte, byte[] ivByte) {
    try {
      val cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
      val sKeySpec = new SecretKeySpec(keyByte, "AES");
      val params = AlgorithmParameters.getInstance("AES");
      params.init(new IvParameterSpec(ivByte));
      cipher.init(Cipher.DECRYPT_MODE, sKeySpec, params);// 初始化
      return cipher.doFinal(content);
    } catch (Exception e) {
      logger.error(e.getMessage(), e);
    }
    return null;
  }
}
