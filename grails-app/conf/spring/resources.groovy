import org.apache.activemq.spring.ActiveMQConnectionFactory
import org.springframework.jms.connection.SingleConnectionFactory
// Place your Spring DSL code here

beans = {
    xmlns amq:"http://activemq.apache.org/schema/core"

    amq.'broker'(
            useJmx:'false',
            persistent:'true',
            //change this to use some other directory for data
            dataDirectory: 'my-activemq-data'){
        amq.'transportConnectors'{
            amq.'transportConnector'(uri:'tcp://localhost:61616')
        }
    }

    amqConnectionFactory(ActiveMQConnectionFactory) {
        brokerURL = "vm://localhost"
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
