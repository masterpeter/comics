package it.mastropietro.marvelcomics.data.repository;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

/**
 * Created by Angelo Mastropietro on 11/03/17.
 */
@Singleton
class ApiKeyProvider {

    private Clock clock;
    private final String publicKey;
    private final String privateKey;

    @Inject
    public ApiKeyProvider(Clock clock,
                          @Named("publicKey") String publicKey,
                          @Named("privateKey") String privateKey) {
        this.clock = clock;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public Map<String, String> getQueryMap() {
        String ts = String.valueOf(clock.getCurrentTimeInMillis());
        Map<String, String> map = new HashMap<>();
        map.put("apikey", publicKey);
        map.put("ts", ts);
        map.put("hash", buildMD5Hash(ts));
        return map;
    }

    private String buildMD5Hash(String ts) {
        try {
            String toDigest = ts + privateKey + publicKey;
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] digest = md5.digest(toDigest.getBytes("UTF-8"));
            StringBuilder stringBuffer = new StringBuilder();
            for (int i = 0; i < digest.length; ++i) {
                String fullHex = Integer.toHexString((digest[i] & 0xFF) | 0x100);
                stringBuffer.append(fullHex.substring(1, 3));
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
