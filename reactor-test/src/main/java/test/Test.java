package test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(111);
        Flux.merge(test(), test2());
        Flux.just(1);
        System.out.println(222);
    }

    public static Flux<Void> test() throws InterruptedException {
        System.out.println("执行test");
        Thread.sleep(5000);
        System.out.println("test执行完毕");
       return Flux.empty();
    }

    public static Flux<Void> test2() throws InterruptedException {
        System.out.println("执行test2");
        Thread.sleep(5000);
        System.out.println("test执行完毕");
        return Flux.empty();
    }

    public Mono<Void> test11() {
        return Mono.empty();
    }

    public void teee() {

    }

}
