package com.myshop.products.common.logging;

import java.io.File;
import java.io.IOException;

import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class LogBackConfigLoader {

	
	public LogBackConfigLoader(String path)  throws IOException, JoranException{
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		 
		File configFile = new File(path);
		if(!configFile.exists()){
			throw new IOException("Logback Config File does not exists = " + path);
		}else{
			if(!configFile.isFile()) {
				throw new IOException("Logback Config File does exists, but does not reference a file");
			} else {
				if(!configFile.canRead()) {
					throw new IOException("Can't read Logback Config File - " + path);
				}else{
					JoranConfigurator configurator = new JoranConfigurator();
					configurator.setContext(lc);
					lc.reset();
					configurator.doConfigure(path);
				}
			}	
		}
	}
}
