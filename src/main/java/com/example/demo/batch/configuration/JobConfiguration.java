package com.example.demo.batch.configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean(name = "step1")
    public Step step1() {
        return stepBuilderFactory.get("step1").tasklet(new Tasklet() {

            @Override
            public RepeatStatus execute(StepContribution arg0, ChunkContext arg1) throws Exception {
                System.out.println("Hello World!");
                return RepeatStatus.FINISHED;
            }
        }).build();
    }

    @Bean(name = "job1")
    public Job job1() {
        return jobBuilderFactory.get("job1").incrementer(new RunIdIncrementer()).start(step1()).build();
    }
}
