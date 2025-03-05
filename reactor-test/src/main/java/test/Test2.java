package test;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class Test2 {
    public static void main(String[] args) throws InterruptedException {
// 创建两个 Flux 流，分别执行不同的任务
        Mono<String> task1 = Mono.just("任务1完成").delayElement(java.time.Duration.ofSeconds(2));
        Mono<String> task2 = Mono.just("任务2完成").delayElement(java.time.Duration.ofSeconds(1));

        // 使用 zip 合并两个任务，并且确保只有两个任务都完成后才会执行下一步
        Flux.zip(task1, task2)
                .doOnNext(tuple -> {
                    System.out.println("任务1结果: " + tuple.getT1());
                    System.out.println("任务2结果: " + tuple.getT2());
                }).blockFirst();
    }

}
