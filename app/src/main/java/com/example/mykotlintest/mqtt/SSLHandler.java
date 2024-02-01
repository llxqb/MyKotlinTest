package com.example.mykotlintest.mqtt;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.os.Build;
import android.util.Base64;


import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManagerFactory;

/**
 * @描述： @SSLHandler
 * @作者： @黄卫旗
 * @创建时间： @05/09/2018
 */
public class SSLHandler {

    public static SSLSocketFactory getSSLSocket(Context context, String caPath, String password) throws Exception {
        // CA certificate is used to authenticate server
        CertificateFactory cAf = CertificateFactory.getInstance("X.509");
        InputStream caIn = context.getAssets().open(caPath);
        X509Certificate ca = (X509Certificate) cAf.generateCertificate(caIn);
        KeyStore caKs = KeyStore.getInstance(KeyStore.getDefaultType());
        caKs.load(null, null);
        caKs.setCertificateEntry("ca-certificate", ca);
        TrustManagerFactory tmf = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        tmf.init(caKs);
        caIn.close();

//        CertificateFactory cf = CertificateFactory.getInstance("X.509");
//        InputStream crtIn = JooanApplication.getAppContext().getAssets().open(crtPath);
//        X509Certificate caCert = (X509Certificate) cf.generateCertificate(crtIn);
//        crtIn.close();
        // client key and certificates are sent to server so it can authenticate us
        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
        ks.load(null, null);
//        ks.setCertificateEntry("certificate", caCert);
//        ks.setKeyEntry("private-key", getPrivateKey(keyPath), password.toCharArray(),
//                new java.security.cert.Certificate[]{caCert});
        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
        kmf.init(ks, password.toCharArray());

        SSLContext sslContext = SSLContext.getInstance("TLSv1");
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), new SecureRandom());
        return sslContext.getSocketFactory();
    }

    private static PrivateKey getPrivateKey(Application app, String path) throws Exception {
//        Base64 base64 = new Base64();
        byte[] buffer = Base64.decode(getPem(app, path),Base64.NO_WRAP);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);
//        KeyFactory keyFactory = KeyFactory.getInstance("RSA","BC");
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.P) {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
            return keyFactory.generatePrivate(keySpec);
        } else {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            return keyFactory.generatePrivate(keySpec);
        }
    }

    private static byte[] getPem(Application app, String path) throws Exception {
        InputStream fin = app.getAssets().open(path);
        BufferedReader br = new BufferedReader(new InputStreamReader(fin));
        String readLine;
        StringBuilder sb = new StringBuilder();
        while ((readLine = br.readLine()) != null) {
            if (readLine.charAt(0) == '-') {
                continue;
            } else {
                sb.append(readLine);
                sb.append('\r');
            }
        }
        fin.close();
        return sb.toString().getBytes();
    }
}
