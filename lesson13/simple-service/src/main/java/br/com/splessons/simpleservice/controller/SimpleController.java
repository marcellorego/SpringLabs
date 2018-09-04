package br.com.splessons.simpleservice.controller;

import br.com.splessons.simpleservice.client.StarClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/simple")
public class SimpleController {

    @Value("${user.environment}")
    private String userEnv;

    @Value("${spring.application.name}")
    private String applicationName;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private StarClient starClient;

    @GetMapping(value = "/env/{username}", produces = MediaType.TEXT_PLAIN_VALUE)
    public String userEnv(@PathVariable("username") String username) {
        return String.format("Hello %s! You are at %s environment\n", username, userEnv);
    }

    @ResponseBody
    @GetMapping(value = "")
    public String home() {
        return "<a href='showAllServiceIds'>Show All Service Ids</a>";
    }

    @ResponseBody
    @GetMapping(value = "/showAllServiceIds")
    public String showAllServiceIds() {

        List<String> serviceIds = this.discoveryClient.getServices();

        if (serviceIds == null || serviceIds.isEmpty()) {
            return "No services found!";
        }
        String html = "<h3>Service Ids:</h3>";
        for (String serviceId : serviceIds) {
            html += "<br><a href='showService?serviceId=" + serviceId + "'>" + serviceId + "</a>";
        }
        return html;
    }

    @ResponseBody
    @GetMapping(value = "/showService", produces = MediaType.TEXT_HTML_VALUE)
    public String showFirstService(@RequestParam(defaultValue = "") String serviceId) {

        // (Need!!) eureka.client.fetchRegistry=true
        List<ServiceInstance> instances = this.discoveryClient.getInstances(serviceId);

        if (instances == null || instances.isEmpty()) {
            return "No instances for service: " + serviceId;
        }
        String html = "<h2>Instances for Service Id: " + serviceId + "</h2>";

        for (ServiceInstance serviceInstance : instances) {
            html += "<h3>Instance: " + serviceInstance.getUri() + "</h3>";
            html += "Host: " + serviceInstance.getHost() + "<br>";
            html += "Port: " + serviceInstance.getPort() + "<br>";
        }

        return html;
    }

    // A REST method, To call from another service.
    // See in Lesson "Load Balancing with Ribbon".
    @ResponseBody
    @GetMapping(value = "/hello", produces = MediaType.TEXT_HTML_VALUE)
    public String hello() {

        return "<html>Hello from " + applicationName + "</html>";
    }

    @ResponseBody
    @GetMapping("/stars/{bookId}")
    public List<Integer> findBookStars(@PathVariable Long bookId) {
        return starClient.bookStars(bookId);
    }


}
