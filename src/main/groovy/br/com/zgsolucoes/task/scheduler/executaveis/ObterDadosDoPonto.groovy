package br.com.zgsolucoes.task.scheduler.executaveis

import groovy.transform.CompileStatic

import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@CompileStatic
class ObterDadosDoPonto {

    String obterDadosPonto() {
        TimeZone.default = TimeZone.getTimeZone('GMT-3')
        URL url = new URL('https://ponto.zgsolucoes.com.br/dok/espelho')
        HttpURLConnection con = (HttpURLConnection) url.openConnection()
        con.setRequestMethod('POST')
        con.doOutput = true
        Date hoje = new Date()
        con.getOutputStream().write("codPessoa=8&ano=${hoje.year + 1900}&mes=${(hoje.month + 1).toString().padLeft(2, '0')}".bytes)
        String pontosHoje = con.inputStream.text.find('coletadas hoje:.*')
        List<String> marcacoes = pontosHoje.findAll('\\d{2}:\\d{2}')
        List<Date> marcacoesData = marcacoes.collect {
            String[] mark = it.split(':')
            Date data = new Date()
            data.minutes = mark[1].toInteger()
            data.hours = mark[0].toInteger()
            data.seconds = 0
            return data
        }

        if (marcacoesData.size() % 2 != 0) {
            Date agora = new Date()
            agora.seconds = 0
            marcacoesData.add(agora)
        }

        long worked = 0
        Date dataAnterior = null
        marcacoesData.eachWithIndex { it, i ->
            if (i % 2 != 0) {
                long current = it.time - dataAnterior.time
                worked += current
            }
            dataAnterior = it
        }

        long faltam = 480 * 60 * 1000 - worked

//		println marcacoes
        if (faltam < 0) {
            return "Você já fez ${TimeUnit.MINUTES.convert(faltam.abs(), TimeUnit.MILLISECONDS)} minutos extras"
        } else {
            Calendar now = Calendar.getInstance()
            now.add(Calendar.MILLISECOND, faltam.toInteger())
            Date teenMinutesFromNow = now.getTime()
            final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm")
            return "Suas 8 horas serão completas às: ${teenMinutesFromNow}"
        }
    }

}