package br.com.zgsolucoes.task.scheduler

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ExecutavelController {

    ExecutavelService executavelService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond executavelService.list(params), model:[executavelCount: executavelService.count()]
    }

    def show(Long id) {
        respond executavelService.get(id)
    }

    def create() {
        respond new Executavel(params)
    }

    def save(Executavel executavel) {
        if (executavel == null) {
            notFound()
            return
        }

        try {
            executavelService.save(executavel)
        } catch (ValidationException e) {
            respond executavel.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'executavel.label', default: 'Executavel'), executavel.id])
                redirect executavel
            }
            '*' { respond executavel, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond executavelService.get(id)
    }

    def update(Executavel executavel) {
        if (executavel == null) {
            notFound()
            return
        }

        try {
            executavelService.save(executavel)
        } catch (ValidationException e) {
            respond executavel.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'executavel.label', default: 'Executavel'), executavel.id])
                redirect executavel
            }
            '*'{ respond executavel, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        executavelService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'executavel.label', default: 'Executavel'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'executavel.label', default: 'Executavel'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
