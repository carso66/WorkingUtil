package carso.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExportFile {

	// 检索目录
	private static String srcAddress = "";

	// 导出目录
	private static String desAddress = "";

	// 项目名称
	private static String projectName = "ApiService";

	public static void main(String[] args) {
		System.out.println("----Start----");
		srcAddress = Util.getOneLineFromFile("resource/srcAddress.properties");
		desAddress = Util.getOneLineFromFile("resource/desAddress.properties");
		
		//读取目标文件列表
		File fileList = new File("resource/fileList.properties");

		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileList), Util.encoding);// 考虑到编码格式
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				// 读取文件列表中的文件
				File thisFile = new File(lineTxt);
				showAllFiles(thisFile);
			}
			read.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("----End----");
	}

	

	/**
	 * 遍历目录下所有文件,如果是文件夹则继续遍历，如果是文件就直接导出
	 * 
	 * @param dir
	 * @throws Exception
	 */
	private static void showAllFiles(File file) throws Exception {
		// 如果是文件夹则继续遍历
		if (file.isDirectory()) {
			File[] fs = file.listFiles();
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].isDirectory()) {
					try {
						showAllFiles(fs[i]);
					} catch (Exception e) {

					}
				} else if (fs[i].isFile()) {
					System.out.println("查找以下文件并导出：" + fs[i].getAbsolutePath());
					exportFile(fs[i]);
				}
			}
		}
		// 如果是文件就直接导出
		else {
			exportFile(file);
		}

	}

	/**
	 * 复制文件到指定目录下
	 * 
	 * @param file
	 */
	private static void exportFile(File file) {
		List<File> fileList = new ArrayList<File>();
		// 首先去指定目录下查找文件
		Util.findFiles(srcAddress + "\\" + projectName, fileNameFilter(file.getName()), fileList);
		File srcFile = fileList.get(0);
		String parentFolderName = srcFile.getParent();
		String[] pathArray = parentFolderName.split(projectName + "\\\\");
		// 取出文件的父路径，添加到目标目录地址下
		String desFolder = desAddress + "\\" + projectName + "\\" + pathArray[1];
		File parentFolder = new File(desFolder);
		if (!parentFolder.exists()) {
			parentFolder.mkdirs();
		}
		// 复制文件到指定目录下
		try {
			File desFile = new File(parentFolder.getAbsoluteFile() + "\\" + srcFile.getName());
			copyFile(srcFile, desFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * 过滤要匹配的文件
	 * 1：如果是.java文件，则查找对应的class文件
	 * 
	 * @param fileName
	 * @return
	 */
	private static String fileNameFilter(String fileName){
		String extensionName = Util.getExtensionName(fileName);
		if(extensionName.equals("java")){
			fileName = Util.getFileNameNoEx(fileName) + ".class";
		}
		return fileName;
	}

	/**
	 * 
	 * @param fileIn
	 *            要被copy的文件
	 * @param fileOutPut
	 *            将文件copy到那个目录下面 (此处为目录+要复制的文件名 例如C:\folder + \ + fileName)
	 * @throws Exception
	 */
	private static void copyFile(File fileIn, File fileOutPut) throws Exception {
		FileInputStream fileInputStream = new FileInputStream(fileIn);
		FileOutputStream fileOutputStream = new FileOutputStream(fileOutPut);
		byte[] by = new byte[1024];
		int len;
		while ((len = fileInputStream.read(by)) != -1) {
			fileOutputStream.write(by, 0, len);
		}
		fileInputStream.close();
		fileOutputStream.close();
	}

	

}
