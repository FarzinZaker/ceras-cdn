package ceras.cdn.node

import com.amazonaws.services.s3.model.AmazonS3Exception

class FileController {

    def fileService
    def downloadService
    def configService
    def filterService
    def timeseriesService
    def s3Service

    def get() {

        def timer = new Timer()

        String path = params.path

        if (!filterService.isValid(path)) {
            response.status = 404
            return
        }

        def name = path.split('/')?.last()
        def bytes = null
        try {
            bytes = s3Service.getFileBytes(path)
        } catch (AmazonS3Exception ignore) {
        }
        String mimeType

        if (bytes) {
            mimeType = fileService.getMimeType(bytes)
            timer.logLocalTime()
        } else {
            if (configService.hasParent) {
                path = downloadService.downloadTemp(path, timer)
                if (fileService.tempFileExists(path)) {
                    bytes = fileService.getTempBytes(path)
                    mimeType = fileService.getMimeType(bytes)
                    fileService.deleteTemp(path)
                    timer.logRemoteTime()
                } else {
                    response.status = 404
                    timer.logLocalTime()
                    return
                }
            } else {
                response.status = 404
                timer.logLocalTime()
                return
            }
        }

        response.setContentType(mimeType ?: "application/octet-stream")
        response.setHeader("Content-disposition", "filename=${name}")
        response.outputStream << bytes
        response.flushBuffer()
        timer.logLocalTime()

        timeseriesService.logProcessingTimes(path, timer.localTime, timer.remoteTime)
    }
}
