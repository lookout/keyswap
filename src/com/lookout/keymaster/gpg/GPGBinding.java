package com.lookout.keymaster.gpg;

import android.content.Context;

import java.util.List;

public interface GPGBinding {

    GPGKey getPublicKey(String keyId);
    GPGKey getSecretKey(String keyId);
    
    List<GPGKey> getPublicKeys();
    List<GPGKey> getSecretKeys();

    void exportKeyring(String destination);
    void exportKey(String destination, String keyId);
    void importKey(String source);

    void signKey(String fingerprint, TrustLevel trustLevel);

    void pushToKeyServer(String server, String keyId);

    String exportAsciiArmoredKey(String keyId);
    void importAsciiArmoredKey(String armoredKey);
}