package br.com.zgsolucoes.task.scheduler.executaveis.implementacoes

import spock.lang.Specification


class InformarHorasTelegramSpec extends Specification{

    def "enviar mensagem para o telegram"() {
        given:
        InformarHorasTelegram informarHorasTelegram = new InformarHorasTelegram()

        when:
        final boolean result = informarHorasTelegram.execute()

        then:
        true
    }

}
