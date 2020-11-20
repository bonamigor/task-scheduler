package br.com.zgsolucoes.task.scheduler

import br.com.zgsolucoes.task.scheduler.executaveis.ClasseExecutavel
import grails.compiler.GrailsCompileStatic
import grails.gorm.transactions.Transactional

@Transactional
@GrailsCompileStatic
class ExecutorExecutavelService {

    ClasseExecutavelFactoryService classeExecutavelFactoryService

    //Informar horas completas no dia pelo telegram
    boolean executar(final String titulo) {
        final Executavel executavel = Executavel.findByTitulo(titulo)
        final ClasseExecutavel classeExecutavel = classeExecutavelFactoryService.procurarClasseExecutavel(executavel)
        return classeExecutavel.execute()
    }


}
