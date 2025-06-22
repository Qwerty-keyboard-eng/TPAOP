package aspects;


import annotations.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
public class LoggingAspect {

    private static final String LOG_FILE = "activity.log"; // Nombre del archivo de log
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    @Around("@annotation(logAnnotation)")
    public Object logMethodCall(ProceedingJoinPoint joinPoint, Log logAnnotation) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getName();
        Object[] args = joinPoint.getArgs();

        String params = formatParameters(args);
        String timestamp = LocalDateTime.now().format(DATE_TIME_FORMATTER);

        String logEntry = String.format("\"%s\", \"%s\", \"%s\"", methodName, params, timestamp);

        writeToLogFile(logEntry);

        return joinPoint.proceed();
    }

    private String formatParameters(Object[] args) {
        if (args == null || args.length == 0) {
            return "sin parametros";
        }
        return Arrays.stream(args)
                .map(this::safeToString)
                .collect(Collectors.joining("|"));
    }

    private String safeToString(Object obj) {
        return obj != null ? obj.toString() : "null";
    }

    private void writeToLogFile(String logEntry) {
        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(logEntry);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }
}