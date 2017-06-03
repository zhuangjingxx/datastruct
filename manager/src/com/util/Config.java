package com.util;

import java.io.File;

public class Config {
	
	static{
	
		File index=new File(".\\index");
		File data2=new File(".\\student_data");
		File data3=new File(".\\teacher_data");
		File data4=new File(".\\course_data");
		File data5=new File(".\\coursechoose_data");
		File data6=new File(".\\other_data");
		File data7=new File(".\\coursetime_data");
		index.mkdir();
		data2.mkdir();
		data3.mkdir();
		data4.mkdir();
		data5.mkdir();
		data6.mkdir();
		data7.mkdir();
	}


	public static final String 	COL_INFORMASTION_FILENAME=".\\other_data\\col_information.txt";//ѧԺ��Ϣ�ļ��Ĵ洢·��
	public static final String 	CLS_INFORMASTION_FILENAME=".\\other_data\\cls_information.txt";//�༶��Ϣ�ļ��Ĵ洢·��
	public static final String 	MAJ_INFORMASTION_FILENAME=".\\other_data\\maj_information.txt";//רҵ��Ϣ�ļ��Ĵ洢·��
	
	public static final String 	STU_INFORMASTION_CODEINDEX__FILENAME=".\\index\\stu_codeindex.txt";//ѧ����Ϣ��ѧ�������ļ��Ĵ洢·��
	public static final String 	STU_INFORMASTION_NAMEINDEX_FILENAME=".\\index\\stu_nameindex.txt";//ѧ����Ϣ�����������ļ��Ĵ洢·��
	public static final String 	TEA_INFORMASTION_CODEINDEX_FILENAME=".\\index\\tea_codeindex.txt";//��ʦ��Ϣ�����������ļ��Ĵ洢·��
	public static final String 	TEA_INFORMASTION_NAMEINDEX_FILENAME=".\\index\\tea_nameindex.txt";//��ʦ��Ϣ�����������ļ��Ĵ洢·��
	public static final String 	TEA_INFORMASTION_COUNAMEINDEX_FILENAME=".\\index\\tea_counameindex.txt";//��ʦ��Ϣ�����������ļ��Ĵ洢·��
	public static final String 	COU_INFORMASTION_CODEINDEX_FILENAME=".\\index\\cou_codeindex.txt";//�γ���Ϣ���γ̺������ļ��Ĵ洢·��
	public static final String 	COU_INFORMASTION_NAMEINDEX_FILENAME=".\\index\\cou_nameindex.txt";//�γ���Ϣ���γ����������ļ��Ĵ洢·��
	public static final String 	COUTIME_INFORMASTION_CODEINDEX_FILENAME=".\\index\\coutime_codeindex.txt";//�Ͽ�ʱ����Ϣ���γ̺������ļ��Ĵ洢·��
	public static final String 	COUCHOOSE_INFORMASTION_STUCODEINDEX_FILENAME=".\\index\\couchoose_stucodeindex.txt";//ѡ����Ϣ�ļ���ѧ�������Ĵ洢·��
	public static final String 	COUCHOOSE_INFORMASTION_TEAINDEX_FILENAME=".\\index\\couchoose_teacodeindex.txt";//ѡ����Ϣ����ʦ���������ļ��Ĵ洢·��
	public static final String 	COUCHOOSE_INFORMASTION_STUINDEX_FILENAME=".\\index\\couchooose_coucodeindex.txt";//ѡ����Ϣ���γ̺������ļ��Ĵ洢·��
	public static final String 	COL_INFORMASTION_CODEINDEX_FILENAME=".\\index\\col_codeindex.txt";//ѧԺ��Ϣ��ѧԺ�������ļ��Ĵ洢·��
	public static final String 	CLS_INFORMASTION_CODEINDEX_FILENAME=".\\index\\cls_codeindex.txt";//�༶��Ϣ���༶���ļ��Ĵ洢·��
	public static final String 	MAJ_INFORMASTION_CODEINDEX_FILENAME=".\\index\\maj_codeindex.txt";//רҵ��Ϣ��רҵ�������ļ��Ĵ洢·��


			
			
}
