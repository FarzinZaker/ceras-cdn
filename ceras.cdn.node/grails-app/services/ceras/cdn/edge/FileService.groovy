package ceras.cdn.node

import grails.gorm.transactions.Transactional
import sun.nio.fs.UnixPath

import javax.servlet.http.HttpServletResponse
import java.nio.file.Files
import java.nio.file.Path

@Transactional
class FileService {

    def configService

    String getFullPath(String path){
        configService.repositoryPath + path
    }

    boolean exists(String path) {
        new File(getFullPath(path)).exists()
    }

    byte[] getBytes(String path){
        new File(getFullPath(path)).bytes
    }

    String getName(String path){
        new File(getFullPath(path)).name
    }

    String getMimeType(String path){
        InputStream is = new BufferedInputStream(new FileInputStream(new File(getFullPath(path))))
        URLConnection.guessContentTypeFromStream(is)
    }
}
