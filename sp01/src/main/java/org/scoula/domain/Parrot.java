package org.scoula.domain;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component // 디폴트 컴포넌트의 name: 클래스명의 camelCase - parrot
public class Parrot {
    private String name;
    @PostConstruct
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
