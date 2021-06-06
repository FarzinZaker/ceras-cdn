package ceras.cdn

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(view:"/index")

        "/$path**"(controller: 'File', action: 'get')

        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
