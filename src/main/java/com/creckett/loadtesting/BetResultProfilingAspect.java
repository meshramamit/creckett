package com.creckett.loadtesting;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import org.apache.commons.lang.time.StopWatch;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.beans.factory.InitializingBean;

import com.creckett.writer.RecordWriter;
import com.creckett.writer.csv.CsvRecordWriter;

public class BetResultProfilingAspect implements InitializingBean {

	private RecordWriter csvWriter;
	
	private int over;
	
	private TimeZone timeZone = TimeZone.getTimeZone("America/New_York");
	
	private String responsePushTime;
	
	private String resultCalculationTime;

	public Object invoke(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		Object val = pjp.proceed();
		stopWatch.stop();
		resultCalculationTime = String.valueOf(stopWatch.getTime());
		return val;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		String today = "betResultProfiling-" + new SimpleDateFormat("dd-MM-yyyy").format(Calendar
				.getInstance().getTime()) + ".csv";
		File file = new File("d:/" + today);
		if (file.exists()) {
			file.delete();
		}
		
		file.createNewFile();
		csvWriter = new CsvRecordWriter(file.getAbsolutePath());
		csvWriter.writeRecord(new String[]{"Over", " Time taken for calculation (ms)", " Response Push Time"});

	}
	
	public void beforeBetResultPush(JoinPoint joinPoint){
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss.SSS");
		simpleDateFormat.setTimeZone(timeZone);
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(System.currentTimeMillis());
		responsePushTime = simpleDateFormat.format(calendar.getTime());
	}
	
	public void afterBetResultPush() throws IOException{
		csvWriter.writeRecord(new String[]{String.valueOf(++over),String.valueOf(resultCalculationTime),responsePushTime});
		csvWriter.flush();
	}

}
