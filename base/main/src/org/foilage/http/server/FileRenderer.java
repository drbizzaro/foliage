package org.foilage.http.server;

import org.foilage.authorization.exceptions.NotAuthorizedException;
import org.foilage.utils.log.Log;

import java.io.*;
import java.nio.file.Files;

public enum FileRenderer {

    INSTANCE;

    public void renderFile(OutputStream outImg, String url, File dataFilesRoot) throws IOException, NotAuthorizedException {

        if(verifyFile(url, dataFilesRoot)) {

            outImg.write(Files.readAllBytes(new File(dataFilesRoot.toString() + url).toPath()));

        } else {

            throw new NotAuthorizedException("Client trying to retrieve information outside data files root. Tried path: "+url);
        }

    }

    private boolean verifyFile(String url, File dataFilesRoot) {

        try {

            File callFile = new File(dataFilesRoot.toString() + url).getCanonicalFile();

            File parent = callFile.getParentFile();

            while (parent != null) {

                if (dataFilesRoot.equals(parent)) {

                    return true;
                }
                parent = parent.getParentFile();
            }

            return false;

        } catch (IOException e) {

            Log.error(e.getMessage());

            return false;
        }
    }
}
