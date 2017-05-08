package clone.swaper.infrastructure.command;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixThreadPoolProperties;

import static com.netflix.hystrix.HystrixCommandProperties.ExecutionIsolationStrategy;
import static com.netflix.hystrix.HystrixCommandProperties.Setter;

class HystrixCommandOptions {
    static HystrixCommand.Setter options(Command command) {
        Integer concurrencyLimit = (Integer) command.concurrencyLimit()
                .orElse(10);
        return HystrixCommand.Setter
                .withGroupKey(HystrixCommandGroupKey.Factory.asKey(command.name()))
                .andCommandKey(HystrixCommandKey.Factory.asKey(command.name()))
                .andCommandPropertiesDefaults(Setter()
                        .withCircuitBreakerSleepWindowInMilliseconds(30000)
                        .withCircuitBreakerRequestVolumeThreshold(concurrencyLimit)
                        .withExecutionIsolationStrategy(ExecutionIsolationStrategy.THREAD)
                        .withExecutionTimeoutInMilliseconds(command.executionTimeout()))
                .andThreadPoolPropertiesDefaults(HystrixThreadPoolProperties.Setter()
                        .withCoreSize(concurrencyLimit));
    }
}
