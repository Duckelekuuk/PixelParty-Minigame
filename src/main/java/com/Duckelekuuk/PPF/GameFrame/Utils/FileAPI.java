package com.Duckelekuuk.PPF.GameFrame.Utils;

import java.io.*;

/**
 * @AUTHOR Duco.
 * Description
 */
public class FileAPI {

    public static void removeFolderContent(File file) {
        try {
            if (file.isDirectory()) {
                String[] children = file.list();
                for (int i = 0; i < children.length; i++) {
                    removeFolderContent(new File(file, children[i]));
                }
            } else {
                file.delete();
            }
        } catch (Exception e) {
        }
    }

    public static void copyFolderContent(File sourceFile, File targetFile) {
        try {
            if (sourceFile.isDirectory()) {
                if (!targetFile.exists()) {
                    targetFile.mkdir();
                }

                String[] children = sourceFile.list();
                for (int i = 0; i < children.length; i++) {
                    copyFolderContent(new File(sourceFile, children[i]),
                            new File(targetFile, children[i]));
                }
            } else {

                InputStream in = new FileInputStream(sourceFile);
                OutputStream out = new FileOutputStream(targetFile);

                // Copy the bits from instream to outstream
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                in.close();
                out.close();
            }
        } catch (Exception e) {
            
        }
    }
}
