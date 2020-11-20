package br.com.zgsolucoes.task.scheduler

import grails.gorm.transactions.Transactional
import groovy.transform.CompileStatic

@Transactional
@CompileStatic
class ExecutavelCrudService {

    void criarExecutavel(final String titulo, final String classeExecutavel) {
        final Executavel executavel = new Executavel(titulo: titulo, classeExecutavel: classeExecutavel)
        executavel.save()
    }
}
