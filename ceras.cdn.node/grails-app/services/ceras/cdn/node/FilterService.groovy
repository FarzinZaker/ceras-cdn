package ceras.cdn.node

import grails.gorm.transactions.Transactional

@Transactional
class FilterService {

    def isValid(String path) {
        ![
                'robots.txt',
                'favicon.ico',
        ].contains(path)
    }
}
