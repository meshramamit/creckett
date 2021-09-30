package com.creckett.writer.csv;

import java.io.FileWriter;
import java.io.IOException;

import com.creckett.writer.RecordWriter;
import com.csvreader.CsvWriter;

public class CsvRecordWriter implements RecordWriter {

	CsvWriter csvWriter;
	
	public CsvRecordWriter(String fileName) throws IOException{
		this.csvWriter =new CsvWriter(new FileWriter(fileName),',');
	}
	
	public void flush() {
		csvWriter.flush();
		
	}
		
	@Override
	public void close() {
		csvWriter.close();
		
	}

	@Override
	public void writeRecord(String[] values) throws IOException {
		csvWriter.writeRecord(values);
		
	}

}
