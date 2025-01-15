package com.itwillbs.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
@EnableAsync
public class AsyncConfig implements AsyncConfigurer {

	// 이메일을 보내는 데 걸리는 시간(지연 시간)을 줄이기 위한 비동기 처리
	
    @Override
    //@Bean(name = "mailExecutor")
    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(2);	// 기본적으로 실행 대기 중인 Thread 의 개수
        executor.setMaxPoolSize(5);		// 동시에 동작하는 최대 Thread의 개수
        executor.setQueueCapacity(10);	// CorePool의 크기를 넘어서면 큐에 저장하는데 그 큐의 최대 용량
        executor.setThreadNamePrefix("Async MailExecutor-");
        executor.initialize();
        return executor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
