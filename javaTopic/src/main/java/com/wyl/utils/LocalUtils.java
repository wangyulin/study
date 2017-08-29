package com.wyl.utils;

import java.io.File;

/**
 * Created by wangyulin on 3/2/16.
 */
public class LocalUtils {

    public static File getLocalDataPath() {
        String _path = System.getenv("dataPath");
        if(_path != null) {
            return new File(_path);
        } else {
            String home = System.getProperty("HOME");
            return new File(home + "/workDir/workspaces/javaTopic/src/data/");
        }
    }

}
