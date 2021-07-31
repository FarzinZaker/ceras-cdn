package ceras.cdn.node

import grails.gorm.transactions.Transactional

@Transactional
class FileService {

    def configService

    String getFullPath(String path) {
        configService.repositoryPath + path
    }

    String getTempPath() {
        configService.repositoryPath + 'tmp/' + (UUID.randomUUID().toString())
    }

    boolean exists(String path) {
        new File(getFullPath(path)).exists()
    }

    boolean tempFileExists(String path) {
        new File(path).exists()
    }

    byte[] getBytes(String path) {
        exists(path) ? new File(getFullPath(path)).bytes : null
    }

    byte[] getTempBytes(String path) {
        tempFileExists(path) ? new File(path).bytes : null
    }

    def deleteTemp(String path) {
        new File(path).delete()
    }

    String getName(String path) {
        new File(getFullPath(path)).name
    }

    String getMimeType(String path) {
        InputStream is = new BufferedInputStream(new FileInputStream(new File(path)))
        URLConnection.guessContentTypeFromStream(is)
    }

    String getMimeType(byte[] bytes) {
        InputStream is = new BufferedInputStream(new ByteArrayInputStream(bytes))
        URLConnection.guessContentTypeFromStream(is)
    }
}
