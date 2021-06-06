package ceras.cdn.node

class FileController {

    def fileService
    def downloadService

    def get() {
        String path = params.path
        if (!fileService.exists(path)) {
            downloadService.download(path)
            if (!fileService.exists(path)) {
                response.status = 404
                return
            }
        }

        response.setContentType(fileService.getMimeType(path) ?: "application/octet-stream")
        response.setHeader("Content-disposition", "filename=${fileService.getName(path)}")
        response.outputStream << fileService.getBytes(path)
        response.flushBuffer()
    }
}
