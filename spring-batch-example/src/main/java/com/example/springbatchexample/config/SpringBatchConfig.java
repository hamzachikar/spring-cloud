package com.example.springbatchexample.config;

import com.example.springbatchexample.bean.User;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfig {
    @Bean
    public Job job(JobBuilderFactory jobBuilderFactory,
                   StepBuilderFactory stepBuilderFactory,
                   ItemReader<User> userItemReader,
                   ItemProcessor<User,User> userItemProcessor,
                   ItemWriter<User> userItemWriter){
        Step step=stepBuilderFactory.get("ETL-file-load")
                .<User,User>chunk(100)
                .reader(userItemReader)
                .processor(userItemProcessor)
                .writer(userItemWriter)
                .build();
        return jobBuilderFactory.get("ETL-load")
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();

    }
    @Bean
    public FlatFileItemReader<User> fileItemReader(@Value("${input}") Resource resource){
        FlatFileItemReader<User> userFlatFileItemReader=new FlatFileItemReader<>();
        userFlatFileItemReader.setResource(resource);
        userFlatFileItemReader.setName("CSV reader");
        userFlatFileItemReader.setLinesToSkip(1);
        userFlatFileItemReader.setLineMapper(lineMapper());
        return userFlatFileItemReader;
    }
    @Bean
    public LineMapper<User> lineMapper() {
        DefaultLineMapper<User> userDefaultLineMapper=new DefaultLineMapper<>();
        DelimitedLineTokenizer lineTokenizer=new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[]{"id","name","dept","salary"});
        userDefaultLineMapper.setLineTokenizer(lineTokenizer);
        BeanWrapperFieldSetMapper<User> fieldSetMapper=new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(User.class);
        userDefaultLineMapper.setFieldSetMapper(fieldSetMapper);
        return userDefaultLineMapper;
    }

}
