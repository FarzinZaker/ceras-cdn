package ceras.cdn.node

import grails.gorm.transactions.Transactional

@Transactional
class DownloadService {

    def fileService
    def configService

    String getFullURL(String path) {
        configService.parentURL + path
    }

    def download(String path) {
        if (!configService.hasParent)
            return

        def fullPath = fileService.getFullPath(path)
        new File(fullPath.substring(0, fullPath.lastIndexOf('/') ?: fullPath.size() - 1)).mkdirs()
        def targetFile = new File(fullPath)
        targetFile.createNewFile()
        targetFile.withOutputStream { out ->
            new URL(getFullURL(path)).withInputStream { from -> out << from }
        }
    }
}
