package br.com.splessons.lesson17;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.List;
import java.util.Map;

//@Configuration
//@PropertySource(value = "classpath:jsonProperties.json", factory = JsonPropertySourceFactory.class)
//@ConfigurationProperties
public class JsonProperties {

    private String host;
    private int port;
    private boolean resend;
    private List<String> topics;
    private Map<String, ?> sender;
    private List<Name> names;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public boolean isResend() {
        return resend;
    }

    public void setResend(boolean resend) {
        this.resend = resend;
    }

    public List<String> getTopics() {
        return topics;
    }

    public void setTopics(List<String> topics) {
        this.topics = topics;
    }

    public Map<String, ?> getSender() {
        return sender;
    }

    public void setSender(Map<String, ?> sender) {
        this.sender = sender;
    }

    public List<Name> getNames() {
        return names;
    }

    public void setNames(List<Name> names) {
        this.names = names;
    }

    public static class Name {

        private int order;
        private String name;

        public int getOrder() {
            return order;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
