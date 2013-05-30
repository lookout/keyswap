package com.lookout.gpg;

import android.content.Context;
import android.content.Intent;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;

public class KeyringSyncManager {

    private static KeyringSyncManager instance;
    private static final String KEY_STORE_DIRECTORY = "/sdcard/LookoutPG/keyring/";

    private String storagePath;

    public static KeyringSyncManager getInstance() {
        if(instance == null) {
            instance = new KeyringSyncManager();
        }
        return instance;
    }

    private KeyringSyncManager() {
        File file = new File(KEY_STORE_DIRECTORY);
        file.mkdirs();

        this.storagePath = file.getAbsolutePath();

        Log.i("LookoutPG", "KeyringSyncManager initialized");
    }

    public void sync(Context context) {
        this.importPublicKeyring();
        this.exportPublicKeyring();

        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://" + storagePath)));
    }

    public void exportPublicKeyring() {
        ArrayList<GPGKey> publicKeys = GPGCli.getInstance().getPublicKeys();
        for(GPGKey key : publicKeys) {
            GPGCli.getInstance().exportKey(this.storagePath, key.getKeyId());
        }
    }

    public void importPublicKeyring() {
        for(File file : new File(storagePath).listFiles()) {
            GPGCli.getInstance().importKey(file.getAbsolutePath());
        }
    }
}
