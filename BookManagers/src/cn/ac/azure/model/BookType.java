package cn.ac.azure.model;
/**
 * ͼ�����ʵ��
 * @author green
 *
 */
public class BookType {
	private int id;  //����ID
	private String bookTypeName;  //����ͼ���������
	private String bookTypeDesc;  //����ͼ���������
	//�޲ι�����
	public BookType() {

	}
	//�вι��캯��
	public BookType(String bookTypeName, String bookTypeDesc) {
		super();
		this.bookTypeName = bookTypeName;
		this.bookTypeDesc = bookTypeDesc;
	}
	
	public BookType(int id, String bookTypeName, String bookTypeDesc) {
		super();
		this.id = id;
		this.bookTypeName = bookTypeName;
		this.bookTypeDesc = bookTypeDesc;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookTypeName() {
		return bookTypeName;
	}
	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}
	public String getBookTypeDesc() {
		return bookTypeDesc;
	}
	public void setBookTypeDesc(String bookTypeDesc) {
		this.bookTypeDesc = bookTypeDesc;
	}
	@Override
	public String toString() {
		return bookTypeName;
	}
}
