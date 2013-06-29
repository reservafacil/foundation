package com.brazoft.foundation.util;

import java.io.IOException;
import java.io.InputStream;

import com.brazoft.foundation.commons.Validator;
import com.brazoft.foundation.io.IOHandler;

/**
 * @author Anderson Braz - anderson.braz@brazoft.com.br
 */
public class OSUtil {

  /**
   * @return boolean
   */
  public static boolean isLinux() {
    if (OSUtil.getOSName().contains("Linux")) {
      return true;
    }

    return false;
  }

  /**
   * @return boolean
   */
  public static boolean isWindows() {
    if (OSUtil.getOSName().contains("Windows")) {
      return true;
    }

    return false;
  }

  /**
   * @return Returns OS Name
   */
  public static String getOSName() {
    return OSUtil.getProperty("os.name");
  }

  /**
   * @return Returns user home directory
   */
  public static String getUserHome() {
    return OSUtil.getProperty("user.home");
  }

  /**
   * @param command
   * @return Returns byte[]
   * @throws IOException
   * @throws InterruptedException
   */
  public static ProcessResult execute(String command) throws IOException, InterruptedException {
    return OSUtil.execute(command, false);
  }

  /**
   * @param command
   * @param ignoreError
   * @param args
   * @return Returns byte[]
   * @throws IOException
   * @throws InterruptedException
   */
  public static ProcessResult execute(String command, boolean ignoreError, Object... args)
      throws IOException, InterruptedException {
    Process process;
    byte[] info;
    String error;
    int exitValue;

    process = Runtime.getRuntime().exec(command);

    if (!ignoreError) {
      error = Converter.toString(OSUtil.handle(process.getErrorStream()));
      if (!Validator.isEmptyOrNull(error)) {
        throw new IOException(error);
      }
    }

    info = OSUtil.handle(process.getInputStream());

    if (!Validator.isEmptyOrNull(args)) {
      for (Object arg : args) {
        IOHandler.write(process.getOutputStream(), arg.toString().getBytes());
      }
    }

    exitValue = process.waitFor();

    return new ProcessResult(info, exitValue, process);
  }

  private static byte[] handle(InputStream input) {
    try {
      return IOHandler.read(input);
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  private static String getProperty(String key) {
    return System.getProperties().get(key).toString();
  }

  /**
   * @author Anderson Braz - anderson.braz@brazoft.com.br
   */
  public static class ProcessResult {

    private byte[] info;

    private int exitValue;

    private Process process;

    /**
     * @param info
     * @param exitValue
     * @param process
     */
    ProcessResult(byte[] info, int exitValue, Process process) {
      super();
      this.info = info;
      this.exitValue = exitValue;
      this.process = process;
    }

    /**
     * @return the info
     */
    public byte[] getInfo() {
      return info;
    }

    /**
     * @return the exitValue
     */
    public int getExitValue() {
      return exitValue;
    }

    /**
     * @return the process
     */
    public Process getProcess() {
      return process;
    }
  }
}