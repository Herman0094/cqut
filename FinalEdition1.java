package HuffMan;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Scanner;

public class FinalEdition1 {
	static HuffmanCoding HMC = new HuffmanCoding();
	static fileFunction f = new fileFunction();

	public static void main(String[] args) throws Exception {
		// �ļ�ѹ��
		f.wrtieString();
		String srcFile = "1.txt";
		String dstFile = "2.zip";
		System.out.println("��������������Ϊ:");
		try {
			HMC.zipFile(srcFile, dstFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// �ļ���ѹ
		System.out.println("��������������Ϊ:");
		try {
			HMC.unZip("2.zip", "2.txt");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Node1 implements Comparable<Node1> {

	Byte data;
	int weight;
	Node1 left;
	Node1 right;

	public Node1() {

	}

	public Node1(Byte data, int weight) {
		this.data = data;
		this.weight = weight;
	}

	@Override
	public String toString() {
		return "Node2 [data=" + data + ", weight=" + weight + "]";
	}

	@Override
	public int compareTo(Node1 o) {
		// TODO Auto-generated method stub
		return o.weight - this.weight;
	}
}

class HuffmanTree {
	List<Node1> node = new ArrayList<Node1>();

	public Node1 TREE(List<Node1> node) {
		while (node.size() > 1) {
			// ����
			Collections.sort(node);
			// ȡ��Ȩֵ��С�ö�����
			Node1 left = node.get(node.size() - 1);
			Node1 right = node.get(node.size() - 2);
			// �����µĶ�����
			Node1 parent = new Node1(null, left.weight + right.weight);
			// ��ȡ�����ö���������Ϊ�´����ö�����������
			parent.left = left;
			parent.right = right;
			// ɾ��ǰ��ö�����
			node.remove(left);
			node.remove(right);
			// ���´����ö��������ڼ�����
			node.add(parent);
		}
		return node.get(0);
	}
}

class HuffmanCoding1 {
	creatHuffmanTree creatTree = new creatHuffmanTree();
	List<Node2> node = new ArrayList<>();
	static StringBuilder sb = new StringBuilder();
	static Map<Byte, String> huffcode = new HashMap<>();

	// �ļ�ѹ��
	public void zipFile(String srcFile, String dstFile) throws Exception {
		// ����һ��������
		InputStream is = new FileInputStream(srcFile);
		// ����һ����������ָ��һ����С��byte����
		byte[] b = new byte[is.available()];
		// ��ȡ�ļ�����
		is.read(b);
		is.close();
		// ʹ�ù�����������б���
		byte[] byteZip = huffmanZip(b);
		System.out.println("������ѹ�����ֽ�Ϊ" + byteZip.length);
		// �����
		OutputStream os = new FileOutputStream(dstFile);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		// ��ѹ�����byte����д���ļ�
		oos.writeObject(byteZip);
		// �������������д���ļ�
		oos.writeObject(huffcode);
		oos.close();
		os.close();
	}

	// �ļ���ѹ
	public void unZip(String srcFile, String dstFile) throws Exception {
		// ����һ��������
		InputStream is = new FileInputStream("2.zip");
		ObjectInputStream ois = new ObjectInputStream(is);
		// ��ȡbyte����
		byte[] b = (byte[]) ois.readObject();
		// ��ȡ�����������
		Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
		ois.close();
		is.close();
		// ����
		byte[] bytes = decode(codes, b);
		// ����һ�������
		OutputStream os = new FileOutputStream(dstFile);
		// д������
		os.write(bytes);
		os.close();
	}

	// ����������
	public byte[] huffmanZip(byte[] bytes) {
		// ��ͳ��ÿ��byte���ֵô���,������һ��������
		List<Node2> nodes = getNode2(bytes);
		// System.out.println(nodes);
		// ����һ�ù�������
		Node2 tree = creatTree.TREE(nodes);
		// System.out.println(tree);
		// ���������������
		Map<Byte, String> HuffCodes = getCodes(tree);
		// System.out.println(huffcode);
		// ����
		byte[] b = zip(bytes, huffcode);
		return b;
	}

	// ����
	public static byte[] decode(Map<Byte, String> huffcode2, byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		// ��byte����תΪһ������������
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			// �Ƿ������һ��
			boolean flog = (i == bytes.length - 1);
			sb.append(byteToStr(!flog, b));
		}
		System.out.println(sb.toString());
		// ���ַ�����ָ���ù�����������н���
		// �ѹ���������ý�ֵ�Խ���
		Map<String, Byte> map = new HashMap<>();
		for (Map.Entry<Byte, String> entry : huffcode.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		// ����һ������,���ڴ�byte
		List<Byte> list = new ArrayList<>();
		// �����ַ���
		for (int i = 0; i < sb.length();) {
			int count = 1;
			boolean flog = true;
			Byte b = null;
			// ��ȡ��key
			while (flog) {
				String key = sb.substring(i, i + count);
				b = map.get(key);
				if (b == null) {
					count++;
				} else {
					flog = false;
				}
			}
			// System.out.println(b);
			list.add(b);
			i += count;
		}
		// �ѽ��תΪ����
		byte[] b = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	// ����
	private static String byteToStr(boolean flog, byte b) {
		int temp = b;
		if (flog) {
			temp |= 256;

		}
		String str = Integer.toBinaryString(temp);
		if (flog) {
			return str.substring(str.length() - 8);
		} else {
			return str;
		}
	}

	// ѹ��
	private byte[] zip(byte[] bytes, Map<Byte, String> huffcode2) {
		StringBuilder sb = new StringBuilder();
		// ����Ҫѹ����byte���鴦��ɶ������ַ���
		for (byte b : bytes) {
			sb.append(huffcode.get(b));
		}
		System.out.println(sb.toString());
		// ���峤��
		int len;
		if (sb.length() % 8 == 0) {
			len = sb.length() / 8;
		} else {
			len = sb.length() / 8 + 1;
		}
		// ���ڴ洢ѹ�����byte
		byte[] by = new byte[len];
		// ��¼�µ�byte��λ��
		int index = 0;
		for (int i = 0; i < sb.length(); i += 8) {
			String strByte;
			if (i + 8 > sb.length()) {
				strByte = sb.substring(i);
			} else {
				strByte = sb.substring(i, i + 8);
			}
			// System.out.println(strByte);
			byte byt = (byte) Integer.parseInt(strByte, 2);
			// System.out.println(strByte+";"+byt);
			by[index] = byt;
			index++;
		}
		return by;
	}

	private Map<Byte, String> getCodes(Node2 tree) {
		if (tree == null) {
			return null;
		}
		getCodes(tree.left, "0", sb);
		getCodes(tree.right, "1", sb);
		return huffcode;
	}

	// �ݹ�
	private void getCodes(Node2 node, String code, StringBuilder sb) {
		StringBuilder sb2 = new StringBuilder(sb);
		sb2.append(code);
		if (node.data == null) {
			getCodes(node.left, "0", sb2);
			getCodes(node.right, "1", sb2);
		} else {
			huffcode.put(node.data, sb2.toString());
		}
	}

	public List<Node2> getNode2(byte[] bytes) {
		// �洢ÿ��byte���ֵô���
		Map<Byte, Integer> counts = new HashMap<>();
		// ͳ��ÿһ��byte���ֵô���
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		// ��ÿһ��ֵת��Ϊnode����
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			node.add(new Node2(entry.getKey(), entry.getValue()));
		}
		return node;
	}
}

class fileFunction1 {
	public void wrtieString() {
		File file = new File("1.txt"); // �����ļ�����
		System.out.println("�������ַ���");
		Scanner scanner = new Scanner(System.in);
		String msg = scanner.nextLine();
		try {
			if (!file.exists()) { // ����ļ����������½��ļ�
				file.createNewFile();

			}
			FileOutputStream output = new FileOutputStream(file);

			byte[] bytes = msg.getBytes();
			System.out.println("ԭ�ֽڳ���Ϊ" + bytes.length);
			output.write(bytes); // ���������Ϣд���ļ���

			output.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			FileInputStream input = new FileInputStream(file);
			StringBuilder sb = new StringBuilder();
			int len;
			if (sb.length() % 8 == 0) {
				len = sb.length() / 8;
			} else {
				len = sb.length() / 8 + 1;
			}
			byte[] bytes2 = new byte[len];

			int len1 = input.read(bytes2);

			System.out.println("������ļ�����ϢΪ��" + new String(bytes2, 0, len1));

			input.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
