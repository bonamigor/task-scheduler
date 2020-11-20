package br.com.zgsolucoes.task.scheduler.executaveis.implementacoes

import br.com.zgsolucoes.task.scheduler.executaveis.ClasseExecutavel
import groovy.transform.CompileStatic
import groovyx.net.http.HttpBuilder


@CompileStatic
class InformarHorasTelegram implements ClasseExecutavel{

    @Override
    boolean execute() {
        final Map env = System.getenv()
        final String TELEGRAM_BOT_ID = env['TELEGRAM_BOT_ID']
        final String TELEGRAM_BOT_KEY = env['TELEGRAM_BOT_KEY']
        final String TELEGRAM_CHAT_ID = env['TELEGRAM_CHAT_ID']

        final String encodedMessage = ''
        final String basePath = "https://api.telegram.org/"
        final String uriPath = "${TELEGRAM_BOT_ID}:${TELEGRAM_BOT_KEY}/sendMessage?chat_id=@${TELEGRAM_CHAT_ID}&text=" + encodedMessage

        //let's configure an http client to make calls to httpbin.org using the default http library
        final HttpBuilder httpBin = HttpBuilder.configure {
            request.uri = basePath
        }

        //now let's GET /get endpoint at httpbin.
        //This will return a JSON formatted response with an origin property.
        def result = httpBin.get {
            request.uri.path = uriPath
        }
        return false

    }
}
