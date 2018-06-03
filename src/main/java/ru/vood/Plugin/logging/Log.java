package ru.vood.Plugin.logging;

import ru.vood.Plugin.applicationConst.AppConst;

import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {
    private static Level defaultlogLevel = AppConst.DEFAULT_LOG_LEVEL;
    private static Logger logger = Logger.getLogger("L");
    private static FileHandler fh;

    private static Log ourInstance = new Log();
    private static boolean success = true;

    private static Class currentClass;

    private static AppConst.ModeLogging modeLogging = AppConst.defaultModeLogging;

   /* private static Log getInstance() {
        return ourInstance;
    }*/

    private Log() {
        File fDir = new File(AppConst.LOG_FILE_DIR);

        if (!fDir.exists()) {
            success = fDir.mkdir();
            if (!success) {
                System.out.println(("Не удалось создать директорию для логгирования " + fDir.getPath()
                        /*AppConst.LOG_FILE*/ + " логирование не возможно."));
            }
        }
        //File fFile = new File(fDir.getPath()+"\\"+"log.log");
        calcFileHandler(modeLogging);
    }

    private Log(Class cl) {
        currentClass = cl;
        ourInstance = new Log();
    }

    public static Log getLogger(Class cl) {
        if (!cl.equals(currentClass) || ourInstance == null) {
            currentClass = cl;
            ourInstance = new Log(cl);
        }
        return ourInstance;
    }

    private void calcFileHandler(AppConst.ModeLogging modeLogging) {
        String patternLogFilename = null;
        if (currentClass == null || modeLogging == AppConst.ModeLogging.COLLECTIVEL_FOR_ALL_CLASS) {
            patternLogFilename = AppConst.DEFUALT_LOG_FILE_NAME;
        } else if (modeLogging == AppConst.ModeLogging.SINGLY_FOR_EACH_CLASS) {
            patternLogFilename = currentClass.getName() + ".log";
        }/* else {
            new ErrWin(null, "Ошибка Логгирования", true, "Ошибка Логгирования", null);
        }*/

        if (success && patternLogFilename != null) {
            try {
                fh = new FileHandler(AppConst.LOG_FILE_DIR + "\\" + patternLogFilename);
                logger.addHandler(fh);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void putToLog(String msg, Level level, Exception e) {
        boolean isPutToLog = false;

        if (ourInstance == null) {
            ourInstance = new Log();
        }
        if (success) {
            StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
            StringBuffer messages = new StringBuffer();

            for (int i = 0; i < stackTraceElements.length; i++) {
                if (stackTraceElements[i].getMethodName().equals("putToLog") == true) {
                    isPutToLog = true;
                }
                if (isPutToLog == true && stackTraceElements[i].getMethodName().equals("putToLog") == false) {
                    msg = stackTraceElements[3].toString() + " " + msg;
                    break;
                }
            }
            logger.log(level, msg, e);
        }
    }

    public void putToLog(String msg, Level level) {
        putToLog(msg, level, null);
    }

    public void putToLog(String msg) {
        putToLog(msg, defaultlogLevel);
    }

    public void putToLog(String msg, Exception e) {
        putToLog(msg, defaultlogLevel, e);
    }


}
