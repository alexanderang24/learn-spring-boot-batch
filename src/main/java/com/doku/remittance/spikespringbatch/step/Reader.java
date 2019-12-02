package com.doku.remittance.spikespringbatch.step;

import com.doku.remittance.spikespringbatch.dto.Menu;
import com.doku.remittance.spikespringbatch.dto.Person;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class Reader {

    @Value("#{'${sample.data}'.split(',')}")
    Resource[] inputResources;

    public FlatFileItemReader<Person> reader() {

        BeanWrapperFieldSetMapper<Person> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Person.class);

        return new FlatFileItemReaderBuilder<Person>()
                .name("personItemReader")
                .delimited()
                .names(new String[]{"firstName", "lastName"})
                .fieldSetMapper(beanWrapperFieldSetMapper)
                .build();
    }

    public FlatFileItemReader<Menu> reader2() {
        BeanWrapperFieldSetMapper<Menu> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Menu.class);

        String[] names = {"nama1","nama2"};

        return new FlatFileItemReaderBuilder<Menu>()
                .name("orangItemReader")
                .resource(new ClassPathResource("sample-data3.csv"))
                .delimited()
                .names(names)
                .fieldSetMapper(beanWrapperFieldSetMapper)
                .build();
    }

    public MultiResourceItemReader<Person> multiResourceItemReader() {

        BeanWrapperFieldSetMapper<Person> beanWrapperFieldSetMapper = new BeanWrapperFieldSetMapper<>();
        beanWrapperFieldSetMapper.setTargetType(Person.class);

        return new MultiResourceItemReaderBuilder<Person>()
                .name("multiResourceItemReaderBuilder")
                .resources(inputResources)
                .delegate(reader())
                .build();
    }
}
