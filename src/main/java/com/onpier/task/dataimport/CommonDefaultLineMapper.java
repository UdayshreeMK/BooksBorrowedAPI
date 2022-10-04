package com.onpier.task.dataimport;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;

/**
 * @author Udayshree
 * 
 *         To set the columns,mappers from csv import
 * @param <T>: data Object
 */
public class CommonDefaultLineMapper<T> extends DefaultLineMapper<T> {
	private String[] columns;
	private Class<T> targetType;
	private LineTokenizer lineTokenizer;
	private FieldSetMapper<T> fieldSetMapper;

	public CommonDefaultLineMapper(String[] columns, Class<T> type) {
		this.columns = columns;
		this.targetType = type;
	}

	@Override
	public T mapLine(String line, int lineNumber) throws Exception {
		DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
		delimitedLineTokenizer.setNames(columns);
		setLineTokenizer(delimitedLineTokenizer);
		setFieldSetMapper(new CommonBeanWFSetMapper<>(targetType));
		return this.fieldSetMapper.mapFieldSet(this.lineTokenizer.tokenize(line));
	}

	public LineTokenizer getLineTokenizer() {
		return lineTokenizer;
	}

	@Override
	public void setLineTokenizer(LineTokenizer lineTokenizer) {
		super.setLineTokenizer(lineTokenizer);
		this.lineTokenizer = lineTokenizer;
	}

	public FieldSetMapper<T> getFieldSetMapper() {
		return fieldSetMapper;
	}

	@Override
	public void setFieldSetMapper(FieldSetMapper<T> fieldSetMapper) {
		super.setFieldSetMapper(fieldSetMapper);
		this.fieldSetMapper = fieldSetMapper;
	}
}
