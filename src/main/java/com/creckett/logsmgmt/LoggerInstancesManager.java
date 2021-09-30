/**
 * Copyright 2011. All rights reserved. No part of this program may be 
 * photocopied reproduced or translated to another program language without prior written consent 
 * of author(s).
 *
 * FILE         : LoggerInstancesManager.java
 *
 * PACKAGE      : com.creckett.logsmgmt
 *
 * AUTHOR(S)    : dps - shah_d_p@yahoo.com
 *
 * VERSION      : 1.0
 *
 * REVISION LOG :
 *
 *    Date          By         Version  TicketId        Description
 *    ------------  ---------  -------  --------  --------------------------------------------------
 *    Apr 12, 2011  dps      1.0      NA        Created first cut of code.
 */
package com.creckett.logsmgmt;

import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;


/**
 * LoggerInstancesManager class manages the life cycle of loggers and allows configuration of
 * customized LOG4J loggers by an application.
 *
 * @author dps [shah_d_p@yahoo.com]
 * @version 1.0
 */
public final class LoggerInstancesManager 
{
    /**
     * Holds the logger factory that has been set up for use, if any.
     */
    private static LoggerFactory currentFactory = null;

    /**
     * LoggerInstancesManager constructor is hidden to ensure that this class cannot be
     * instantiated.
     */
    private LoggerInstancesManager()
    {
        super();
    }

    /**
     * Retrieves the Logger Factory being used for creation of loggers.
     *
     * @return Returns Logger Factory being used for creation of loggers. Null value implies
     * default LOG4J Logger factory is being used.
     */
    public static LoggerFactory getCurrentFactory()
    {
        return LoggerInstancesManager.currentFactory;
    }

    /**
     * Returns a logger for the given class object.
     *
     * @param clazz Class object for which associated logger is to be retrieved
     *
     * @return Logger associated with the given class object
     */
    public static Logger getLogger(final Class clazz)
    {
        return LoggerInstancesManager.getLogger(clazz.getName());
    }

    /**
     * Returns a logger for the given class object. Logger instance is created, if required, using
     * the logger factory instance provided. If logger factory instance is null then default logger
     * factory is used.
     *
     * @param clazz Class object for which associated logger is to be retrieved
     *
     * @param loggerFactory Logger Factory to use
     *
     * @return Logger associated with the given class object
     */
    public static Logger getLogger(final Class clazz, final LoggerFactory loggerFactory)
    {
        Logger result = null;

        if (loggerFactory != null)
        {
            result = Logger.getLogger(clazz.getName(), loggerFactory);
        }
        else
        {
            result = LoggerInstancesManager.getLogger(clazz);
        }
        return result;
    }

    /**
     * Returns a logger for the given logger name.
     *
     * @param name Name of the logger to be retrieved
     *
     * @return Logger associated with the given name
     */
    public static Logger getLogger(final String name)
    {
        Logger result = null;

        if (LoggerInstancesManager.currentFactory != null)
        {
            result = Logger.getLogger(name, LoggerInstancesManager.currentFactory);
        }
        else
        {
            result = Logger.getLogger(name);
        }

        return result;
    }

    /**
     * Returns a logger for the given logger name. Logger instance is created, if required, using
     * the logger factory instance provided. If logger factory instance is null then default logger
     * factory is used.
     *
     * @param name Name of the logger to be retrieved
     *
     * @param loggerFactory Logger Factory to use
     *
     * @return Logger associated with the given name
     */
    public static Logger getLogger(final String name, final LoggerFactory loggerFactory)
    {
        Logger result = null;

        if (loggerFactory != null)
        {
            result = Logger.getLogger(name, loggerFactory);
        }
        else
        {
            result = Logger.getLogger(name);
        }

        return result;
    }

    /**
     * Sets the Logger Factory to use for creation of Loggers.
     *
     * @param value Value to set. If set to null, implies LOG4J default LoggerFactory will be used
     * from here on
     */
    public static void setCurrentFactory(final LoggerFactory value)
    {
        LoggerInstancesManager.currentFactory = value;
    }
}
