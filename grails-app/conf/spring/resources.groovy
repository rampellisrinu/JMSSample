import org.apache.activemq.broker.TransportConnector
import org.apache.activemq.spring.ActiveMQConnectionFactory
import org.apache.activemq.usage.StoreUsage
import org.apache.activemq.usage.SystemUsage
import org.apache.activemq.usage.TempUsage
import org.apache.activemq.xbean.XBeanBrokerService
import org.springframework.jms.connection.SingleConnectionFactory
// Place your Spring DSL code here

beans = {
    xmlns amq:"http://activemq.apache.org/schema/core"

    myTempUsage(TempUsage){
        myTempUsage.limit=512*1024*1024
    }

    myStoreUsage(StoreUsage){
        myStoreUsage.limit=512*1024*1024
    }

    mySystemUsage(SystemUsage){
        mySystemUsage.tempUsage =ref('myTempUsage')
        mySystemUsage.storeUsage =ref('myStoreUsage')
    }

    myBrokerService(XBeanBrokerService){bean->
        myBrokerService.useJmx = '${grails.jms.useJmx}'
        myBrokerService.persistent = '${grails.jms.persistent}'
        myBrokerService.dataDirectory = '${grails.jms.dataDirectory}'
        myBrokerService.systemUsage = ref('mySystemUsage')
        myBrokerService.deleteAllMessagesOnStartup = true
        transportConnectors {transportConnector(TransportConnector,uri: '${grails.jms.transportConnector}')}
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
