package com.onpier.task.dataimport;

import org.springframework.batch.item.file.separator.SimpleRecordSeparatorPolicy;

/**
 * @author Udayshree
 * 
 *         It Ignore the Blank cells during the csv import
 *
 */
public class IgnoreBlankCells extends SimpleRecordSeparatorPolicy {

	@Override
	public boolean isEndOfRecord(final String line) {
		return line.trim().length() != 0 && super.isEndOfRecord(line);
	}

	@Override
	public String postProcess(final String rec) {
		if (rec == null || rec.trim().length() == 0) {
			return null;
		}
		return super.postProcess(rec);
	}

}