package br.com.zgsolucoes.task.scheduler

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ExecutavelServiceSpec extends Specification {

    ExecutavelService executavelService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Executavel(...).save(flush: true, failOnError: true)
        //new Executavel(...).save(flush: true, failOnError: true)
        //Executavel executavel = new Executavel(...).save(flush: true, failOnError: true)
        //new Executavel(...).save(flush: true, failOnError: true)
        //new Executavel(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //executavel.id
    }

    void "test get"() {
        setupData()

        expect:
        executavelService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Executavel> executavelList = executavelService.list(max: 2, offset: 2)

        then:
        executavelList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        executavelService.count() == 5
    }

    void "test delete"() {
        Long executavelId = setupData()

        expect:
        executavelService.count() == 5

        when:
        executavelService.delete(executavelId)
        sessionFactory.currentSession.flush()

        then:
        executavelService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Executavel executavel = new Executavel()
        executavelService.save(executavel)

        then:
        executavel.id != null
    }
}
