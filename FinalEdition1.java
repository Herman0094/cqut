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
		// 文件压缩
		f.wrtieString();
		String srcFile = "1.txt";
		String dstFile = "2.zip";
		System.out.println("哈夫曼编码后代码为:");
		try {
			HMC.zipFile(srcFile, dstFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 文件解压
		System.out.println("哈夫曼解码后代码为:");
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
			// 排序
			Collections.sort(node);
			// 取出权值最小得二叉树
			Node1 left = node.get(node.size() - 1);
			Node1 right = node.get(node.size() - 2);
			// 创建新的二叉树
			Node1 parent = new Node1(null, left.weight + right.weight);
			// 把取出来得二叉树设置为新创建得二叉树得子树
			parent.left = left;
			parent.right = right;
			// 删除前面得二叉树
			node.remove(left);
			node.remove(right);
			// 把新创建得二叉树放在集合中
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

	// 文件压缩
	public void zipFile(String srcFile, String dstFile) throws Exception {
		// 创建一个输入流
		InputStream is = new FileInputStream(srcFile);
		// 创建一个和输入流指向一样大小得byte数组
		byte[] b = new byte[is.available()];
		// 读取文件内容
		is.read(b);
		is.close();
		// 使用哈夫曼编码进行编码
		byte[] byteZip = huffmanZip(b);
		System.out.println("哈夫曼压缩后字节为" + byteZip.length);
		// 输出流
		OutputStream os = new FileOutputStream(dstFile);
		ObjectOutputStream oos = new ObjectOutputStream(os);
		// 将压缩后得byte数组写入文件
		oos.writeObject(byteZip);
		// 将哈夫曼编码表写入文件
		oos.writeObject(huffcode);
		oos.close();
		os.close();
	}

	// 文件解压
	public void unZip(String srcFile, String dstFile) throws Exception {
		// 创建一个输入流
		InputStream is = new FileInputStream("2.zip");
		ObjectInputStream ois = new ObjectInputStream(is);
		// 读取byte数组
		byte[] b = (byte[]) ois.readObject();
		// 读取哈夫曼编码表
		Map<Byte, String> codes = (Map<Byte, String>) ois.readObject();
		ois.close();
		is.close();
		// 解码
		byte[] bytes = decode(codes, b);
		// 创建一个输出流
		OutputStream os = new FileOutputStream(dstFile);
		// 写出数据
		os.write(bytes);
		os.close();
	}

	// 哈夫曼编码
	public byte[] huffmanZip(byte[] bytes) {
		// 先统计每个byte出现得次数,并放入一个集合中
		List<Node2> nodes = getNode2(bytes);
		// System.out.println(nodes);
		// 创建一棵哈夫曼树
		Node2 tree = creatTree.TREE(nodes);
		// System.out.println(tree);
		// 创建哈夫曼编码表
		Map<Byte, String> HuffCodes = getCodes(tree);
		// System.out.println(huffcode);
		// 编码
		byte[] b = zip(bytes, huffcode);
		return b;
	}

	// 解码
	public static byte[] decode(Map<Byte, String> huffcode2, byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		// 把byte数组转为一个二进制数组
		for (int i = 0; i < bytes.length; i++) {
			byte b = bytes[i];
			// 是否是最后一个
			boolean flog = (i == bytes.length - 1);
			sb.append(byteToStr(!flog, b));
		}
		System.out.println(sb.toString());
		// 把字符串按指定得哈夫曼编码进行解码
		// 把哈夫曼编码得健值对交换
		Map<String, Byte> map = new HashMap<>();
		for (Map.Entry<Byte, String> entry : huffcode.entrySet()) {
			map.put(entry.getValue(), entry.getKey());
		}
		// 创建一个集合,用于存byte
		List<Byte> list = new ArrayList<>();
		// 处理字符串
		for (int i = 0; i < sb.length();) {
			int count = 1;
			boolean flog = true;
			Byte b = null;
			// 截取出key
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
		// 把结合转为数组
		byte[] b = new byte[list.size()];
		for (int i = 0; i < b.length; i++) {
			b[i] = list.get(i);
		}
		return b;
	}

	// 补码
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

	// 压缩
	private byte[] zip(byte[] bytes, Map<Byte, String> huffcode2) {
		StringBuilder sb = new StringBuilder();
		// 把需要压缩得byte数组处理成二进制字符串
		for (byte b : bytes) {
			sb.append(huffcode.get(b));
		}
		System.out.println(sb.toString());
		// 定义长度
		int len;
		if (sb.length() % 8 == 0) {
			len = sb.length() / 8;
		} else {
			len = sb.length() / 8 + 1;
		}
		// 用于存储压缩后的byte
		byte[] by = new byte[len];
		// 记录新的byte得位置
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

	// 递归
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
		// 存储每个byte出现得次数
		Map<Byte, Integer> counts = new HashMap<>();
		// 统计每一个byte出现得次数
		for (byte b : bytes) {
			Integer count = counts.get(b);
			if (count == null) {
				counts.put(b, 1);
			} else {
				counts.put(b, count + 1);
			}
		}
		// 把每一个值转换为node对象
		for (Map.Entry<Byte, Integer> entry : counts.entrySet()) {
			node.add(new Node2(entry.getKey(), entry.getValue()));
		}
		return node;
	}
}

class fileFunction1 {
	public void wrtieString() {
		File file = new File("1.txt"); // 创建文件对象
		System.out.println("请输入字符串");
		Scanner scanner = new Scanner(System.in);
		String msg = scanner.nextLine();
		try {
			if (!file.exists()) { // 如果文件不存在则新建文件
				file.createNewFile();

			}
			FileOutputStream output = new FileOutputStream(file);

			byte[] bytes = msg.getBytes();
			System.out.println("原字节长度为" + bytes.length);
			output.write(bytes); // 将数组的信息写入文件中

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

			System.out.println("解码后文件中信息为：" + new String(bytes2, 0, len1));

			input.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
