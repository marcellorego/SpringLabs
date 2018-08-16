package br.com.splessons;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class MappingConfig {

    private static class FromToMap {

        public String getFrom() {
            return from;
        }

        public void setFrom(String from) {
            this.from = from;
        }

        public String getTo() {
            return to;
        }

        public void setTo(String to) {
            this.to = to;
        }

        private String from;
        private String to;
    }

    private Map<Integer, String> title;
    private List<FromToMap> state;

    public Map<Integer, String> getTitle() {
        return title;
    }

    public void setTitle(Map<Integer, String> title) {
        this.title = title;
    }

    public List<FromToMap> getState() {
        return state;
    }

    public void setState(List<FromToMap> state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return getState().stream()
                .map(item -> "{".concat(item.getFrom()).concat(":").concat(item.getTo()).concat("}"))
                .collect(Collectors.joining(",","[","]"));
    }
}
