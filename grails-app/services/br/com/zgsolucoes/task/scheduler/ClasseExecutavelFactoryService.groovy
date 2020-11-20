package br.com.zgsolucoes.task.scheduler

import br.com.zgsolucoes.task.scheduler.executaveis.ClasseExecutavel
import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@Transactional
@CompileStatic
class ClasseExecutavelFactoryService {

    ClasseExecutavel procurarClasseExecutavel(final Executavel executavel) {
        final Class clazz = Class.forName("br.com.zgsolucoes.task.scheduler.executaveis.implementacoes.${executavel.classeExecutavel}")
        final ClasseExecutavel classeExecutavel = (ClasseExecutavel) clazz.newInstance()
        return classeExecutavel
    }
}
