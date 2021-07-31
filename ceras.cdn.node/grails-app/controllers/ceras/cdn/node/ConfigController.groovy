package ceras.cdn.node

import grails.converters.JSON

class ConfigController {

    def configService

    def list() {
        render([
                name: configService.name,
                s3Bucket: configService.s3Bucket,
                s3Root: configService.s3Root,
                parentURL: configService.parentURL,
        ] as JSON)
    }
}
