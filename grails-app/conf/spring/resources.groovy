import org.apache.activemq.spring.ActiveMQConnectionFactory
import org.springframework.jms.connection.SingleConnectionFactory
// Place your Spring DSL code here

beans = {
    xmlns amq:"http://activemq.apache.org/schema/core"

    amq.'broker'(
            useJmx: '${grails.jms.useJmx}',//'false',
            persistent:'${grails.jms.persistent}',
            //change this to use some other directory for data
            dataDirectory: '${grails.jms.dataDirectory}'){
        amq.'transportConnectors'{
            amq.'transportConnector'(uri:'${grails.jms.transportConnector}')
        }
    }

    amqConnectionFactory(ActiveMQConnectionFactory) {
        brokerURL = '${grails.jms.brokerUrl}'
    }

    jmsConnectionFactory(SingleConnectionFactory) {
        targetConnectionFactory = ref(amqConnectionFactory)
    }

    /*jmsConnectionFactory(SingleConnectionFactory) {
        targetConnectionFactory = { ActiveMQConnectionFactory cf ->
            brokerURL = "vm://localhost"
        }
    }*/
}
