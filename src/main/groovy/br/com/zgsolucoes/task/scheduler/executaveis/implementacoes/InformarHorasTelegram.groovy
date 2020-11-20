package br.com.zgsolucoes.task.scheduler.executaveis.implementacoes

import br.com.zgsolucoes.task.scheduler.executaveis.ClasseExecutavel
import br.com.zgsolucoes.task.scheduler.executaveis.ObterDadosDoPonto
import groovy.transform.CompileStatic
import groovyx.net.http.HttpBuilder
import groovyx.net.http.UriBuilder


@CompileStatic
class InformarHorasTelegram implements ClasseExecutavel{

    @Override
    boolean execute() {
        final Map env = System.getenv()
        final String TELEGRAM_BOT_ID = env['TELEGRAM_BOT_ID']
        final String TELEGRAM_BOT_KEY = env['TELEGRAM_BOT_KEY']
        final String TELEGRAM_CHAT_ID = env['TELEGRAM_CHAT_ID']

        final ObterDadosDoPonto obterDadosDoPonto = new ObterDadosDoPonto()
        final String encodedMessage = obterDadosDoPonto.obterDadosPonto()

        final String basePath = "https://api.telegram.org/"
        final String uriPath = "${TELEGRAM_BOT_ID}:${TELEGRAM_BOT_KEY}/sendMessage"

        //let's configure an http client to make calls to httpbin.org using the default http library
        final HttpBuilder httpBin = HttpBuilder.configure {
            request.uri = basePath
        }

        final Map<String, ?> params = [
                chat_id : "@${TELEGRAM_CHAT_ID}".toString(),
                text : encodedMessage,
        ]

        final Map result = httpBin.get {
            final UriBuilder uriBuilder = request.uri
            uriBuilder.path = uriPath
            uriBuilder.query = params
        } as Map
        return result.ok
    }
}
