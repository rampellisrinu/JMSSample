package jmssample

class MessageReceiverService {

    static exposes = ["jms"]
    static destination = "queue.notification"
    def onMessage(msg) {
        log.info "message received.."+msg
    }
}
