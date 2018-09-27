package com.hs.datatrans.utils.dimeng.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {
    private static final Logger logs = LogManager.getLogger(FileUtil.class);

    public FileUtil() {
    }

    public static void saveFile(File file, String filepath) {
        try {
            saveFile((InputStream)(new FileInputStream(file)), filepath);
        } catch (Exception var3) {
            logs.error("FileUtil.saveFile  File not found: " + file + ". ", var3);
        }

    }

    public static void saveFile(InputStream ins, String filepath) {
        BufferedInputStream in = null;
        BufferedOutputStream out = null;

        try {
            int pos = filepath.lastIndexOf(System.getProperty("file.separator"));
            File folder = new File(filepath.substring(0, pos));
            if (!folder.exists()) {
                folder.mkdirs();
            }

            in = new BufferedInputStream(ins);
            out = new BufferedOutputStream(new FileOutputStream(filepath));
            byte[] b = new byte[8096];

            int i;
            while((i = in.read(b)) != -1) {
                out.write(b, 0, i);
            }

            out.flush();
        } catch (IOException var20) {
            logs.error("FileUtil.saveFile  IOException: " + filepath + ": ", var20);
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException var19) {
                logs.error("FileUtil.saveFile  IOException: " + filepath + ": ", var19);
            }

            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException var18) {
                logs.error("FileUtil.saveFile  IOException: " + filepath + ": ", var18);
            }

        }

    }

    public static String saveFile(File file, String type, String filename) {
        StringBuffer bufStr = new StringBuffer();
        String savePath = bufStr.append("/fileStore/").append(getFileDirName(type)).append(filename).toString();
        String filepath = FrameworkConfigurer.getProperties("fileStore.home") + savePath.toString().replace("/fileStore/", "").replace("/", System.getProperty("file.separator"));
        saveFile(file, filepath);
        logs.info("上传文件至>>>>>>" + filepath);
        return savePath;
    }

    private static String getFileDirName(String typeName) {
        StringBuffer bufStr = new StringBuffer();
        Date nowTime = new Date(System.currentTimeMillis());
        bufStr.append(DateUtils.getYear(nowTime)).append("/");
        bufStr.append(DateUtils.getMonth(nowTime)).append("/");
        bufStr.append(DateUtils.getDay(nowTime)).append("/");
        bufStr.append(DateUtils.getHour(nowTime)).append("/");
        bufStr.append(typeName).append("/");
        return bufStr.toString();
    }

    public static String saveFile(InputStream ins, String type, String filename) {
        StringBuffer bufStr = new StringBuffer();
        String savePath = bufStr.append("/fileStore/").append(getFileDirName(type)).append(filename).toString();
        String filepath = FrameworkConfigurer.getProperties("fileStore.home") + savePath.toString().replace("/fileStore/", "").replace("/", System.getProperty("file.separator"));
        saveFile(ins, filepath);
        logs.info("上传文件至>>>>>>" + filepath);
        return savePath;
    }

    public static String readFromFile(String filePath) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            String line = reader.readLine();
            if (line != null) {
                sb.append(line);
            }

            while((line = reader.readLine()) != null) {
                sb.append("\n" + line);
            }
        } catch (IOException var12) {
            logs.error("FileUtil.readFromFile  IOException: " + filePath + ": ", var12);
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (Exception var11) {
                logs.error("FileUtil.readFromFile  IOException: " + filePath + ": ", var11);
            }

        }

        return sb.toString();
    }

    public static void writeToFile(String content, String filePath) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "UTF-8"));
            writer.write(content);
            writer.flush();
        } catch (IOException var12) {
            logs.error("FileUtil.writeToFile  IOException: " + filePath + ": ", var12);
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception var11) {
                logs.error("FileUtil.writeToFile  IOException: " + filePath + ": ", var11);
            }

        }

    }

    public static void downloadFile(HttpServletResponse response, String filename, File file) {
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;

        try {
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes("GBK"), "ISO-8859-1"));
            response.setContentType("application/octet-stream");
            bos = new BufferedOutputStream(response.getOutputStream());
            bis = new BufferedInputStream(new FileInputStream(file));
            byte[] buffer = new byte[8192];

            int i;
            while((i = bis.read(buffer)) != -1) {
                bos.write(buffer, 0, i);
            }

            bos.flush();
        } catch (Exception var19) {
            logs.error("FileUtil.downloadFile  Exception: " + filename + ": ", var19);
        } finally {
            try {
                if (bos != null) {
                    bos.close();
                }
            } catch (IOException var18) {
                logs.error("FileUtil.downloadFile  Exception: " + filename + ": ", var18);
            }

            try {
                if (bis != null) {
                    bis.close();
                }
            } catch (IOException var17) {
                logs.error("FileUtil.downloadFile  Exception: " + filename + ": ", var17);
            }

        }

    }

    public static void xcopy(String source, String dest) {
        File folder = new File(source);
        File[] files = folder.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.indexOf(".svn") == -1;
            }
        });
        if (files != null && files.length != 0) {
            File[] var4 = files;
            int var5 = files.length;

            for(int var6 = 0; var6 < var5; ++var6) {
                File file = var4[var6];
                if (file.isDirectory()) {
                    xcopy(source + System.getProperty("file.separator") + file.getName(), dest + System.getProperty("file.separator") + file.getName());
                } else {
                    copy(source + System.getProperty("file.separator") + file.getName(), dest + System.getProperty("file.separator") + file.getName());
                }
            }

        }
    }

    public static void copy(String srcFile, String destFile) {
        logs.info(srcFile + "\t拷贝一个源文件到目标文件\t" + destFile);
        int pos = destFile.lastIndexOf("/");
        if (pos == -1) {
            pos = destFile.lastIndexOf("\\");
        }

        if (pos != -1) {
            File destFolder = new File(destFile.substring(0, pos));
            if (!destFolder.exists()) {
                boolean success = destFolder.mkdirs();
                if (!success) {
                    return;
                }
            }

            FileInputStream fis = null;
            FileOutputStream fos = null;

            try {
                fis = new FileInputStream(srcFile);
                fos = new FileOutputStream(destFile);
                byte[] b = new byte[8192];

                int i;
                while((i = fis.read(b)) != -1) {
                    fos.write(b, 0, i);
                }

                fos.flush();
            } catch (Exception var20) {
                logs.error("FileUtil.copy  Exception: ", var20);
            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                } catch (Exception var19) {
                    logs.error("FileUtil.copy  Exception: ", var19);
                }

                try {
                    if (fis != null) {
                        fis.close();
                    }
                } catch (Exception var18) {
                    logs.error("FileUtil.copy  Exception: ", var18);
                }

            }

        }
    }

    public static void zip(String zipFileName, String srcFolderName) throws Exception {
        ZipOutputStream out = new ZipOutputStream(new FileOutputStream(zipFileName));
        Throwable var3 = null;

        try {
            File inputFile = new File(srcFolderName);
            zip(out, inputFile, "");
            logs.info("zip done");
        } catch (Throwable var12) {
            var3 = var12;
            throw var12;
        } finally {
            if (out != null) {
                if (var3 != null) {
                    try {
                        out.close();
                    } catch (Throwable var11) {
                        var3.addSuppressed(var11);
                    }
                } else {
                    out.close();
                }
            }

        }

    }

    private static void zip(ZipOutputStream out, File f, String base) throws Exception {
        if (f.isDirectory()) {
            File[] fl = f.listFiles(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.indexOf(".svn") == -1 && !name.endsWith(".class");
                }
            });
            out.putNextEntry(new ZipEntry(base + System.getProperty("file.separator")));
            base = base.length() == 0 ? "" : base + System.getProperty("file.separator");
            if (fl == null || fl.length == 0) {
                return;
            }

            for(int i = 0; i < fl.length; ++i) {
                zip(out, fl[i], base + fl[i].getName());
            }
        } else {
            out.putNextEntry(new ZipEntry(base));
            FileInputStream in = new FileInputStream(f);
            Throwable var4 = null;

            try {
                byte[] b = new byte[8192];
                logs.info(base);

                int i;
                while((i = in.read(b)) != -1) {
                    out.write(b, 0, i);
                }
            } catch (Throwable var14) {
                var4 = var14;
                throw var14;
            } finally {
                if (in != null) {
                    if (var4 != null) {
                        try {
                            in.close();
                        } catch (Throwable var13) {
                            var4.addSuppressed(var13);
                        }
                    } else {
                        in.close();
                    }
                }

            }
        }

    }

    private static String bytesToHexString(byte[] src) {
        if (src != null && src.length > 0) {
            StringBuilder stringBuilder = new StringBuilder();
            String hv = "";

            for(int i = 0; i < src.length; ++i) {
                int v = src[i] & 255;
                hv = Integer.toHexString(v);
                if (hv.length() < 2) {
                    stringBuilder.append(0);
                }

                stringBuilder.append(hv);
            }

            return stringBuilder.toString();
        } else {
            return null;
        }
    }

    private static String getFileContent(InputStream inputStream) throws IOException {
        byte[] b = new byte[64];

        try {
            inputStream.read(b, 0, b.length);
        } catch (IOException var10) {
            logs.error(var10, var10);
            throw var10;
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException var9) {
                    logs.error(var9, var9);
                    throw var9;
                }
            }

        }

        return bytesToHexString(b);
    }

    public static FileType getType(InputStream inputStream, String type) throws IOException {
        String fileHead = getFileContent(inputStream);
        if (fileHead != null && fileHead.length() != 0) {
            fileHead = fileHead.toUpperCase();
            FileType[] var3 = FileType.values();
            int var4 = var3.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                FileType fileType = var3[var5];
                if (type.equalsIgnoreCase(fileType.name()) && fileHead.startsWith(fileType.getValue())) {
                    return fileType;
                }
            }

            return null;
        } else {
            return null;
        }
    }

    public static void main(String[] args) {
        System.out.println("Line separator is: " + System.getProperty("file.separator"));
        System.out.println("Path separator is: " + System.getProperty("path.separator"));
    }
}
