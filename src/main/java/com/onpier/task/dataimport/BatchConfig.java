package com.onpier.task.dataimport;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.onpier.task.entity.Books;
import com.onpier.task.entity.Borrowed;
import com.onpier.task.entity.Users;
import com.onpier.task.util.Constants;

/**
 * @author Udayshree
 * 
 *         BatchConfig to read the csv files when the application is started
 *
 */
@Configuration
@EnableBatchProcessing
public class BatchConfig {
	private static final String READ_CSV_FILES_JOB = "readCSVFilesJob";

	private static final int DEAFULT_CHUNK = 150;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Value("file:src/main/resources/data/books.csv")
	private Resource[] bookResources;

	@Value("file:src/main/resources/data/user.csv")
	private Resource[] userResources;

	@Value("file:src/main/resources/data/borrowed.csv")
	private Resource[] borrowedResources;

	@Bean
	public Job readCSVFilesJob(JobBuilderFactory jobBuilderFactory) {
		return jobBuilderFactory.get(READ_CSV_FILES_JOB).incrementer(new RunIdIncrementer()).start(booksStep())
				.next(usersStep()).next(borrowedStep()).build();
	}

	@Bean
	public Step booksStep() {
		return stepBuilderFactory.get(Constants.BOOKS).<Books, Books>chunk(DEAFULT_CHUNK).reader(bookReader())
				.writer(dbWriter()).build();
	}

	@Bean
	public Step usersStep() {
		return stepBuilderFactory.get(Constants.USERS).<Users, Users>chunk(DEAFULT_CHUNK).reader(userReader())
				.writer(dbWriter()).build();
	}

	@Bean
	public Step borrowedStep() {
		return stepBuilderFactory.get(Constants.BORROWED).<Borrowed, Borrowed>chunk(DEAFULT_CHUNK)
				.reader(borrowedReader()).writer(dbWriter()).build();
	}

	@Bean
	public MultiResourceItemReader<Books> bookReader() {
		MultiResourceItemReader<Books> resourceItemReader = new MultiResourceItemReader<>();
		resourceItemReader.setResources(bookResources);
		resourceItemReader.setDelegate(bookReaderConfig());
		return resourceItemReader;
	}

	@Bean
	public MultiResourceItemReader<Users> userReader() {
		MultiResourceItemReader<Users> resourceItemReader = new MultiResourceItemReader<>();
		resourceItemReader.setResources(userResources);
		resourceItemReader.setDelegate(userReaderConfig());
		return resourceItemReader;
	}

	@Bean
	public MultiResourceItemReader<Borrowed> borrowedReader() {
		MultiResourceItemReader<Borrowed> resourceItemReader = new MultiResourceItemReader<>();
		resourceItemReader.setResources(borrowedResources);
		resourceItemReader.setDelegate(borrowedReaderConfig());
		return resourceItemReader;
	}

	@Bean
	public FlatFileItemReader<Books> bookReaderConfig() {
		FlatFileItemReader<Books> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setRecordSeparatorPolicy(new IgnoreBlankCells());
		CommonDefaultLineMapper<Books> lineMapper = new CommonDefaultLineMapper<>(Constants.BOOKS_COLUMNS, Books.class);
		reader.setLineMapper(lineMapper);
		return reader;
	}

	@Bean
	public FlatFileItemReader<Users> userReaderConfig() {
		FlatFileItemReader<Users> reader = new FlatFileItemReader<>();
		reader.setLinesToSkip(1);
		reader.setRecordSeparatorPolicy(new IgnoreBlankCells());
		CommonDefaultLineMapper<Users> lineMapper = new CommonDefaultLineMapper<>(Constants.USERS_COLUMNS, Users.class);
		reader.setLineMapper(lineMapper);
		return reader;
	}

	@Bean
	public FlatFileItemReader<Borrowed> borrowedReaderConfig() {
		FlatFileItemReader<Borrowed> reader = new FlatFileItemReader<>();

		reader.setLinesToSkip(1);
		reader.setRecordSeparatorPolicy(new IgnoreBlankCells());
		CommonDefaultLineMapper<Borrowed> lineMapper = new CommonDefaultLineMapper<>(Constants.BORROWED_COLUMNS,
				Borrowed.class);
		reader.setLineMapper(lineMapper);
		return reader;

	}

	@Bean
	public DatabaseWriter<Object> dbWriter() {
		return new DatabaseWriter<>();
	}
}