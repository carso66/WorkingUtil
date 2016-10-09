package carso.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Util {
	
	// ���뷽ʽ
	public static String encoding = "UTF-8";
	
	/**
	 * ��ȡ�ļ���չ��
	 * 
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * ��ȡ������չ�����ļ���
	 * 
	 * @param filename
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
	
	/**
	 * �ݹ�����ļ�
	 * 
	 * @param baseDirName
	 *            ���ҵ��ļ���·��
	 * @param targetFileName
	 *            ��Ҫ���ҵ��ļ���
	 * @param fileList
	 *            ���ҵ����ļ�����
	 */
	public static void findFiles(String baseDirName, String targetFileName, List<File> fileList) {
		/**
		 * �㷨������ ��ĳ������������ҵ��ļ��г������������ļ��е��������ļ��м��ļ���
		 * ��Ϊ�ļ��������ƥ�䣬ƥ��ɹ��������������Ϊ���ļ��У�������С� ���в��գ��ظ���������������Ϊ�գ�������������ؽ����
		 */
		String tempName = null;
		// �ж�Ŀ¼�Ƿ����
		File baseDir = new File(baseDirName);
		if (!baseDir.exists() || !baseDir.isDirectory()) {
			System.out.println("�ļ�����ʧ�ܣ�" + baseDirName + "����һ��Ŀ¼��");
		} else {
			String[] filelist = baseDir.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(baseDirName + "\\" + filelist[i]);
				// System.out.println(readfile.getName());
				if (!readfile.isDirectory()) {
					tempName = readfile.getName();
					if (wildcardMatch(targetFileName, tempName)) {
						// ƥ��ɹ������ļ�����ӵ������
						fileList.add(readfile.getAbsoluteFile());
						System.out.println("���ҳɹ����������ļ����и��ƣ�" + readfile.getAbsoluteFile().getAbsolutePath());
					}
				} else if (readfile.isDirectory()) {
					findFiles(baseDirName + "\\" + filelist[i], targetFileName, fileList);
				}
			}
		}
	}

	/**
	 * ͨ���ƥ��
	 * 
	 * @param pattern
	 *            ͨ���ģʽ
	 * @param str
	 *            ��ƥ����ַ���
	 * @return ƥ��ɹ��򷵻�true�����򷵻�false
	 */
	public static boolean wildcardMatch(String pattern, String str) {
		int patternLength = pattern.length();
		int strLength = str.length();
		int strIndex = 0;
		char ch;
		for (int patternIndex = 0; patternIndex < patternLength; patternIndex++) {
			ch = pattern.charAt(patternIndex);
			if (ch == '*') {
				// ͨ����Ǻ�*��ʾ����ƥ���������ַ�
				while (strIndex < strLength) {
					if (wildcardMatch(pattern.substring(patternIndex + 1), str.substring(strIndex))) {
						return true;
					}
					strIndex++;
				}
			} else if (ch == '?') {
				// ͨ����ʺ�?��ʾƥ������һ���ַ�
				strIndex++;
				if (strIndex > strLength) {
					// ��ʾstr���Ѿ�û���ַ�ƥ��?�ˡ�
					return false;
				}
			} else {
				if ((strIndex >= strLength) || (ch != str.charAt(strIndex))) {
					return false;
				}
				strIndex++;
			}
		}
		return (strIndex == strLength);
	}
	
	/**
	 * ��ȡ�����ļ����һ�У�����ֱ��ճ���ļ���ַ��ʡ��ת�壩
	 * 
	 * @param fileName
	 * @return
	 */
	public static String getOneLineFromFile(String fileName) {
		File file = new File(fileName);

		String result = null;
		try {
			InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);// ���ǵ������ʽ
			BufferedReader bufferedReader = new BufferedReader(read);
			String lineTxt = null;
			while ((lineTxt = bufferedReader.readLine()) != null) {
				result = lineTxt;
			}
			read.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}

}
