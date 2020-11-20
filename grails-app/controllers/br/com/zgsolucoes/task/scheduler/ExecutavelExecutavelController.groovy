package br.com.zgsolucoes.task.scheduler

import grails.compiler.GrailsCompileStatic
import org.springframework.http.HttpStatus

@GrailsCompileStatic
class ExecutavelExecutavelController {

    ExecutorExecutavelService executorExecutavelService


    def executar(final String titulo) {
        if (!titulo) {
            render(status: HttpStatus.INTERNAL_SERVER_ERROR)
            return
        }

        executorExecutavelService.executar(titulo)


    }
}
