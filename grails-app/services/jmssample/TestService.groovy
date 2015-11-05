package jmssample

import grails.plugin.jms.Queue

class TestService {

    def jmsService

    def testJMS() {
        log.info "JMS Object " + jmsService
        jmsService.send(queue: "queue.notification", "Hello World!", "standard", null)
    }
}
