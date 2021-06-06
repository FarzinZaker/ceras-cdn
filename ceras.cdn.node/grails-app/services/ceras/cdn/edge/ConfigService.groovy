package ceras.cdn.node

import grails.gorm.transactions.Transactional

@Transactional
class ConfigService {

    def grailsApplication

    String getRepositoryPath() {
        grailsApplication.config.cdn.repository.path
    }

    String getConfigPath() {
        grailsApplication.config.cdn.config.path
    }

    String getParentURL() {
        new File(configPath + 'parent').text?.trim()
    }

    Boolean getHasParent() {
        new File(configPath + 'parent').exists()
    }
}
