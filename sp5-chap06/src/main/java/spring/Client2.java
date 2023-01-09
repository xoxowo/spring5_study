package spring;

public class Client2 {
    private String host;

    public void setHost(String host) {
        this.host = host;
    }

    public void connect() {
        System.out.println("Client2.connet() 실행");
    }

    public void send() {
        System.out.println("Clinet2.send() to" + host);
    }

    public void close() {
        System.out.println("Client2.close() 실행");
    }
}

/*
설정 파일에서 initMethod속성과 destroyMethod속성을 \
사용할 메서드인 이름으로 지정해주어야한다.

    @Bean(initMethod = "connect", destroyMethod= "close")
    public Client2 client2() {
        Client2 client = new Client2();
        client.setHost("host");
        return client;
    }
 */