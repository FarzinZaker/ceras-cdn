package ceras.cdn.node

import grails.gorm.transactions.Transactional

@Transactional
class DownloadService {

    def fileService
    def configService

    String getFullURL(String path) {
        configService.parentURL + path
    }

    def download(String path, Timer timer) {
        if (!configService.hasParent)
            return

        def fullPath = fileService.getFullPath(path)
        downloadFile(path, fullPath, timer)

        fullPath
    }

    def downloadTemp(String path, Timer timer) {
        if (!configService.hasParent)
            return

        def tempPath = fileService.getTempPath()
        downloadFile(path, tempPath, timer)

        tempPath
    }

    def downloadFile(String remotePath, String localPath, Timer timer){
        new File(localPath.substring(0, localPath.lastIndexOf('/') ?: tempPath.size() - 1)).mkdirs()
        def targetFile = new File(localPath)
        targetFile.createNewFile()
        timer.logLocalTime()
        targetFile.withOutputStream { out ->
            new URL(getFullURL(remotePath)).withInputStream { from -> out << from }
        }
        timer.logRemoteTime()
    }
}
