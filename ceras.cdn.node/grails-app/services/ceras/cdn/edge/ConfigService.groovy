package ceras.cdn.node

import com.amazonaws.regions.Regions
import grails.gorm.transactions.Transactional

@Transactional
class ConfigService {

    def grailsApplication

    private Properties _config

    Properties getConfig(){
        if(!_config) {
            _config = new Properties()
            File propertiesFile = new File('/app/config/config.properties')
//            File propertiesFile = new File('/opt/cdn/config.properties')
            propertiesFile.withInputStream {
                _config.load(it)
            }
        }
        _config
    }

    String getName() {
        System.getenv('name') ?: grailsApplication.config.cdn.name
    }

    String getS3Bucket() {
        System.getenv('s3_bucket') ?: grailsApplication.config.cdn.s3_bucket
    }

    String getS3Root() {
        System.getenv('s3_root') ?: grailsApplication.config.cdn.s3_root
    }

    String getParentURL() {
        System.getenv('parent_url') ?: grailsApplication.config.cdn.parent_url
    }

    Boolean getHasParent() {
        getParentURL()
    }

    String getRepositoryPath() {
        "${s3Root}files/"
    }

    String getAWSAccessKey() {
        config."aws_access_key"
    }

    String getAWSSecretKey() {
        config."aws_secret_key"
    }

    Regions getAWSRegion() {
        Regions.fromName(config."aws_region"?.toString())
    }

    String getInfluxDBHost() {
        config."influx_host"
    }

    Integer getInfluxDBPort() {
        config."influx_port"?.toString()?.toInteger()
    }

    String getInfluxDBOrg() {
        config."influx_org"
    }

    String getInfluxDBBucket() {
        config."influx_bucket"
    }

    String getInfluxDBToken() {
        config."influx_token"
    }
}
