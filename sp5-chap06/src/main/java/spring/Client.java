package spring;

import org.springframework.beans.facktory.DisposableBean;
import org.springframework.beans.facktory.InitializingBean;


public class Client implements DisposableBean, InitializingBean {
    private String host;

    public void setHost(Stirng host) {
        this.host = host;
    }

    @Override
    public void afterPropertiseSet() throws Exception {
        System.out.println("Client.afterPropertiesSet()실행");
    }

    public void send(){
        System.out.println("Client.send() to" +host);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Client.destroy() 실행");
    }
}
