 package cn.ac.azure.model;
/**
 * ͼ��ʵ��
 * @author green
 *
 */
public class Book {
	private Integer id;  //ͼ��id
	private String bookName;  //ͼ������
	private String author;  //ͼ������
	private String sex;     //�����Ա�
	private Float price;    //ͼ��۸�
	private Integer bookTypeId;  //ͼ�����ID
	private String bookTypeName;  //ͼ���������
	private String bookDesc;  //ͼ������
	//�޲ι��캯��
	public Book() {

	}
	
	public Book(String bookName, String author, String sex, Float price, Integer bookTypeId,String bookDesc) {
		super();
		this.bookName = bookName;
		this.author = author;
		this.sex = sex;
		this.price = price;
		this.bookTypeName = bookTypeName;
		this.bookDesc = bookDesc;
	}

	public Book(Integer id,String bookName, String author, String sex, Float price, Integer bookTypeId, String bookDesc) {
		super();
		this.id=id;
		this.bookName = bookName;
		this.author = author;
		this.sex = sex;
		this.price = price;
		this.bookTypeId = bookTypeId;
		this.bookDesc = bookDesc;
	}
	
	
	public String getBookTypeName() {
		return bookTypeName;
	}

	public void setBookTypeName(String bookTypeName) {
		this.bookTypeName = bookTypeName;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Float getPrice() {
		return price;
	}
	public void setPrice(Float price) {
		this.price = price;
	}
	public Integer getBookTypeId() {
		return bookTypeId;
	}
	public void setBookTypeId(Integer bookTypeId) {
		this.bookTypeId = bookTypeId;
	}
	public String getBookDesc() {
		return bookDesc;
	}
	public void setBookDesc(String bookDesc) {
		this.bookDesc = bookDesc;
	}
}
