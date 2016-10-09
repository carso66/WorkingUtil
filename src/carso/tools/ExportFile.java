package carso.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ExportFile {

	// ����Ŀ¼
	private static String srcAddress = "";

	// ����Ŀ¼
	private static String desAddress = "";

	// ��Ŀ����
	private static String projectName = "ApiService";

	public static void main(String[] args) {
		System.out.println("----Start----");
		srcAddress = Util.getOneLineFromFile("resource/srcAddress.properties");
		desAddress = Util.getOneLineFromFile("resource/desAddress.properties");
		
		//��ȡĿ���ļ��б�
		File fileList = new File("resource/fileList.properties");

		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(fileList), Util.encoding);// ���ǵ������ʽ
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				// ��ȡ�ļ��б��е��ļ�
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
	 * ����Ŀ¼�������ļ�,������ļ��������������������ļ���ֱ�ӵ���
	 * 
	 * @param dir
	 * @throws Exception
	 */
	private static void showAllFiles(File file) throws Exception {
		// ������ļ������������
		if (file.isDirectory()) {
			File[] fs = file.listFiles();
			for (int i = 0; i < fs.length; i++) {
				if (fs[i].isDirectory()) {
					try {
						showAllFiles(fs[i]);
					} catch (Exception e) {

					}
				} else if (fs[i].isFile()) {
					System.out.println("���������ļ���������" + fs[i].getAbsolutePath());
					exportFile(fs[i]);
				}
			}
		}
		// ������ļ���ֱ�ӵ���
		else {
			exportFile(file);
		}

	}

	/**
	 * �����ļ���ָ��Ŀ¼��
	 * 
	 * @param file
	 */
	private static void exportFile(File file) {
		List<File> fileList = new ArrayList<File>();
		// ����ȥָ��Ŀ¼�²����ļ�
		Util.findFiles(srcAddress + "\\" + projectName, fileNameFilter(file.getName()), fileList);
		File srcFile = fileList.get(0);
		String parentFolderName = srcFile.getParent();
		String[] pathArray = parentFolderName.split(projectName + "\\\\");
		// ȡ���ļ��ĸ�·������ӵ�Ŀ��Ŀ¼��ַ��
		String desFolder = desAddress + "\\" + projectName + "\\" + pathArray[1];
		File parentFolder = new File(desFolder);
		if (!parentFolder.exists()) {
			parentFolder.mkdirs();
		}
		// �����ļ���ָ��Ŀ¼��
		try {
			File desFile = new File(parentFolder.getAbsoluteFile() + "\\" + srcFile.getName());
			copyFile(srcFile, desFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
	/**
	 * ����Ҫƥ����ļ�
	 * 1�������.java�ļ�������Ҷ�Ӧ��class�ļ�
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
	 *            Ҫ��copy���ļ�
	 * @param fileOutPut
	 *            ���ļ�copy���Ǹ�Ŀ¼���� (�˴�ΪĿ¼+Ҫ���Ƶ��ļ��� ����C:\folder + \ + fileName)
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
