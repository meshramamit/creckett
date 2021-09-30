package com.creckett.logger;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;

public class CreckettLogger {

	private Logger debugLogger;
	private Logger errorLogger;
	private Logger infoLogger;
	private static CreckettLogger creckettLogger = new CreckettLogger();

	private CreckettLogger() {

	}

	public static CreckettLogger getInstance() {
		if (creckettLogger != null) {
			return creckettLogger;
		}
		return null;
	}

	public void debug(String debugMsg) {
		if (debugLogger != null) {
			debugLogger.debug(debugMsg);
		}
	}
	
	public void debug(String place, String debugMsg) {
		if (debugLogger != null) {
			debugLogger.debug("[" + place + "] " + debugMsg);
		}
	}

	public void error(String errorMsg) {
		if (debugLogger != null) {
			debugLogger.error(errorMsg);
		}
		if (infoLogger != null) {
			infoLogger.error(errorMsg);
		}
		if (errorLogger != null) {
			errorLogger.error(errorMsg);
		}
	}

	public void error(String place, String errorMsg) {
		errorMsg = "[" + place + "] " + errorMsg;
		if (debugLogger != null) {
			debugLogger.error(errorMsg);
		}
		if (infoLogger != null) {
			infoLogger.error(errorMsg);
		}
		if (errorLogger != null) {
			errorLogger.error(errorMsg);
		}
	}

	public void error(String errorMsg, Throwable t) {
		
		if (debugLogger != null) {
			debugLogger.error(errorMsg,t);
		}
		if (infoLogger != null) {
			infoLogger.error(errorMsg,t);
		}
		if (errorLogger != null) {
			errorLogger.error(errorMsg,t);
		}
	}
	
	public void info(String infoMsg) {
		if (infoLogger != null) {
			infoLogger.info(infoMsg);
		}
		if (debugLogger != null) {
			debugLogger.info(infoMsg);
		}
	}
	
	public void info(String place, String infoMsg) {
		infoMsg = "[" + place + "] " + infoMsg;
		if (infoLogger != null) {
			infoLogger.info(infoMsg);
		}
		if (debugLogger != null) {
			debugLogger.info(infoMsg);
		}
	}

	public void fatal(String fatalMsg) {
		if (debugLogger != null) {
			debugLogger.fatal(fatalMsg);
		}
		if (infoLogger != null) {
			infoLogger.fatal(fatalMsg);
		}
		if (errorLogger != null) {
			errorLogger.fatal(fatalMsg);
		}
	}
	
	public void fatal(String place, String fatalMsg) {
		fatalMsg = "[" + place + "] " +  fatalMsg;
		if (debugLogger != null) {
			debugLogger.fatal(fatalMsg);
		}
		if (infoLogger != null) {
			infoLogger.fatal(fatalMsg);
		}
		if (errorLogger != null) {
			errorLogger.fatal(fatalMsg);
		}
	}

	public void warn(String warnMsg) {
		if (debugLogger != null) {
			debugLogger.warn(warnMsg);
		}
		if (infoLogger != null) {
			infoLogger.warn(warnMsg);
		}
		if (errorLogger != null) {
			errorLogger.warn(warnMsg);
		}
	}
	
	public void warn(String place, String warnMsg) {
		warnMsg = "[" + place + "] " + warnMsg;
		if (debugLogger != null) {
			debugLogger.warn(warnMsg);
		}
		if (infoLogger != null) {
			infoLogger.warn(warnMsg);
		}
		if (errorLogger != null) {
			errorLogger.warn(warnMsg);
		}
	}

	public void beforeMethod(JoinPoint joinPoint) {
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("Calling method : "
				+ joinPoint.getSignature().getClass() + "#");
		stringBuffer.append(joinPoint.getSignature().getName()
				+ " With arguments : ");
		if (joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++)
				stringBuffer.append(joinPoint.getArgs()[i] + ",");
		}
		this.debug(stringBuffer.toString());
	}

	public void afterMethod(JoinPoint joinPoint) {
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append("Returning from method : "
				+ joinPoint.getSignature().getClass() + "#");
		stringBuffer.append(joinPoint.getSignature().getName()
				+ " With arguments : ");
		if (joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++)
				stringBuffer.append(joinPoint.getArgs()[i] + ",");
		}
		this.debug(stringBuffer.toString());
	}

	public void afterThrowingMethod(JoinPoint joinPoint, Exception ex) {
		StringBuffer stringBuffer = new StringBuffer("");
		stringBuffer.append(ex.getClass().getName()
				+ " Exception reported while Returning from method : ");
		stringBuffer.append(joinPoint.getSignature().getClass() + "#"
				+ joinPoint.getSignature().getName() + " With arguments : ");
		if (joinPoint.getArgs().length > 0) {
			for (int i = 0; i < joinPoint.getArgs().length; i++)
				stringBuffer.append(joinPoint.getArgs()[i] + ",");
		}
		this.error(stringBuffer.toString());
	}

	public Logger getDebugLogger() {
		return debugLogger;
	}

	public void setDebugLogger(Logger debugLogger) {
		this.debugLogger = debugLogger;
	}

	public Logger getErrorLogger() {
		return errorLogger;
	}

	public void setErrorLogger(Logger errorLogger) {
		this.errorLogger = errorLogger;
	}

	public Logger getInfoLogger() {
		return infoLogger;
	}

	public void setInfoLogger(Logger infoLogger) {
		this.infoLogger = infoLogger;
	}
}
