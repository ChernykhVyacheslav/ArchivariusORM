package org.softserve.kh47.logger;

import java.util.Objects;

public class ProgramLogger {
    private static ProgramLogger programLogger;
    private static String logFile = "This is log file.\n";

    private ProgramLogger(){}
    public static ProgramLogger getProgramLogger(){
        return Objects.isNull(programLogger) ? programLogger = new ProgramLogger() : programLogger;
    }

    public void addLogInfo(String logInfo) {
        logFile += logInfo + "\n";
    }

    public void showLogFile(){
        System.out.println(logFile);
    }
}