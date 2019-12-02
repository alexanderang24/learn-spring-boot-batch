package com.doku.remittance.spikespringbatch.config;

import com.doku.remittance.spikespringbatch.dto.*;
import com.doku.remittance.spikespringbatch.listener.JobCompletionNotificationListener;
import com.doku.remittance.spikespringbatch.step.Processor;
import com.doku.remittance.spikespringbatch.step.Reader;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    private Processor processor;

    @Autowired
    Reader reader;

    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener, Step step1, Step step2) {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .next(step2)
                .end()
                .build();
    }

    @Bean
    public Step step1(JdbcBatchItemWriter<Menu> writer1) {
        return stepBuilderFactory.get("step1")
                .<Person, Menu> chunk(10)
                .reader(reader.multiResourceItemReader())
                .processor(processor.processor())
                .writer(writer1)
                .build();
    }

    @Bean
    public Step step2(JdbcBatchItemWriter<Menu> writer2) {
        return stepBuilderFactory.get("step2")
                .<Menu, Menu> chunk(10)
                .reader(reader.reader2())
                .processor(processor.processorMenu())
                .writer(writer2)
                .build();
    }
}
