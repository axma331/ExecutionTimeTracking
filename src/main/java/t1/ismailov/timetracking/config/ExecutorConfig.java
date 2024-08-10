package t1.ismailov.timetracking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class ExecutorConfig {

    @Bean(name = "jobExecutor")
    public Executor taskExecutor() {
        return new ThreadPoolTaskExecutor();
    }
}