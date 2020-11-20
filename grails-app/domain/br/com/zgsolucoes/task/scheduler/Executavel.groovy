package br.com.zgsolucoes.task.scheduler

import grails.compiler.GrailsCompileStatic

@GrailsCompileStatic
class Executavel {

    String titulo
    String classeExecutavel

    static constraints = {
        titulo unique: true
    }
}
