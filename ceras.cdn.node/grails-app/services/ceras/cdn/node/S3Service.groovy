package ceras.cdn.node


import com.amazonaws.auth.AWSStaticCredentialsProvider
import com.amazonaws.auth.BasicAWSCredentials
import com.amazonaws.services.s3.AmazonS3
import com.amazonaws.services.s3.AmazonS3ClientBuilder
import com.amazonaws.services.s3.model.S3Object
import com.amazonaws.util.IOUtils
import grails.gorm.transactions.Transactional

import java.nio.charset.StandardCharsets
import java.util.stream.Collectors

@Transactional
class S3Service {

    def configService

    AmazonS3 getS3Client() {
        AmazonS3ClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(
                        configService.getAWSAccessKey(), configService.getAWSSecretKey()
                )))
                .withRegion(configService.getAWSRegion())
                .build()
    }

    byte[] getFileBytes(String path) {
        def s3 = s3Client
        S3Object o = s3.getObject(configService.s3Bucket, getFullPath(path))
        IOUtils.toByteArray(o.getObjectContent())
    }

    String getFileText(String path) {
        def s3 = s3Client
        S3Object o = s3.getObject(configService.s3Bucket, getFullPath(path))
        final InputStreamReader streamReader = new InputStreamReader(o.getObjectContent(), StandardCharsets.UTF_8)
        final BufferedReader reader = new BufferedReader(streamReader)
        reader.lines().collect(Collectors.toSet())
    }

    String getFullPath(String path){
        def fullPath = "${configService.s3Root}/files/${path}"
        while(fullPath.contains('//'))
            fullPath = fullPath.replace('//', '/')
        fullPath
    }
}
