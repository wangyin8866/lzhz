package com.example.user.liangzi.utils;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

	private static String SDPATH;

	public String getSDPATH() {
		return SDPATH;
	}

	public FileUtils() {
		// 得到当前外部存储设备的目录
		// /SDCARD
		SDPATH = Environment.getExternalStorageDirectory() + "/";
	}

	/**
	 * 在SD卡上创建文件
	 * 
	 * @throws IOException
	 */
	public File creatSDFile(String fileName) throws IOException {
		File file = new File(SDPATH + fileName);
		file.createNewFile();
		return file;
	}

	/**
	 * 在SD卡上创建目录
	 * 
	 * @param dirName
	 */
	public File creatSDDir(String dirName) {
		File dir = new File(SDPATH + dirName);
		dir.mkdirs();
		Log.d("创建dir:" , dir.getAbsolutePath());
		return dir;
	}

	/**
	 * 判断SD卡上的文件夹是否存在
	 */
	public boolean isFileExist(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.exists();
	}

	public boolean deleteFile(String fileName) {
		File file = new File(SDPATH + fileName);
		return file.delete();
	}

	/**
	 * 将一个InputStream里面的数据写入到SD卡中
	 */
	public File write2SDFromInput(String path, String fileName,
			InputStream input, long apkLengt) {
		File file = null;
		FileOutputStream output = null;
		try {
			creatSDDir(path);
			file = creatSDFile(path + fileName);
			output = new FileOutputStream(file);
			byte buffer[] = new byte[1024];
			int ch = -1;
			float count = 0;
			while ((ch = input.read(buffer)) != -1) {
				output.write(buffer, 0, ch);
				count += ch;
				dialogoption.showData(1, (int) (count * 100 / apkLengt), null);
			}
			output.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				output.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return file;
	}

	/**
	 * 回调监听
	 */
	public DialogOption dialogoption;

	public void setDialogoption(DialogOption dialogoption) {
		this.dialogoption = dialogoption;
	}

	/**
	 * 删除某地址下的所有文件
	 */
	public static boolean deleteFiles(String dirPath) {
		try {
			File extDir = Environment.getExternalStorageDirectory();
			String dirName = extDir.getAbsolutePath() + dirPath;
			File newExtDir = new File(dirName);
			if (newExtDir.exists()) {
				File[] fls = newExtDir.listFiles();
				for (File f : fls) {
					f.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	/**
	 * 监听事件
	 * 
	 * @author wangwx
	 * 
	 */
	public interface DialogOption {
		public void showData(int flag, int progress, String error);
	}
}